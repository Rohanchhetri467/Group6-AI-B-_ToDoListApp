package views;

import controllers.ToDoController;
import models.ToDoItem;
import observers.Observer;
import observers.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents the view for displaying and managing to-do items
public class ToDoListView extends JFrame implements Observer {
    private ToDoController toDoController;
    private String userEmail;
    private Subject subject;
    private DefaultListModel<ToDoItem> toDoListModel;
    private JList<ToDoItem> toDoList;
    private JButton addButton;
    private JButton removeButton;
    private JButton startButton;
    private JButton completeButton;
    private JButton cancelButton;
    private JTextField titleField;
    private JTextField descriptionField;

    // Constructor to initialize the to-do list view
    public ToDoListView(String userEmail) {
        this.userEmail = userEmail;
        this.toDoController = new ToDoController();
        this.subject = new Subject();
        subject.attach(this); // Attach this view as an observer to the subject

        setTitle("To-Do List"); // Set the title of the window
        setSize(600, 300); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout()); // Set BorderLayout for the layout

        // Create the list model and list to display to-do items
        toDoListModel = new DefaultListModel<>();
        toDoList = new JList<>(toDoListModel);
        add(new JScrollPane(toDoList), BorderLayout.CENTER); // Add the list to the center of the window

        // Create panel for input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // Grid layout for organizing components

        // Add input fields for title and description
        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        // Add buttons for actions
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        startButton = new JButton("Start");
        completeButton = new JButton("Complete");
        cancelButton = new JButton("Cancel");

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(startButton);
        panel.add(completeButton);
        panel.add(cancelButton);

        // Add the panel to the bottom of the window
        add(panel, BorderLayout.SOUTH);

        // Action listeners for buttons

        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                ToDoItem item = new ToDoItem(0, title, description, "pending", userEmail);
                toDoController.addToDoItem(item);
                subject.notifyObservers(); // Notify observers (update the view)
            }
        });

        // Remove button action
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoItem selectedItem = toDoList.getSelectedValue();
                if (selectedItem != null) {
                    toDoController.removeToDoItem(selectedItem.getId());
                    subject.notifyObservers(); // Notify observers (update the view)
                }
            }
        });

        // Start button action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoItem selectedItem = toDoList.getSelectedValue();
                if (selectedItem != null) {
                    selectedItem.start();
                    toDoController.updateStatus(selectedItem.getId(), selectedItem.getStatus());
                    subject.notifyObservers(); // Notify observers (update the view)
                }
            }
        });

        // Complete button action
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoItem selectedItem = toDoList.getSelectedValue();
                if (selectedItem != null) {
                    selectedItem.complete();
                    toDoController.updateStatus(selectedItem.getId(), selectedItem.getStatus());
                    subject.notifyObservers(); // Notify observers (update the view)
                }
            }
        });

        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoItem selectedItem = toDoList.getSelectedValue();
                if (selectedItem != null) {
                    selectedItem.cancel();
                    toDoController.updateStatus(selectedItem.getId(), selectedItem.getStatus());
                    subject.notifyObservers(); // Notify observers (update the view)
                }
            }
        });

        // Update the view initially
        update();
    }

    // Method to update the view when changes occur
    @Override
    public void update() {
        // Clear the list model
        toDoListModel.clear();
        // Retrieve the list of to-do items for the user
        List<ToDoItem> items = toDoController.getToDoItems(userEmail);
        // Add each item to the list model
        for (ToDoItem item : items) {
            toDoListModel.addElement(item);
        }
    }
}
