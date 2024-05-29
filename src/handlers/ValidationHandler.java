package handlers;

import models.User;
import java.util.regex.Pattern;

public class ValidationHandler extends Handler {
    // Regex pattern for validating email addresses
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$");
    // Regex pattern for validating passwords (minimum 8 characters, at least one letter and one number)
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    @Override
    public boolean handle(User user) {
        // Perform email and password validation
        if (!isValidEmail(user.getEmail()) || !isValidPassword(user.getPassword())) {
            System.out.println("Validation failed for user: " + user.getEmail());
            return false; // Validation failed
        }
        
        // If there is a next handler in the chain, pass the user to the next handler
        if (next != null) {
            return next.handle(user);
        }
        
        // Validation succeeded and there is no next handler
        return true;
    }

    // Helper method to validate email using regex
    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Helper method to validate password using regex
    private boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
}
