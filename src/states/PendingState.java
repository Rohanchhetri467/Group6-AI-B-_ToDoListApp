package states;

import models.ToDoItem;

// Concrete state class representing the "pending" state of a ToDoItem
public class PendingState implements State {

    // Method called when trying to start a task in the "pending" state
    @Override
    public void start(ToDoItem item) {
        // Change the status to "in progress" and set the new state to InProgressState
        item.setStatus("in progress");
        item.setState(new InProgressState());
    }

    // Method called when trying to complete a task in the "pending" state
    @Override
    public void complete(ToDoItem item) {
        System.out.println("Cannot complete a pending task. Start it first.");
    }

    // Method called when trying to cancel a task in the "pending" state
    @Override
    public void cancel(ToDoItem item) {
        // Change the status to "canceled" and set the new state to CanceledState
        item.setStatus("canceled");
        item.setState(new CanceledState());
    }
}
