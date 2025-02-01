import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class UserProfile extends JFrame {
  private JPanel board;
  private JTextField firstNameField, lastNameField, ageField, phoneField, emailField;
  private JComboBox<String> genderComboBox;
  private JTextArea hobbiesArea;
  private JLabel messageLabel, imgLabel;
  private ImageIcon userPhoto;

  private static final Logger logger = Logger.getLogger(UserProfile.class.getName()); // better log

  public UserProfile() {
    setTitle("User Profile Creation");
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(9, 2));

    // init components
    firstNameField = new JTextField();
    lastNameField = new JTextField();
    ageField = new JTextField();
    phoneField = new JTextField();
    emailField = new JTextField();

    String[] genders = {"Male", "Female"};
    genderComboBox = new JComboBox<>(genders);

    hobbiesArea = new JTextArea(3, 20);
    JScrollPane hobbiesScrollPane = new JScrollPane(hobbiesArea);

    JButton submitButton = new JButton("Submit");
    JButton uploadPhotoButton = new JButton("Upload Photo");
    messageLabel = new JLabel("");
    imgLabel = new JLabel("No image given");

    // add components to the frame
    add(new JLabel("First Name:"));
    add(firstNameField);
    add(new JLabel("Last Name:"));
    add(lastNameField);
    add(new JLabel("Age:"));
    add(ageField);
    add(new JLabel("Phone Number:"));
    add(phoneField);
    add(new JLabel("Email:"));
    add(emailField);
    add(new JLabel("Gender:"));
    add(genderComboBox);
    add(new JLabel("Hobbies:"));
    add(hobbiesScrollPane);

    add(uploadPhotoButton);
    add(imgLabel);
    add(submitButton);
    add(messageLabel);

    // add action listener to upload photo button
    uploadPhotoButton.addActionListener(e ->
            uploadPhoto()
    );

    // add action listener to submit button
    submitButton.addActionListener(e -> {
      if (validateInputs()) {
        displayProfile();
      }
    });
  }

  private void uploadPhoto() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      try {
        File selectedFile = fileChooser.getSelectedFile();
        BufferedImage img = ImageIO.read(selectedFile);
        if (img != null) {
          // Resize the image
          Image scaledImage = img.getScaledInstance(60, 80, Image.SCALE_SMOOTH);
          userPhoto = new ImageIcon(scaledImage);
          imgLabel.setText(selectedFile.getName()); // display the upload photo name
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Please upload a valid image file.",
                "Error - Incorrect Image", JOptionPane.ERROR_MESSAGE);
        logger.log(Level.SEVERE, "Error uploading photo", ex);  // log the exception
      }
    }
  }

  private boolean validateInputs() {
    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String ageText = ageField.getText().trim();
    String phone = phoneField.getText().trim();
    String email = emailField.getText().trim();

    // validate inputs
    if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || phone.isEmpty() || email.isEmpty()) {
      messageLabel.setText("Error: All fields are required!");
      return false;
    }

    if (!Pattern.matches("[0-9]+", ageText)) {
      messageLabel.setText("Error: Age must be a number!");
      return false;
    }

    int age = Integer.parseInt(ageText);
    if (age < 1 || age > 120) {
      messageLabel.setText("Error: Age must be between 1 and 120!");
      return false;
    }

    if (!Pattern.matches("[0-9]{10}", phone)) {
      messageLabel.setText("Error: Phone number must be 10 digits!");
      return false;
    }

    if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
      messageLabel.setText("Error: Invalid email format!");
      return false;
    }

    return true; // validation pass
  }

  private void displayProfile() {
    // save profile into attributes of user class
    User user = new User();
    user.setFirstName(firstNameField.getText().trim());
    user.setLastName(lastNameField.getText().trim());
    user.setAge(Integer.parseInt(ageField.getText().trim()));
    user.setPhoneNumber(phoneField.getText().trim());
    user.setEmail(emailField.getText().trim());
    user.setGender((String) genderComboBox.getSelectedItem());
    user.setHobbies(hobbiesArea.getText().trim());
    user.setPhoto(userPhoto);

    // use panel to display the profile and photo
    JPanel panel = new JPanel(new BorderLayout());
    // use toString() to display
    JLabel profileLabel = new JLabel("<html>" + user.toString().replace("\n", "<br>") + "</html>");
    panel.add(profileLabel, BorderLayout.CENTER);

    if (user.getPhoto() != null) {
      JLabel photoLabel = new JLabel(user.getPhoto());
      panel.add(photoLabel, BorderLayout.WEST);
    }

    // show the profile by using panel
    JOptionPane.showMessageDialog(this, panel, "User Profile", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      UserProfile app = new UserProfile();
      app.setVisible(true);
    });
  }

}


