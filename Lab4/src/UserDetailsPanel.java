import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

public class UserDetailsPanel extends JPanel {
  private JTextField firstNameField, lastNameField, ageField, emailField, genderField, dateField;
  private JTextArea hobbyArea;
  private JLabel photoLabel;

  public UserDetailsPanel() {
    setLayout(new BorderLayout(10, 10));
    setBackground(new Color(245, 245, 250));

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
    formPanel.setBackground(Color.WHITE);
    add(formPanel, BorderLayout.CENTER);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // same layer structure with register page
    gbc.gridy = 0;
    addLabel("Last name：", formPanel, gbc);
    firstNameField = createDetailTextField();
    addComponent(firstNameField, formPanel, gbc);

    gbc.gridy++;
    addLabel("First Name：", formPanel, gbc);
    lastNameField = createDetailTextField();
    addComponent(lastNameField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Age：", formPanel, gbc);
    ageField = createDetailTextField();
    addComponent(ageField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Email：", formPanel, gbc);
    emailField = createDetailTextField();
    addComponent(emailField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Birthday：", formPanel, gbc);
    dateField = createDetailTextField();
    addComponent(dateField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Gender：", formPanel, gbc);
    genderField = createDetailTextField();
    addComponent(genderField, formPanel, gbc);

    gbc.gridy++;
    addLabel("Hobby：", formPanel, gbc);
    hobbyArea = new JTextArea(3, 20);
    hobbyArea.setEditable(false);
    hobbyArea.setLineWrap(true);
    addComponent(new JScrollPane(hobbyArea), formPanel, gbc);

    gbc.gridy++;
    addLabel("Image：", formPanel, gbc);

    // special photo display container
    JPanel photoContainer = new JPanel(new GridBagLayout());
    photoContainer.setPreferredSize(new Dimension(120, 120));
    photoContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    photoLabel = new JLabel();
    photoLabel.setHorizontalAlignment(JLabel.CENTER);
    photoLabel.setVerticalAlignment(JLabel.CENTER);

    // put image into container
    photoContainer.add(photoLabel);

    // adjust layout
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.WEST;
    addComponent(photoContainer, formPanel, gbc);
  }

  // label styles
  private void addLabel(String text, JPanel panel, GridBagConstraints gbc) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Noto Sans", Font.BOLD, 14));
    gbc.gridx = 0;
    panel.add(label, gbc);
  }

  // label add method
  private void addComponent(Component comp, JPanel panel, GridBagConstraints gbc) {
    gbc.gridx = 1;
    gbc.weightx = 1.0;
    panel.add(comp, gbc);
    gbc.weightx = 0.0;
  }

  // non-editable field
  private JTextField createDetailTextField() {
    JTextField field = new JTextField(20);
    field.setEditable(false);
    field.setBackground(new Color(240, 240, 240));
    return field;
  }

  public void displayUser(User user) {
    firstNameField.setText(user.getFirstName());
    lastNameField.setText(user.getLastName());
    ageField.setText(String.valueOf(user.getAge()));
    emailField.setText(user.getEmail());
    hobbyArea.setText(user.getHobby());
    genderField.setText(user.getGender());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    dateField.setText(sdf.format(user.getBirthday()));

    if (user.getPhoto() != null) {
      Image scaledPhoto = user.getPhoto().getScaledInstance(
              120, 120, Image.SCALE_SMOOTH);
      photoLabel.setIcon(new ImageIcon(scaledPhoto));
    } else {
      photoLabel.setIcon(null);
    }
  }
}