package views;

import controllers.AuthController;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the login view of the application
public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AuthController authController;

    // Constructor to initialize the login view
    public LoginView(AuthController authController) {
        this.authController = authController;
        setTitle("Login"); // Set the title of the window
        setSize(300, 150); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(null); // Set null layout (custom layout)

        // Create and add components to the window
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 10, 80, 25);
        add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 10, 160, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        add(loginButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get email and password input
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                // Perform login using AuthController
                User user = authController.login(email, password);
                if (user != null) {
                    // If login successful, show a success message and open the to-do list view
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    new ToDoListView(user.getEmail()).setVisible(true);
                    dispose(); // Close the login window
                } else {
                    // If login failed, show an error message
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });
    }
}
