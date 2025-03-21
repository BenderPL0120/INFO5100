package ui;

import model.User;
import utility.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormJPanel extends JPanel {
  private JTextField nameField, ageField, collegeField, hobbyField;
  private JButton addButton;

  public FormJPanel() {
    setLayout(new GridLayout(5, 2));

    add(new JLabel("Name:"));
    nameField = new JTextField();
    add(nameField);

    add(new JLabel("Age:"));
    ageField = new JTextField();
    add(ageField);

    add(new JLabel("College:"));
    collegeField = new JTextField();
    add(collegeField);

    add(new JLabel("Hobbies:"));
    hobbyField = new JTextField();
    add(hobbyField);

    addButton = new JButton("Add User");
    add(addButton);

    // listen tick action
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addUserToDatabase();
      }
    });
  }

  private void addUserToDatabase() {
    String name = nameField.getText();
    int age = Integer.parseInt(ageField.getText());
    String college = collegeField.getText();
    String hobby = hobbyField.getText();

    User user = new User(0, name, age, college, hobby);
    DatabaseConnector.addUser(user);

    JOptionPane.showMessageDialog(this, "User added successfully!");

    // clear input field
    nameField.setText("");
    ageField.setText("");
    collegeField.setText("");
    hobbyField.setText("");
  }
}

