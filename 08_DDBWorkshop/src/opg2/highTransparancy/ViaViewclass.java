package opg2.highTransparancy;
import java.sql.*;
import java.util.*;
import opg4.lowTransparancy.Person;

public class ViaViewclass {
    public static void main(String[] args) {
        try {
            ArrayList<Person> liste = new ArrayList<Person>();
//	 læser viewet person via native SQL-Server driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;" +
                    "databaseName=ddba;user=sa;password=msjdbms1998;");
            String sql= "select * from Person";
            Statement stmt = minConnection.createStatement();
            ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {
                Person p = new Person();
                p.setNavn(res.getString("navn"));
                p.setCpr(res.getString("cpr"));
                p.setByNavn(res.getString("bynavn"));
                p.setLoen(res.getInt("loen"));
                p.setSkatteProcent(res.getInt("skatteprocent"));
                liste.add(p);
            };
//	 udskriver indholdet af de to tabeller
            for (int i=0;i<liste.size();i++) {
                Person s =liste.get(i);
                System.out.println(s.getNavn()+" "+s.getCpr()+" "+s.getByNavn()+" "+s.getLoen()+" "+s.getSkatteProcent());
            }
        }
        catch (Exception e) {
            System.out.println("fejl:  "+e.getMessage());
        }
    }
}
