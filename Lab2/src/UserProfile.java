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
  private JRadioButton maleRadio, femaleRadio;
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

    maleRadio = new JRadioButton("Male");
    femaleRadio = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();
    genderGroup.add(maleRadio);
    genderGroup.add(femaleRadio);

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
    add(maleRadio);
    add(new JLabel(""));
    add(femaleRadio);

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
    String profile = getProfile();

    // use panel to display the profile and photo
    JPanel panel = new JPanel(new BorderLayout());
    JLabel profileLabel = new JLabel("<html>" + profile.replace("\n", "<br>") + "</html>");
    panel.add(profileLabel, BorderLayout.CENTER);

    if (userPhoto != null) {
      JLabel photoLabel = new JLabel(userPhoto);
      panel.add(photoLabel, BorderLayout.WEST);
    }

    // show the profile in a message dialog
    JOptionPane.showMessageDialog(this, panel, "User Profile", JOptionPane.INFORMATION_MESSAGE);
  }

  private String getProfile() {
    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String ageText = ageField.getText().trim();
    String phone = phoneField.getText().trim();
    String email = emailField.getText().trim();
    String gender = maleRadio.isSelected() ? "Male" : "Female";

    // create profile information
    return String.format(
            "Profile Created!\nFirst Name: %s\nLast Name: %s\nAge: %s\nPhone: %s\nEmail: %s\nGender: %s",
            firstName, lastName, ageText, phone, email, gender
    );
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      UserProfile app = new UserProfile();
      app.setVisible(true);
    });
  }
}
