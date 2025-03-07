package gestionecolecourssoutien.classess;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Charge_et_stats extends JFrame {
    private JTextField txtDescription, txtAmount, txtChargeId;
    private DefaultListModel<String> chargeListModel;
    private JList<String> chargeList;
    private ArrayList<ChargeEntry> charges;
    private int chargeIdCounter = 1;

    public Charge_et_stats() {
        setTitle("Gestion des Charges");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        charges = new ArrayList<>();

        // Formulaire d'ajout de charge
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter une Charge"));

        formPanel.add(new JLabel("Description :"));
        txtDescription = new JTextField();
        formPanel.add(txtDescription);

        formPanel.add(new JLabel("Montant (DH) :"));
        txtAmount = new JTextField();
        formPanel.add(txtAmount);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(e -> addCharge());
        formPanel.add(btnAdd);

        add(formPanel, BorderLayout.NORTH);

        // Liste des charges
        chargeListModel = new DefaultListModel<>();
        chargeList = new JList<>(chargeListModel);
        JScrollPane scrollPane = new JScrollPane(chargeList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Charges"));
        add(scrollPane, BorderLayout.CENTER);

        // Zone de suppression et statistiques
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        bottomPanel.add(new JLabel("ID Charge à supprimer :"));
        txtChargeId = new JTextField();
        bottomPanel.add(txtChargeId);

        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(e -> deleteCharge(txtChargeId.getText().trim()));
        bottomPanel.add(btnDelete);

        JButton btnTotal = new JButton("Total Charges");
        btnTotal.addActionListener(e -> calculateTotal());
        bottomPanel.add(btnTotal);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Ajouter une charge
    private void addCharge() {
        String description = txtDescription.getText().trim();
        String amountText = txtAmount.getText().trim();

        if (description.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            ChargeEntry newCharge = new ChargeEntry(chargeIdCounter, description, amount);
            charges.add(newCharge);
            chargeListModel.addElement(newCharge.toString());
            chargeIdCounter++;

            txtDescription.setText("");
            txtAmount.setText("");
            JOptionPane.showMessageDialog(this, "Charge ajoutée avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Montant invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Supprimer une charge
    private void deleteCharge(String chargeIdText) {
        try {
            int id = Integer.parseInt(chargeIdText);
            charges.removeIf(charge -> charge.id == id);
            refreshChargeList();
            JOptionPane.showMessageDialog(this, "Charge supprimée !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Calculer le total des charges
    private void calculateTotal() {
        double total = charges.stream().mapToDouble(charge -> charge.amount).sum();
        JOptionPane.showMessageDialog(this, "Total des charges : " + total + "€");
    }

    // Rafraîchir la liste après suppression
    private void refreshChargeList() {
        chargeListModel.clear();
        for (ChargeEntry c : charges) {
            chargeListModel.addElement(c.toString());
        }
    }

    private static class ChargeEntry {
        int id;
        String description;
        double amount;

        ChargeEntry(int id, String description, double amount) {
            this.id = id;
            this.description = description;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return id + " | " + description + " | " + amount + "€";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Charge_et_stats().setVisible(true));
    }
}