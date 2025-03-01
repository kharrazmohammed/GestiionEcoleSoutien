import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql111.infinityfree.com:3306/if0_38425047";
        String user = "if0_38425047";
        String password = "egMo2K9DEFETR";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
