package models;

import states.CanceledState;
import states.CompletedState;
import states.InProgressState;
import states.PendingState;
import states.State;

public class ToDoItem {
    private int id;
    private String title;
    private String description;
    private String status;
    private String userEmail;
    private State state; // State pattern is used for managing different states of a ToDoItem

    // Constructor to initialize a ToDoItem object
    public ToDoItem(int id, String title, String description, String status, String userEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userEmail = userEmail;
        setState(status); // Set initial state based on status
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    // Method to set the state of the ToDoItem
    public void setState(State state) {
        this.state = state;
    }

    // Method to set the state of the ToDoItem based on status string
    public void setState(String status) {
        switch (status) {
            case "in progress":
                this.state = new InProgressState();
                break;
            case "completed":
                this.state = new CompletedState();
                break;
            case "canceled":
                this.state = new CanceledState();
                break;
            default:
                this.state = new PendingState();
                break;
        }
        this.status = status;
    }

    // Setter method for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Methods to perform actions on the ToDoItem based on its state
    public void start() {
        state.start(this);
    }

    public void complete() {
        state.complete(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    // toString method to represent ToDoItem as a string
    @Override
    public String toString() {
        return "Title: " + title + " | Description: " + description + " | Status: " + status;
    }
}
