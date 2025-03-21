import ui.*;

import javax.swing.*;

public class MainFrame extends JFrame {
  public MainFrame() {
    setTitle("User Management");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTabbedPane tabbedPane = new JTabbedPane();
    FormJPanel formPanel = new FormJPanel();
    ViewJPanel viewPanel = new ViewJPanel();

    tabbedPane.addTab("Add User", formPanel);
    tabbedPane.addTab("View Users", viewPanel);

    // refresh while switch form formPanel to viewPanel
    tabbedPane.addChangeListener(e -> {
      if (tabbedPane.getSelectedComponent() == viewPanel) {
        viewPanel.loadUserData();
      }
    });

    add(tabbedPane);
    setVisible(true);
  }

  public static void main(String[] args) {
    new MainFrame();
  }
}
