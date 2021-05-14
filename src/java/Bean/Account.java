package Bean;


import Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Account {

    private String AccNumber;
    private String AccName;
    private String AccOwner;
    private String AccType;
    private double CurrentBalance;
    private Connection connection;
    private PreparedStatement stmt;

    public Account() {
        AccNumber = "";
        AccName = "";
        AccOwner = "";
        AccType = "";
        CurrentBalance = 0;
        try {
            connection = DatabaseConnection.initializeDatabase();
        } catch (Exception e) {
        }
    }

    public boolean depositAmount(double Amount) {
        try {
            stmt = connection
                    .prepareStatement("UPDATE Account SET CurrentBalance = CurrentBalance + ? WHERE AccNumber = ? ;");
            stmt.setFloat(1, (float) Amount);
            stmt.setString(2, AccNumber);
            int result = stmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean withdrawAmount(double Amount) {
        try {
            stmt = connection.prepareStatement(
                    "UPDATE Account SET CurrentBalance = CurrentBalance - ? WHERE CurrentBalance >= ? AND AccNumber = ? ;");
            stmt.setFloat(1, (float) Amount);
            stmt.setFloat(2, (float) Amount);
            stmt.setString(3, AccNumber);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAccountDetails(String AccName, String AccNumber, String AccOwner, String AccType,
            double CurrentBalance) {
        this.AccName = AccName;
        this.AccNumber = AccNumber;
        this.AccOwner = AccOwner;
        this.AccType = AccType;
        this.CurrentBalance = CurrentBalance;
        return true;
    }

    public boolean insertAccountDetails() {
        try {
            stmt = connection.prepareStatement("INSERT INTO Account VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, AccNumber);
            stmt.setString(2, AccName);
            stmt.setString(3, AccOwner);
            stmt.setString(4, AccType);
            stmt.setFloat(5, (float) CurrentBalance);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Account[] findAccount(String AccOwner) {
        try {
            PreparedStatement stmts = DatabaseConnection.initializeDatabase()
                    .prepareStatement("SELECT * FROM Account WHERE AccOwner = ?;");
            stmts.setString(1, AccOwner);
            ResultSet rs = stmts.executeQuery();
            int count = 0;
            Account result[] = new Account[10];
            while (rs.next()) {
                result[count] = new Account();
                result[count].addAccountDetails(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4),
                        rs.getFloat(5));
                count++;
            }
            if (count == 0) {
                return null;
            } else {
                Account[] temp = new Account[count];
                for (int i = 0; i < count; i++) {
                    temp[i] = result[i];
                }
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printAccountEnquiry() {
        System.out.println("Account Name: " + AccName);
        System.out.println("Account Number: " + AccNumber);
        System.out.println("Account Owner: " + AccOwner);
        System.out.println("Account Type: " + AccType);
        System.out.println("Current Balance: " + CurrentBalance);
    }

    public String getAccountOwner() {
        return AccOwner;
    }

    public String getAccountNumber() {
        return AccNumber;
    }

    public String getAccountName() {
        return AccName;
    }

    public String getAccountType() {
        return AccType;
    }

    public double getCurrentBalance() {
        return CurrentBalance;
    }

}
