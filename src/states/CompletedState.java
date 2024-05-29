package states;

import models.ToDoItem;

// Concrete state class representing the "completed" state of a ToDoItem
public class CompletedState implements State {

    // Method called when trying to start a task in the "completed" state
    @Override
    public void start(ToDoItem item) {
        System.out.println("Cannot start a completed task.");
    }

    // Method called when trying to complete a task in the "completed" state
    @Override
    public void complete(ToDoItem item) {
        System.out.println("Task is already completed.");
    }

    // Method called when trying to cancel a task in the "completed" state
    @Override
    public void cancel(ToDoItem item) {
        System.out.println("Cannot cancel a completed task.");
    }
}
