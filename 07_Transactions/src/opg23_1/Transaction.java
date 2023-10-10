package opg23_1;

import java.sql.*;
import java.util.Scanner;

public class Transaction {
    private static Connection dbConnection;
    public static void main(String[] args) throws SQLException {
        try {
            Scanner input = new Scanner(System.in);
            dbConnection = connectDB();

            logAccounts();
            dbConnection.setAutoCommit(false);

            System.out.println("INSERT MONEY TO ACCOUNT");

            System.out.println("Enter account number: ");
            String accountNumber = input.nextLine();
            ResultSet result = getAccount(accountNumber);
            if (!result.next()) {
                throw new RuntimeException("Account " + accountNumber + " does not exist");
            }
            System.out.println("Konto " + result.getInt(1) +
                    " har saldo " + result.getInt(2) + " og ejer " + result.getString(3) );

            System.out.println("Enter amount to insert: ");
            int amount = Integer.parseInt(input.nextLine());
            if (amount <= 0) {
                throw new RuntimeException("Amount must be positive");
            }
            int accountBalance = result.getInt(2);

            updateAccount(accountNumber, accountBalance + amount);

            dbConnection.commit();

            logAccounts();

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        catch (Exception e) {
            dbConnection.rollback();
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
        System.out.println("ACCOUNTS:");
        ResultSet result = dbConnection.createStatement().executeQuery("select * from konto");
        while (result.next()) {
            System.out.println("Konto " + result.getInt(1) +
                    " har saldo " + result.getInt(2) + " og ejer " + result.getString(3) );
        }
    }
}