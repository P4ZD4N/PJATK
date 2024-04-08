package task7;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private List<Enclosure> enclosures = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();

    public Zoo(List<Worker> workers, List<Enclosure> enclosures) {
        this.workers = workers;
        this.enclosures = enclosures;
    }

    public List<Enclosure> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void showAllAnimals() {

    }

    public void showAllWorkers() {

    }
}
