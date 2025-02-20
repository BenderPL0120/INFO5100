package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;

public class MainController {
  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField emailField;

  @FXML
  private void handleSubmit() {
    try {
      // input validation
      User.validate(
              nameField.getText(),
              ageField.getText(),
              emailField.getText()
      );

      // create user object
      User user = new User();
      user.setName(nameField.getText());
      user.setAge(Integer.parseInt(ageField.getText()));
      user.setEmail(emailField.getText());

      // show success message
      showAlert("Registration Success",
              "User Info：\n" +
                      "Name: " + user.getName() + "\n" +
                      "Age: " + user.getAge() + "\n" +
                      "Email: " + user.getEmail());

    } catch (IllegalArgumentException e) {
      showAlert("Input Error:", e.getMessage());
    }
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
