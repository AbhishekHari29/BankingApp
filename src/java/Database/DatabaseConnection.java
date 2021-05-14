package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class can be used to initialize the database connection 
public class DatabaseConnection {

    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access 
        String dbName = "bankingapp";
        String disableSSL = "?useSSL=false";
        String dbUsername = "root";
        String dbPassword = "Abhishek@Hari";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName + disableSSL, dbUsername, dbPassword);
        return con;
    }
}
