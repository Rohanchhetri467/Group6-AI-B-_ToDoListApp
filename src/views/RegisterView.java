package views;

import controllers.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the registration view of the application
public class RegisterView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private AuthController authController;

    // Constructor to initialize the registration view
    public RegisterView(AuthController authController) {
        this.authController = authController;
        setTitle("Register"); // Set the title of the window
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

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 100, 25);
        add(registerButton);

        // Action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get email and password input
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                // Perform registration using AuthController
                boolean success = authController.register(email, password);
                if (success) {
                    // If registration successful, show a success message and open the login view
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    new LoginView(authController).setVisible(true);
                    dispose(); // Close the registration window
                } else {
                    // If registration failed, show an error message
                    JOptionPane.showMessageDialog(null, "Registration failed.");
                }
            }
        });
    }
}
