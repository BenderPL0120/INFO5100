import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private CardLayout cardLayout = new CardLayout();
  private JPanel cardPanel = new JPanel(cardLayout);
  private UserRegisterPanel registerPanel;
  private UserDetailsPanel detailsPanel;

  public MainFrame() {
    setTitle("User Register System");
    setSize(1000, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Navigation Layout
    JPanel leftPanel = new JPanel(new GridLayout(3, 1, 10, 10));
    JButton regBtn = new JButton("Register");
    JButton viewBtn = new JButton("View");
    leftPanel.add(regBtn);
    leftPanel.add(viewBtn);

    // Content Layout
    registerPanel = new UserRegisterPanel(this);
    detailsPanel = new UserDetailsPanel();
    cardPanel.add(registerPanel, "REGISTER");
    cardPanel.add(detailsPanel, "DETAIL");

    // Implement SplitPanel with CardLayout
    JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, cardPanel);
    splitPanel.setDividerLocation(200);
    add(splitPanel);

    // Button Event
    regBtn.addActionListener(e -> cardLayout.show(cardPanel, "REGISTER"));
    viewBtn.addActionListener(e -> cardLayout.show(cardPanel, "DETAIL"));
  }

  public void showDetails(User user) {
    detailsPanel.displayUser(user);
    cardLayout.show(cardPanel, "DETAIL");
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
  }
}