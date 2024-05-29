package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "email VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL" +
                ");";

        String createToDoItemsTable = "CREATE TABLE IF NOT EXISTS todo_items (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "description TEXT," +
                "status VARCHAR(255)," +
                "user_email VARCHAR(255) NOT NULL," +
                "FOREIGN KEY (user_email) REFERENCES users (email)" +
                ");";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            // create new tables
            stmt.execute(createUsersTable);
            stmt.execute(createToDoItemsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
