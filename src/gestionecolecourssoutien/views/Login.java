package gestionecolecourssoutien.views;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Load and set the logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("../images/ENC.jpg")); // Ensure logo.png is in src/resources/
        Image scaledLogo = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo));
        add(logoLabel, gbc);

        gbc.gridy++;
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(440, 50));
        TitledBorder brdusername = BorderFactory.createTitledBorder("Username");
        brdusername.setTitleFont(new Font("Poppins", Font.BOLD, 15));
        usernameField.setBorder(brdusername);
        usernameField.setFont(new Font("poppins", Font.BOLD, 15));

        add(usernameField, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(440, 50));
        TitledBorder brdpass = BorderFactory.createTitledBorder("Password");
        brdpass.setTitleFont(new Font("Poppins", Font.BOLD, 15));
        passwordField.setBorder(brdpass);
        passwordField.setFont(new Font("poppins", Font.BOLD, 15));
        add(passwordField, gbc);

        gbc.gridy++;
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> onLogin());
        loginButton.setPreferredSize(new Dimension(440, 50));
        loginButton.setFont(new Font("Poppins", Font.BOLD, 15));
        loginButton.setBackground(new Color(250, 57, 67));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        loginButton.setFocusPainted(false);
        add(loginButton, gbc);

    }

    private void onLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                            "Veuillez remplir tous les champs.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( username.trim().equals("Admin") || password.trim().equals("Admin") ) {


            dispose();
            Dashboard dash = new Dashboard();
            dash.setVisible(true);
        }

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


