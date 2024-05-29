package models;

public class User {
    private String email;
    private String password;

    // Constructor to initialize a User object with email and password
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter method to retrieve the email of the user
    public String getEmail() {
        return email;
    }

    // Getter method to retrieve the password of the user
    public String getPassword() {
        return password;
    }
}
