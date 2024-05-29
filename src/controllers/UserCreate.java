package controllers;

import models.User;

public class UserCreate {
    // Static method to create a new User object
    public static User createUser(String email, String password) {
        return new User(email, password);
    }
}
