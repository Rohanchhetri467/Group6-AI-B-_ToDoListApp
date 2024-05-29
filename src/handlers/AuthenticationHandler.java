package handlers;

import dao.UserDao;
import models.User;

public class AuthenticationHandler extends Handler {
    private UserDao userDao;

    // Constructor to initialize the UserDao
    public AuthenticationHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean handle(User user) {
        // Check if the user exists in the database with the provided email and password
        if (userDao.findByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
            return false; // Authentication failed
        }

        // If there is a next handler in the chain, pass the user to the next handler
        if (next != null) {
            return next.handle(user);
        }

        // Authentication succeeded and there is no next handler
        return true;
    }
}
