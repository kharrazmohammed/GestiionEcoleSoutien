package gestionecolecourssoutien;


import com.formdev.flatlaf.themes.FlatMacLightLaf;
import gestionecolecourssoutien.views.Login;

import javax.swing.*;
import java.awt.*;

public class app extends JFrame {

    private JProgressBar progressBar;
    private JLabel logoLabel;
    private JLabel messageLabel;

    public app() {
        initComponents();
        runProgressBar();
    }

    private void initComponents() {
        setTitle("Chargement...");
        setSize(600, 400);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("images/ENC.jpg"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo), SwingConstants.CENTER);

        messageLabel = new JLabel("Bonjour chez École Nouvelle Chance", SwingConstants.CENTER);
        messageLabel.setFont(new Font("poppins", Font.BOLD, 24));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(400, 40));
        progressBar.setFont(new Font("poppins", Font.BOLD, 22));
        add(logoLabel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.SOUTH);
    }

    private void runProgressBar() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(30);
                    progressBar.setValue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dispose();
            new Login().setVisible(true);
        });
        thread.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(() -> new app().setVisible(true));
    }
}