package handlers;

import models.User;

public abstract class Handler {
    // The next handler in the chain
    protected Handler next;

    // Method to set the next handler in the chain
    public void setNext(Handler next) {
        this.next = next;
    }

    // Abstract method to handle the request. Must be implemented by concrete handlers
    public abstract boolean handle(User user);
}
