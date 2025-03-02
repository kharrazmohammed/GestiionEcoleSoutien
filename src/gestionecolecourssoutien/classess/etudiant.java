package gestionecolecourssoutien.classess;
import java.sql.*;
import javax.sql.*;
import java.util.Date;
import java.sql.ResultSet;

public class etudiant {
    String nometd,prenometd,numtelephone;
    Date datenaissance;
    public etudiant(String nometd, String prenometd, String numtelephone, Date datenaissance) {
        this.nometd = nometd;
        this.prenometd = prenometd;
        this.numtelephone = numtelephone;
        this.datenaissance = datenaissance;
    }
    public etudiant() {
    }

    public void supprimer_etudiant(int id){
        Connection con=DB.getConnection();
        //String sqlquery = "DELETE  FROM eleve WHERE id_eleve = ?";

    }


    public boolean inscrire_etudiant() {
        String nom_etudiant = this.nometd;
        String prenom_etudiant = this.prenometd;
        String numtelephone_etudiant = this.numtelephone;
        Date date_naissance_etudiant = this.datenaissance;
        Connection dba = DB.getConnection();
        PreparedStatement pst = null;
        boolean isInserted = false;
        String sql = "INSERT INTO eleve(nom, prenom, date_naissance, contact) VALUES (?, ?, ?, ?)";

        try {
            pst = dba.prepareStatement(sql);
            pst.setString(1, nom_etudiant);
            pst.setString(2, prenom_etudiant);
            pst.setTimestamp(3, (date_naissance_etudiant != null) ? new Timestamp(date_naissance_etudiant.getTime()) : null);
            pst.setString(4, numtelephone_etudiant); // Correction : maintenant c'est bien le 4e paramètre

            int rowsAffected = pst.executeUpdate();
            isInserted = rowsAffected > 0;
            System.out.println("Étudiant ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'étudiant !");
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (dba != null) dba.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isInserted;
    }
    public etudiant recuper_etudiant(int id) {
        String sql = "SELECT * FROM eleve WHERE id_eleve = ?";
        Connection db = DB.getConnection();
        PreparedStatement pst = null;
        etudiant etudiant = null;
        ResultSet rs = null;
        try {
            pst = db.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                etudiant = new etudiant(rs.getString("nom"),rs.getString("prenom"),rs.getString("contact"),rs.getDate("date_naissance"));
            }
            return etudiant;
        }catch (SQLException e){
            return null;

        }

    }
    public String getNometd() {
        return nometd;
    }
    public String getPrenometd() {
        return prenometd;
    }
    public String getNumtelephone() {
        return numtelephone;
    }
    public Date getDatenaissance() {
        return datenaissance;
    }
    public void setNometd(String nometd) {
        this.nometd = nometd;
    }
    public void setPrenometd(String prenometd) {
        this.prenometd = prenometd;
    }
    public void setNumtelephone(String numtelephone) {
        this.numtelephone = numtelephone;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    @Override
    public String toString() {
        return "etudiant{" +
                "nometd='" + nometd + '\'' +
                ", prenometd='" + prenometd + '\'' +
                ", numtelephone='" + numtelephone + '\'' +
                ", datenaissance=" + datenaissance +
                '}';
    }
    public static void main(String[] args) {
        etudiant etd = new etudiant();
        etudiant recup = etd.recuper_etudiant(1);

        if (recup != null) {
            System.out.println(recup.toString());
        } else {
            System.out.println("Aucun étudiant trouvé avec cet ID.");
        }
    }



}
