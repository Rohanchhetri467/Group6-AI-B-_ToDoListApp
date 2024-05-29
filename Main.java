// Import statements for the necessary classes from the controllers, views, and config packages
import controllers.AuthController;
import views.InitialView;
import config.DatabaseInitializer;

public class Main {

    // Main entry point of the application
    public static void main(String[] args) {
        
        // Initialize the database configuration and setup
        DatabaseInitializer.initialize();
        
        // Create an instance of the authentication controller
        AuthController authController = new AuthController();
        
        // Create and display the initial view, passing the authController instance to it
        new InitialView(authController).setVisible(true);
    }
}
