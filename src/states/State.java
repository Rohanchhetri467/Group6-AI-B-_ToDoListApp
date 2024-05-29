package states;

import models.ToDoItem;

// Interface defining the contract for concrete state classes
public interface State {
    
    // Method to start a task
    void start(ToDoItem item);
    
    // Method to complete a task
    void complete(ToDoItem item);
    
    // Method to cancel a task
    void cancel(ToDoItem item);
}
