package opg23_2_optimistic_locking;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Transaction {
    private static Connection dbConnection;
    public static void main(String[] args) throws SQLException {
        try {
            Scanner input = new Scanner(System.in);
            dbConnection = connectDB();

            logAccounts();

            System.out.println("MAKE DEPOSIT");

            System.out.println("Enter account number: ");
            String accountNumber = input.nextLine();
            ResultSet result = getAccount(accountNumber);
            if (!result.next()) {
                throw new RuntimeException("Account " + accountNumber + " does not exist");
            }
            System.out.println("Konto " + result.getInt(1) +
                    " har saldo " + result.getInt(2) + " og ejer " + result.getString(3) );
            byte[] version = result.getBytes("version");
            System.out.println("version: " + version);

            System.out.println("Enter amount to insert: ");
            int amount = Integer.parseInt(input.nextLine());
            if (amount <= 0) {
                throw new RuntimeException("Amount must be positive");
            }
            int accountBalance = result.getInt(2);

            System.out.println("Read is done. Update by pressing any key.");
            input.nextLine();

            dbConnection.setAutoCommit(false);

            result = getAccount(accountNumber);
            if (!result.next()) {
                throw new RuntimeException("Account " + accountNumber + " does not exist");
            } else if (!Arrays.equals(result.getBytes("version"), version)) {
                throw new RuntimeException("Account " + accountNumber + " has been updated by another transaction");
            }
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
        System.out.println("Updating account...");
        Statement statement = dbConnection.createStatement();
        statement.executeUpdate("update konto set saldo = " + newBalance + " where kontonr = " + accountNumber);
        System.out.println("Account updated");
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