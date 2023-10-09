import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DatabaseConnection {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        try {
            System.out.println("Program startet");
            String server="localhost\\SQLEXPRESS";
            String dbnavn="dis-bankdb";
            String login="sa";
            String password="msjdbms1998";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection minConnection = DriverManager.getConnection("jdbc:sqlserver://"+ server +";databaseName="+ dbnavn + ";user=" + login + ";password=" + password + ";");
            Statement stmt = minConnection.createStatement();
            ResultSet res=stmt.executeQuery("select * from konto");
            while (res.next()) {
                System.out.println("Konto " + res.getInt(1) +
                        " har saldo " + res.getInt(2) + " og ejer " + res.getString(3) );
            }
            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        }
        catch (Exception e) {
            System.out.print("fejl: "+e.getMessage());
        }
    }
}