package gestionecolecourssoutien.classess;
import java.security.SecureRandom;

public class professor {

    private String username;
    private String mail;
    private final String id_prof;
    private String speciality;

    public String generateId(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder str = new StringBuilder(12);
        for(int i = 0;i<12;i++){
            int j = (new SecureRandom()).nextInt(12);
            str.append(CHARACTERS.charAt(j));
        }
        return str.toString();
    }
    professor(String nm, String ml, String spec){
        username = nm;
        mail = ml;
        speciality = spec;
        id_prof = generateId();
    }

    @Override
    public String toString() {
        return this.username + " | "+this.speciality+" | "+this.mail+" | "+this.id_prof;
    }

    public static void main(String[] args) {
        professor p = new professor("ali","ali@ali","cs");
        System.out.println(p.toString());
    }

}
