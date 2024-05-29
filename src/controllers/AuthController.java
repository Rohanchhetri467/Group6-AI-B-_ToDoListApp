package controllers;

import dao.UserDao;
import dao.UserDaoProxy;
import models.User;
import handlers.Handler;
import handlers.ValidationHandler;
import handlers.AuthenticationHandler;

public class AuthController {
    private UserDao userDao;

    public AuthController() {
        // Initialize UserDao with a proxy to handle additional functionalities like logging, etc.
        this.userDao = new UserDaoProxy(new UserDao());
    }

    // Method to handle user registration
    public boolean register(String email, String password) {
        // Create a new User object
        User user = UserCreate.createUser(email, password);

        // Chain of Responsibility for validation
        Handler validationHandler = new ValidationHandler();
        
        // If validation fails, return false indicating registration failure
        if (!validationHandler.handle(user)) {
            System.out.println("Registration failed: invalid email or password format.");
            return false; // Registration failed due to validation
        }

        // Save the user using UserDao and return the result of the operation
        return userDao.save(user);
    }

    // Method to handle user login
    public User login(String email, String password) {
        // Create a new User object with the provided credentials
        User user = new User(email, password);

        // Chain of Responsibility for validation and authentication
        Handler validationHandler = new ValidationHandler();
        Handler authenticationHandler = new AuthenticationHandler(userDao);

        // Set the next handler in the chain
        validationHandler.setNext(authenticationHandler);

        // If either validation or authentication fails, return null indicating login failure
        if (!validationHandler.handle(user)) {
            System.out.println("Login failed: invalid credentials.");
            return null; // Login failed due to validation or authentication
        }

        // Retrieve and return the user from the database
        return userDao.findByEmailAndPassword(email, password);
    }
}
