package gestionecolecourssoutien.views;

import gestionecolecourssoutien.classess.Charge_et_stats;
import gestionecolecourssoutien.classess.Cours;
import gestionecolecourssoutien.classess.EtudiantManagement;
import gestionecolecourssoutien.classess.ProfessorManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JLabel lab = new JLabel("Bienvenue au tableau de bord");

    Dashboard() {
        setSize(781, 645);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.add(lab);
        add(centerPanel, BorderLayout.CENTER);

        // Right Sidebar Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(7, 1, 10, 10)); // 7 Buttons
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Button Labels
        String[] buttonLabels = {
                "Gérer les Étudiants",
                "Gérer les Professeurs",
                "Gérer les Cours",
                "Gérer les Examens",
                "Voir l'Assiduité",
                "Finances & Paiements",
                "Se Déconnecter"
        };

        // Create Buttons & Add Listeners
        for (String text : buttonLabels) {
            JButton btn = new JButton(text);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.addActionListener(new ButtonClickListener(text)); // Attach event handler
            rightPanel.add(btn);
        }

        // Add the sidebar to the right
        add(rightPanel, BorderLayout.EAST);
    }

    // ActionListener for Button Handling
    private class ButtonClickListener implements ActionListener {
        private String buttonText;

        public ButtonClickListener(String text) {
            this.buttonText = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonText) {
                case "Gérer les Étudiants":
                    JOptionPane.showMessageDialog(null, "Ouverture de la gestion des étudiants...");
                    dispose();
                    EtudiantManagement em = new EtudiantManagement();
                    em.setVisible(true);
                    break;
                case "Gérer les Professeurs":
                    JOptionPane.showMessageDialog(null, "Ouverture de la gestion des professeurs...");
                    dispose();
                    ProfessorManagement pm = new ProfessorManagement();
                    pm.setVisible(true);
                    break;
                case "Gérer les Cours":
                    JOptionPane.showMessageDialog(null, "Ouverture de la gestion des cours...");
                    dispose();
                    Cours cm = new Cours();
                    cm.setVisible(true);
                    break;
                case "Gérer les Examens":
                    JOptionPane.showMessageDialog(null, "Ouverture de la gestion des examens...");
                    break;
                case "Voir la Présence":
                    JOptionPane.showMessageDialog(null, "Ouverture du suivi de la présence...");
                    break;
                case "Finances & Paiements":
                    JOptionPane.showMessageDialog(null, "Ouverture du module de paiements...");
                    dispose();
                    Charge_et_stats com = new Charge_et_stats();
                    com.setVisible(true);
                    break;
                case "Se Déconnecter":
                    int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous déconnecter ?",
                            "Déconnexion", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        dispose(); // Close dashboard
                        new Login().setVisible(true); // Reopen login screen (Assuming you have a LoginFrame class)
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}
