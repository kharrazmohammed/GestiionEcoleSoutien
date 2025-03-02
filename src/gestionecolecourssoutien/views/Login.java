package gestionecolecourssoutien.views;




import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel logoLabel;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Admin Login");
        setSize(781, 645);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Load and set the logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("../images/ENC.jpg")); // Ensure logo.png is in src/resources/
        Image scaledLogo = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo));
        add(logoLabel, gbc);

        gbc.gridy++;
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(440, 50));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        add(usernameField, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(440, 50));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        add(passwordField, gbc);

        gbc.gridy++;
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> onLogin());
        loginButton.setPreferredSize(new Dimension(440, 50));
        add(loginButton, gbc);
    }

    private void onLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // TODO: Add authentication logic
        JOptionPane.showMessageDialog(this, "Login clicked with Username: " + username);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}


