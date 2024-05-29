package views;

import controllers.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the initial view of the application
public class InitialView extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private AuthController authController;

    // Constructor to initialize the initial view
    public InitialView(AuthController authController) {
        this.authController = authController;
        setTitle("Welcome"); // Set the title of the window
        setSize(300, 150); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(null); // Set null layout (custom layout)

        // Create login button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 30, 80, 25); // Set bounds (position and size)
        add(loginButton); // Add the login button to the window

        // Create register button
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 30, 100, 25); // Set bounds (position and size)
        add(registerButton); // Add the register button to the window

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login view and dispose the current window
                new LoginView(authController).setVisible(true);
                dispose();
            }
        });

        // Action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the register view and dispose the current window
                new RegisterView(authController).setVisible(true);
                dispose();
            }
        });
    }
}
