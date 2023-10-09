import java.sql.*;
import java.util.Scanner;

public class Transaction {
    private static Connection dbConnection;
    public static void main(String[] args) {
// TODO Auto-generated method stub
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("MAKE TRANSACTION");
            System.out.println("Enter FROM account number: ");
            String fromAccount = input.nextLine();
            System.out.println("Enter TO account number: ");
            String toAccount = input.nextLine();
            System.out.println("Enter amount: ");
            int amount = Integer.parseInt(input.nextLine());

            dbConnection = connectDB();

            ResultSet result = getAccount(fromAccount);
            if (!result.next()) {
                throw new RuntimeException("From account not found");
            }
            int fromAccountBalance = result.getInt(2);
            if (fromAccountBalance < amount) {
                throw new RuntimeException("Not enough money");
            }

            result = getAccount(toAccount);
            if (!result.next()) {
                throw new RuntimeException("To account not found");
            }
            int toAccountBalance = result.getInt(2);

            updateAccount(fromAccount, fromAccountBalance - amount);
            updateAccount(toAccount, toAccountBalance + amount);

            logAccounts();

            result.getStatement().close();
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connectDB() throws SQLException, ClassNotFoundException {
        String server="localhost\\SQLEXPRESS";
        String dbnavn="dis-bankdb";
        String login="sa";
        String password="msjdbms1998";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://"+ server +";databaseName="+ dbnavn + ";user=" + login + ";password=" + password + ";");
    }

    public static ResultSet getAccount(String accountNumber) throws SQLException {
        Statement stmt = dbConnection.createStatement();
        ResultSet res = stmt.executeQuery("select * from konto where kontonr = " + accountNumber);
        return res;
    }

    public static void updateAccount(String accountNumber, int newBalance) throws SQLException {
        Statement statement = dbConnection.createStatement();
        statement.executeUpdate("update konto set saldo = " + newBalance + " where kontonr = " + accountNumber);
    }

    public static void logAccounts() throws SQLException {
        ResultSet result = dbConnection.createStatement().executeQuery("select * from konto");
        while (result.next()) {
            System.out.println("Konto " + result.getInt(1) +
                    " har saldo " + result.getInt(2) + " og ejer " + result.getString(3) );
        }
    }
}