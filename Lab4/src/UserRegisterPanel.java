import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

public class UserRegisterPanel extends JPanel {
  private final MainFrame frame;
  private JTextField firstNameField, lastNameField, ageField, emailField;
  private JTextArea hobbyArea;
  private JComboBox<String> genderCombo;
  private JLabel photoLabel;
  private Image photo;
  private JSpinner dateSpinner;
  private JLabel errorLabel;

  public UserRegisterPanel(MainFrame frame) {
    this.frame = frame;
    setLayout(new BorderLayout(10, 10));
    setBackground(new Color(245, 245, 250));

    errorLabel = new JLabel(" ");
    errorLabel.setForeground(Color.RED);
    errorLabel.setHorizontalAlignment(JLabel.CENTER);

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
    formPanel.setBackground(Color.WHITE);
    add(formPanel, BorderLayout.CENTER);
    add(errorLabel, BorderLayout.NORTH);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Add Input Fields
    gbc.gridy = 0;
    addLabel("Last Name：", formPanel, gbc);
    firstNameField = createTextFieldWithHint("Please enter your last name");
    addComponent(firstNameField, formPanel, gbc);

    gbc.gridy++;
    addLabel("First Name：", formPanel, gbc);
    lastNameField = createTextFieldWithHint("Please enter your first name");
    addComponent(lastNameField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Age：", formPanel, gbc);
    ageField = createTextFieldWithHint("between 18 and 120");
    addComponent(ageField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Email：", formPanel, gbc);
    emailField = createTextFieldWithHint("example@domain.com");
    addComponent(emailField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Birthday：", formPanel, gbc);
    dateSpinner = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
    dateSpinner.setEditor(editor);
    addComponent(dateSpinner, formPanel, gbc);

    gbc.gridy++;
    addLabel("Gender：", formPanel, gbc);
    genderCombo = new JComboBox<>(new String[]{"Male", "Female"});
    addComponent(genderCombo, formPanel, gbc);

    gbc.gridy++;
    addLabel("Hobby：", formPanel, gbc);
    hobbyArea = new JTextArea(3, 20);
    hobbyArea.setLineWrap(true);
    JScrollPane hobbyScroll = new JScrollPane(hobbyArea);
    addComponent(hobbyScroll, formPanel, gbc);

    gbc.gridy++;
    addLabel("Image：", formPanel, gbc);
    JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton uploadBtn = new JButton("Select Image...");
    uploadBtn.addActionListener(e -> uploadPhoto());
    photoLabel = new JLabel();
    photoLabel.setPreferredSize(new Dimension(120, 120));
    photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    photoPanel.add(uploadBtn);
    photoPanel.add(photoLabel);
    addComponent(photoPanel, formPanel, gbc);

    // Submit Button
    gbc.gridy++;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    JButton submitBtn = new JButton("Submit");
    submitBtn.setPreferredSize(new Dimension(140, 40));
    submitBtn.addActionListener(e -> validateAndSubmit());
    formPanel.add(submitBtn, gbc);
  }

  private void addLabel(String text, JPanel panel, GridBagConstraints gbc) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Noto Sans", Font.BOLD, 14));
    gbc.gridx = 0;
    panel.add(label, gbc);
  }

  private void addComponent(Component comp, JPanel panel, GridBagConstraints gbc) {
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    panel.add(comp, gbc);
    gbc.weightx = 0.0;
  }

  private void uploadPhoto() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Image file", "jpg", "png"));
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedImage img = ImageIO.read(chooser.getSelectedFile());
        photo = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(photo));
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Image upload error", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private JTextField createTextFieldWithHint(String hint) {
    return new JTextField(20) {
      private String hintText = hint;
      private boolean showingHint = true;

      {
        setForeground(Color.GRAY);
        setText(hintText);
        addFocusListener(new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            // while getting focus, if it is showing hint now, turn it off
            if (showingHint) {
              setText("");
              setForeground(Color.BLACK);
              showingHint = false;
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            // if field not empty, show hint again
            if (getText().isEmpty()) {
              showingHint = true;
              setForeground(Color.GRAY);
              setText(hintText);
            }
          }
        });
      }

      @Override
      public String getText() {
        return showingHint ? "" : super.getText();
      }
    };
  }

  private void validateAndSubmit() {
    try {
      // clear the error before
      errorLabel.setForeground(Color.RED);

      // nonnull check
      if (firstNameField.getText().trim().isEmpty()) {
        throw new IllegalArgumentException("Last name cannot be empty");
      }
      if (lastNameField.getText().trim().isEmpty()) {
        throw new IllegalArgumentException("First name cannot be empty");
      }

      if (ageField.getText().trim().isEmpty()) {
        throw new IllegalArgumentException("Age cannot be empty");
      }
      // age check
      int age;
      try {
        age = Integer.parseInt(ageField.getText().trim());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Age must be a valid number");
      }
      if (age < 18 || age > 120) {
        throw new IllegalArgumentException("Age must be between 18 and 120");
      }

      if (emailField.getText().trim().isEmpty()) {
        throw new IllegalArgumentException("Email cannot be empty");
      }
      // email format check
      String email = emailField.getText().trim();
      if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
        throw new IllegalArgumentException("Incorrect email format");
      }

      // birthday check
      Date birthDate = (Date) dateSpinner.getValue();
      Date now = new Date();
      if (birthDate.after(now)) {
        throw new IllegalArgumentException("The date of birth cannot exceed the current date");
      }

      if (hobbyArea.getText().trim().isEmpty()) {
        throw new IllegalArgumentException("Hobby cannot be empty");
      }
      if (photo == null) {
        throw new IllegalArgumentException("Please upload image");
      }

      errorLabel.setForeground(new Color(0, 128, 0));
      errorLabel.setText("Register Success!");
      User user = new User();
      user.setFirstName(firstNameField.getText().trim());
      user.setLastName(lastNameField.getText().trim());
      user.setAge(Integer.parseInt(ageField.getText().trim()));
      user.setEmail(emailField.getText().trim());
      user.setHobby(hobbyArea.getText().trim());
      user.setGender((String) genderCombo.getSelectedItem());
      user.setBirthday((Date) dateSpinner.getValue());
      user.setPhoto(photo);

      frame.showDetails(user);
    } catch (IllegalArgumentException ex) {
      errorLabel.setText(ex.getMessage());
    }
  }
}