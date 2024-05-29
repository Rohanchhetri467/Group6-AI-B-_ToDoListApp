package states;

import models.ToDoItem;

// Concrete state class representing the "canceled" state of a ToDoItem
public class CanceledState implements State {

    // Method called when trying to start a task in the "canceled" state
    @Override
    public void start(ToDoItem item) {
        System.out.println("Cannot start a canceled task.");
    }

    // Method called when trying to complete a task in the "canceled" state
    @Override
    public void complete(ToDoItem item) {
        System.out.println("Cannot complete a canceled task.");
    }

    // Method called when trying to cancel a task that is already canceled
    @Override
    public void cancel(ToDoItem item) {
        System.out.println("Task is already canceled.");
    }
}
