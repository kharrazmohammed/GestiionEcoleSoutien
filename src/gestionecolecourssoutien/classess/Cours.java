package gestionecolecourssoutien.classess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Cours extends JFrame {
    private JTextField txtCourseName, txtCourseId;
    private DefaultListModel<String> courseListModel;
    private JList<String> courseList;
    private ArrayList<CoursEntry> courses;
    private int courseIdCounter = 1;

    public Cours() {
        setTitle("Gestion des Cours");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        courses = new ArrayList<>();

        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter un Cours"));

        formPanel.add(new JLabel("Nom du Cours :"));
        txtCourseName = new JTextField();
        formPanel.add(txtCourseName);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.addActionListener(e -> addCourse());
        formPanel.add(btnAdd);

        add(formPanel, BorderLayout.NORTH);

        // Liste des cours
        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);
        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Cours"));
        add(scrollPane, BorderLayout.CENTER);

        // Zone de suppression
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        bottomPanel.add(new JLabel("ID Cours à supprimer :"));
        txtCourseId = new JTextField();
        bottomPanel.add(txtCourseId);

        JButton btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(e -> deleteCourse());
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Ajouter un cours
    private void addCourse() {
        String courseName = txtCourseName.getText().trim();

        if (courseName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer le nom du cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CoursEntry newCourse = new CoursEntry(courseIdCounter, courseName);
        courses.add(newCourse);
        courseListModel.addElement(newCourse.toString());
        courseIdCounter++;

        txtCourseName.setText("");
        JOptionPane.showMessageDialog(this, "Cours ajouté avec succès !");
    }

    // Supprimer un cours
    private void deleteCourse() {
        try {
            int id = Integer.parseInt(txtCourseId.getText().trim());
            courses.removeIf(course -> course.id == id);
            refreshCourseList();
            txtCourseId.setText("");
            JOptionPane.showMessageDialog(this, "Cours supprimé !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Rafraîchir la liste après suppression
    private void refreshCourseList() {
        courseListModel.clear();
        for (CoursEntry c : courses) {
            courseListModel.addElement(c.toString());
        }
    }

    private static class CoursEntry {
        int id;
        String name;

        CoursEntry(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " | " + name;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cours().setVisible(true));
    }
}