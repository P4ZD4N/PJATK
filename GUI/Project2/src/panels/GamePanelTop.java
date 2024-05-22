package panels;

import game.Bullet;
import game.Enemy;
import game.Ship;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanelTop extends JPanel {

    private SettingsPanel settings;
    private GamePanelBottom bottom;
    private Ship ship;
    private List<List<Enemy>> enemyRows;
    private Timer timer;

    public GamePanelTop(Ship ship, GamePanelBottom bottom, SettingsPanel settings) {

        this.settings = settings;
        this.bottom = bottom;
        this.ship = ship;
        ship.addKeyListener(ship);

        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0, 0, 50, 0));
        setLayout(null);

        add(ship);
        ship.setBounds(575, 600, 50, 50);

        enemyRows = new ArrayList<>();
        createEnemyRows();

        int delayFromSettings = (int) settings.getEnemyFallingTimeSpinner().getValue();
        timer = new Timer(1000 * delayFromSettings, e -> moveEnemies());
        timer.start();
    }

    private void createEnemyRows() {

        final int ENEMY_WIDTH = 30;
        final int ENEMY_HEIGHT = 30;
        final int ROW_COUNT = (int) settings.getEnemyRowsSpinner().getValue();
        final int COLUMN_COUNT = settings.getColumns();
        final int HORIZONTAL_GAP = 10;
        final int VERTICAL_GAP = 10;

        for (int i = 0; i < ROW_COUNT; i++) {

            List<Enemy> row = new ArrayList<>();
            for (int j = 0; j < COLUMN_COUNT; j++) {

                Enemy enemy = new Enemy();
                int x = j * (ENEMY_WIDTH + HORIZONTAL_GAP);
                int y = i * (ENEMY_HEIGHT + VERTICAL_GAP);
                enemy.setBounds(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
                row.add(enemy);
                add(enemy);
            }
            enemyRows.add(row);
        }
    }

    private void moveEnemies() {

        for (List<Enemy> row : enemyRows) {

            for (Enemy enemy : row) {

                enemy.setLocation(enemy.getX(), enemy.getY() + enemy.getHeight());
                int enemyY = enemy.getY();
                int enemyBottomY = enemyY + enemy.getHeight();
                int shipY = ship.getY();
                int shipBottomY = shipY + ship.getHeight();
                if (enemyBottomY >= shipY && enemyY <= shipBottomY) {
                    timer.stop();
                    exitGame();
                    return;
                }
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        checkCollisions();

        for (Component component : getComponents()) {
            if (component instanceof Bullet bullet) {
                bullet.move();
            }
        }
    }

    private void checkCollisions() {

        for (Component component : getComponents()) {

            if (component instanceof Bullet) {

                Bullet bullet = (Bullet) component;
                for (List<Enemy> row : enemyRows) {

                    for (Enemy enemy : row) {

                        if (bullet.hit(enemy)) {

                            remove(bullet);
                            remove(enemy);
                            row.remove(enemy);
                            repaint();
                            bottom.updateScore(settings.getPointsForEnemyHit());
                            return;
                        }
                    }
                }
            }
        }
    }

    public void pauseGame() {
        timer.stop();
        ship.removeKeyListener(ship);
    }

    public void resumeGame() {
        timer.start();
        ship.addKeyListener(ship);
    }

    public void displayPauseOptions() {

        Object[] options = {"Resume", "Exit"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Game paused.",
                "Pause",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == JOptionPane.YES_OPTION) {

            resumeGame();
        } else if (choice == JOptionPane.NO_OPTION) {

            exitGame();
        }
    }

    private void exitGame() {

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MenuPanel());
        frame.revalidate();
        frame.repaint();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}