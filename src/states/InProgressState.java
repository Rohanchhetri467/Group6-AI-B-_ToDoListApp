package states;

import models.ToDoItem;

// Concrete state class representing the "in progress" state of a ToDoItem
public class InProgressState implements State {

    // Method called when trying to start a task in the "in progress" state
    @Override
    public void start(ToDoItem item) {
        System.out.println("Task is already in progress.");
    }

    // Method called when trying to complete a task in the "in progress" state
    @Override
    public void complete(ToDoItem item) {
        // Change the status to "completed" and set the new state to CompletedState
        item.setStatus("completed");
        item.setState(new CompletedState());
    }

    // Method called when trying to cancel a task in the "in progress" state
    @Override
    public void cancel(ToDoItem item) {
        // Change the status to "canceled" and set the new state to CanceledState
        item.setStatus("canceled");
        item.setState(new CanceledState());
    }
}
