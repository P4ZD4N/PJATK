package t3;

public class Main {

    public static void main(String[] args) {

        Frame frame = new Frame();
        Panel panel = new Panel(frame);
        frame.add(panel);
        panel.saveShapesAsImages();
    }
}
