package dao;

import config.DatabaseConfig;
import models.ToDoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemDao {

    // Method to save a new to-do item to the database
    public boolean save(ToDoItem item) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "INSERT INTO todo_items (title, description, status, user_email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getStatus());
            statement.setString(4, item.getUserEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to find a to-do item by its ID
    public ToDoItem findById(int id) {
        ToDoItem item = null;
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM todo_items WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = new ToDoItem(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getString("user_email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    // Method to find to-do items by the user's email
    public List<ToDoItem> findByUserEmail(String userEmail) {
        List<ToDoItem> items = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM todo_items WHERE user_email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(new ToDoItem(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getString("user_email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Method to update the status of a to-do item
    public boolean updateStatus(int id, String status) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "UPDATE todo_items SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a to-do item by its ID
    public boolean delete(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "DELETE FROM todo_items WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
