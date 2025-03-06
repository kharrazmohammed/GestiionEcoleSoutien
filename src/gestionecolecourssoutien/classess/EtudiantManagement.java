package gestionecolecourssoutien.classess;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EtudiantManagement extends JFrame {
    private JTextField txtName, txtEmail, txtLevel, txtId, txtCourse;
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;
    private ArrayList<Etudiant> students;
    private Map<Integer, String> courses; // Associe l'ID de l'élève à un cours

    public EtudiantManagement() {
        setTitle("Gestion des Élèves");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        students = new ArrayList<>();
        courses = new HashMap<>();

        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter un Élève"));

        formPanel.add(new JLabel("Nom :"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Email :"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Niveau :"));
        txtLevel = new JTextField();
        formPanel.add(txtLevel);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(e -> addStudent());
        formPanel.add(btnAdd);

        add(formPanel, BorderLayout.NORTH);

        // Liste des élèves
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Élèves"));

        add(scrollPane, BorderLayout.CENTER);

        // Zone de suppression et d'affectation
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        bottomPanel.add(new JLabel("ID Élève à supprimer :"));
        txtId = new JTextField();
        bottomPanel.add(txtId);

        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(e -> deleteStudent());
        bottomPanel.add(btnDelete);

        bottomPanel.add(new JLabel("Cours à affecter :"));
        txtCourse = new JTextField();
        bottomPanel.add(txtCourse);

        JButton btnAssign = new JButton("Affecter Cours");
        btnAssign.addActionListener(e -> assignCourse());
        bottomPanel.add(btnAssign);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Ajouter un élève
    private void addStudent() {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String level = txtLevel.getText().trim();

        if (name.isEmpty() || email.isEmpty() || level.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = students.size() + 1; // ID auto-incrémenté
        Etudiant newStudent = new Etudiant(name, email, level, id);
        students.add(newStudent);
        studentListModel.addElement(newStudent.toString());

        txtName.setText("");
        txtEmail.setText("");
        txtLevel.setText("");

        JOptionPane.showMessageDialog(this, "Élève ajouté avec succès !");
    }

    // Supprimer un élève
    private void deleteStudent() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            boolean removed = students.removeIf(student -> student.getIdStudent() == id);
            courses.remove(id);

            if (removed) {
                refreshStudentList();
                JOptionPane.showMessageDialog(this, "Élève supprimé !");
            } else {
                JOptionPane.showMessageDialog(this, "Aucun élève trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            txtId.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Affecter un cours
    private void assignCourse() {
        String course = txtCourse.getText().trim();
        int index = studentList.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un élève dans la liste !");
            return;
        }

        Etudiant selectedStudent = students.get(index);
        courses.put(selectedStudent.getIdStudent(), course);

        JOptionPane.showMessageDialog(this, "Cours '" + course + "' affecté à " + selectedStudent.getName());

        txtCourse.setText("");
    }

    // Rafraîchir la liste après suppression
    private void refreshStudentList() {
        studentListModel.clear();
        for (Etudiant s : students) {
            studentListModel.addElement(s.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EtudiantManagement().setVisible(true);
        });
    }
}

// Classe Student
class Etudiant {
    private String name;
    private String email;
    private final int idStudent;
    private String level;

    public Etudiant(String name, String email, String level, int idStudent) {
        this.name = name;
        this.email = email;
        this.level = level;
        this.idStudent = idStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return idStudent + " | " + name + " | " + level + " | " + email;
    }
}
