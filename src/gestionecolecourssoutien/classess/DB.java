package gestionecolecourssoutien.classess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    // Informations de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_centre";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Constructeur privé pour empêcher l'instanciation directe
    private DB() {}

    // Méthode pour obtenir une connexion unique (Singleton)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger le driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Établir la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion réussie à la base de données !");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Erreur : Driver MySQL introuvable !");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Erreur de connexion à la base de données !");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Réinitialiser la connexion
                System.out.println("✅ Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la fermeture de la connexion !");
                e.printStackTrace();
            }
        }
    }


}
