package gestionecolecourssoutien.classess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfessorManagement extends JFrame {
    private JTextField txtUsername, txtMail, txtSpeciality, txtId, txtCourse;
    private DefaultListModel<String> professorListModel;
    private JList<String> professorList;
    private ArrayList<Professor> professors;
    private Map<Integer, String> courses; // Associe l'ID du professeur à un cours

    public ProfessorManagement() {
        setTitle("Gestion des Professeurs");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        professors = new ArrayList<>();
        courses = new HashMap<>();

        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter un Professeur"));

        formPanel.add(new JLabel("Nom :"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Email :"));
        txtMail = new JTextField();
        formPanel.add(txtMail);

        formPanel.add(new JLabel("Spécialité :"));
        txtSpeciality = new JTextField();
        formPanel.add(txtSpeciality);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(e -> addProfessor());
        formPanel.add(btnAdd);

        add(formPanel, BorderLayout.NORTH);

        // Liste des professeurs
        professorListModel = new DefaultListModel<>();
        professorList = new JList<>(professorListModel);
        JScrollPane scrollPane = new JScrollPane(professorList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Professeurs"));

        add(scrollPane, BorderLayout.CENTER);

        // Zone de suppression et d'affectation
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        bottomPanel.add(new JLabel("ID Prof à supprimer :"));
        txtId = new JTextField();
        bottomPanel.add(txtId);

        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(e -> deleteProfessor());
        bottomPanel.add(btnDelete);

        bottomPanel.add(new JLabel("Cours à affecter :"));
        txtCourse = new JTextField();
        bottomPanel.add(txtCourse);

        JButton btnAssign = new JButton("Affecter Cours");
        btnAssign.addActionListener(e -> assignCourse());
        bottomPanel.add(btnAssign);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Ajouter un professeur
    private void addProfessor() {
        String name = txtUsername.getText().trim();
        String mail = txtMail.getText().trim();
        String speciality = txtSpeciality.getText().trim();

        if (name.isEmpty() || mail.isEmpty() || speciality.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = professors.size() + 1; // ID auto-incrémenté
        Professor newProf = new Professor(name, mail, speciality, id);
        professors.add(newProf);
        professorListModel.addElement(newProf.toString());

        // Nettoyer les champs
        txtUsername.setText("");
        txtMail.setText("");
        txtSpeciality.setText("");

        JOptionPane.showMessageDialog(this, "Professeur ajouté avec succès !");
    }

    // Supprimer un professeur
    private void deleteProfessor() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            boolean removed = professors.removeIf(prof -> prof.getIdProf() == id);
            courses.remove(id); // Supprimer les cours affectés au professeur supprimé

            if (removed) {
                refreshProfessorList();
                JOptionPane.showMessageDialog(this, "Professeur supprimé !");
            } else {
                JOptionPane.showMessageDialog(this, "Aucun professeur trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            txtId.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Affecter un cours
    private void assignCourse() {
        String course = txtCourse.getText().trim();
        int index = professorList.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un professeur dans la liste !");
            return;
        }

        Professor selectedProf = professors.get(index);
        courses.put(selectedProf.getIdProf(), course);

        JOptionPane.showMessageDialog(this, "Cours '" + course + "' affecté à " + selectedProf.getUsername());

        txtCourse.setText("");
    }

    // Rafraîchir la liste après suppression
    private void refreshProfessorList() {
        professorListModel.clear();
        for (Professor p : professors) {
            professorListModel.addElement(p.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProfessorManagement().setVisible(true);
        });
    }
}

// Classe Professor
class Professor {
    private String username;
    private String mail;
    private final int idProf;
    private String speciality;

    public Professor(String username, String mail, String speciality, int idProf) {
        this.username = username;
        this.mail = mail;
        this.speciality = speciality;
        this.idProf = idProf;
    }

    public int getIdProf() {
        return idProf;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return idProf + " | " + username + " | " + speciality + " | " + mail;
    }
}
