package ui;

import model.User;
import utility.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewJPanel extends JPanel {
  private JTable userTable;
  private DefaultTableModel tableModel;
  private JButton deleteButton, updateButton;
  private JTextField nameField, ageField, collegeField, hobbyField;

  public ViewJPanel() {
    setLayout(new BorderLayout());

    // table init
    tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "College", "Hobbies"}, 0);
    userTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(userTable);
    add(scrollPane, BorderLayout.CENTER);

    // load data
    loadUserData();

    // edit area
    JPanel editPanel = new JPanel(new GridLayout(5, 2));

    editPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    editPanel.add(nameField);

    editPanel.add(new JLabel("Age:"));
    ageField = new JTextField();
    editPanel.add(ageField);

    editPanel.add(new JLabel("College:"));
    collegeField = new JTextField();
    editPanel.add(collegeField);

    editPanel.add(new JLabel("Hobby:"));
    hobbyField = new JTextField();
    editPanel.add(hobbyField);

    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");

    editPanel.add(updateButton);
    editPanel.add(deleteButton);

    add(editPanel, BorderLayout.SOUTH);

    // listen choice of the table
    userTable.getSelectionModel().addListSelectionListener(e -> {
      int row = userTable.getSelectedRow();
      if (row != -1) {
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        ageField.setText(tableModel.getValueAt(row, 2).toString());
        collegeField.setText(tableModel.getValueAt(row, 3).toString());
        hobbyField.setText(tableModel.getValueAt(row, 4).toString());
      }
    });

    // update button
    updateButton.addActionListener(e -> updateUser());

    // delete button
    deleteButton.addActionListener(e -> deleteUser());
  }

  public void loadUserData() {
    tableModel.setRowCount(0);
    ArrayList<User> users = DatabaseConnector.getUsers();
    for (User user : users) {
      tableModel.addRow(new Object[]{user.getUserId(), user.getName(), user.getAge(), user.getCollege(), user.getHobby()});
    }
  }

  private void updateUser() {
    int row = userTable.getSelectedRow();
    if (row == -1) {
      JOptionPane.showMessageDialog(this, "Please select a user to update.");
      return;
    }

    int userId = (int) tableModel.getValueAt(row, 0);
    String name = nameField.getText();
    int age = Integer.parseInt(ageField.getText());
    String college = collegeField.getText();
    String hobby = hobbyField.getText();

    User updatedUser = new User(userId, name, age, college, hobby);
    User oldUser = new User();
    oldUser.setUserId(userId);

    DatabaseConnector.updateUser(oldUser, updatedUser);
    JOptionPane.showMessageDialog(this, "User updated successfully!");
    loadUserData();
  }

  private void deleteUser() {
    int row = userTable.getSelectedRow();
    if (row == -1) {
      JOptionPane.showMessageDialog(this, "Please select a user to delete.");
      return;
    }

    int userId = (int) tableModel.getValueAt(row, 0);
    User user = new User();
    user.setUserId(userId);

    DatabaseConnector.deleteUser(user);
    JOptionPane.showMessageDialog(this, "User deleted successfully!");
    loadUserData();
  }
}
