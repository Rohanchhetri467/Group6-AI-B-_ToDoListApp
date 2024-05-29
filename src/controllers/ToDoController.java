package controllers;

import dao.ToDoItemDao;
import models.ToDoItem;

import java.util.List;

public class ToDoController {
    private ToDoItemDao toDoItemDao;

    // Constructor initializes the DAO (Data Access Object)
    public ToDoController() {
        this.toDoItemDao = new ToDoItemDao();
    }

    // Method to add a new to-do item
    public void addToDoItem(ToDoItem item) {
        toDoItemDao.save(item);
    }

    // Method to remove a to-do item by its ID
    public void removeToDoItem(int id) {
        toDoItemDao.delete(id);
    }

    // Method to update the status of a to-do item
    public void updateStatus(int id, String status) {
        // Find the to-do item by its ID
        ToDoItem item = toDoItemDao.findById(id);
        
        // If the item is found, update its status
        if (item != null) {
            item.setState(status);
            toDoItemDao.updateStatus(id, status);
        }
    }

    // Method to retrieve all to-do items for a specific user by their email
    public List<ToDoItem> getToDoItems(String userEmail) {
        return toDoItemDao.findByUserEmail(userEmail);
    }
}
