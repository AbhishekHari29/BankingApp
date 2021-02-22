import java.sql.*;

import java.sql.Connection;

public class User{
    private String Name;
    private String ID;
    private String Gender;
    private String Password;
    private Connection connection = null;
    private PreparedStatement stmt = null;
    
    User(){
            Name = "";
            ID = "";
            Gender = "";
            Password = "";
            try {
                connection = DatabaseConnection.initializeDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public String getUserID(){
        return ID;
    }
    public String getUserName(){
        return Name;
    }
    public String getPassword(){
        return Password;
    }
    public void addUser(String Name, String ID, String Gender, String Password){
        this.ID = ID;
        this.Name = Name;
        this.Gender = Gender;
        this.Password = Password;
    }
    public boolean insertUser(){
        try {
            stmt = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?);");            
            stmt.setString(1, Name);
            stmt.setString(2, ID);
            stmt.setString(3, Gender);
            stmt.setString(4, Password);
            int result = stmt.executeUpdate();
            if(result > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public boolean validateUser(String Password){
        if(this.Password.equals(Password))
            return true;
        return false;
    }
    public static User userExist(String userID){
        try {
            PreparedStatement stmt1 = DatabaseConnection.initializeDatabase().prepareStatement("SELECT * FROM user WHERE UserID = ?;");
            stmt1.setString(1, userID);
            ResultSet rs = stmt1.executeQuery();
            if(rs.next()){
                User result = new User();
                result.addUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}