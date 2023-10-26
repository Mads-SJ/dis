package opg2.lowTransparancy;
import java.sql.*;
import java.util.*;
public class ViaTwoParts {
    public static void main(String[] args) {
    try {
        ArrayList<Person> liste = new ArrayList<Person>();
// læser tabellen Personjyl via native-driver
        Connection minConnection;
        minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;" +
                "databaseName=ddba;user=sa;password=msjdbms1998;");
        String sql= "select * from personadr";
        Statement stmt = minConnection.createStatement();
        ResultSet res=stmt.executeQuery(sql);
        while (res.next()) {
            Person p = new Person();
            p.setNavn(res.getString("navn"));
            p.setCpr(res.getString("cpr"));
            p.setByNavn(res.getString("bynavn"));
            liste.add(p);
        };

// l�ser tabellen Personoeer via native-driver
        Connection minCon2;
        minCon2 = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;" +
                "databaseName=ddbb;user=sa;password=msjdbms1998;");
        String sql2= "select * from personloen";
        Statement stmt2 = minCon2.createStatement();
        ResultSet res2=stmt2.executeQuery(sql2);
        int i=0;
        while (res2.next()) {
            Person p = liste.get(i);
            p.setLoen(res2.getInt("loen"));
            p.setSkatteProcent(res2.getInt("skatteprocent"));
            i++;
        }

// udskriver indholdet af de to tabeller
        for (int j=0;j<liste.size();j++) {
            Person s =liste.get(j);
            System.out.println(s.getNavn()+" "+s.getCpr()+" "+s.getByNavn()+" "+s.getLoen()+" "+s.getSkatteProcent());
        }
    }
		catch (Exception e) {
        System.out.println("fejl:  "+e.getMessage());
    }
}
}
