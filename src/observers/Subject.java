package observers;

import java.util.ArrayList;
import java.util.List;

// Subject class representing the object being observed
public class Subject {
    // List to store observers
    private List<Observer> observers = new ArrayList<>();

    // Method to attach an observer
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // Method to detach an observer
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    // Method to notify all attached observers
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
