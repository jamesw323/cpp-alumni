/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumni;

import java.sql.*;
import java.util.HashSet;


/**
 *
 * @author alejandrok
 */
public class Controller {
    
    private String DBurl = "jdbc:mysql://localhost:3306/cpp-alumni";
    private String username = "root";
    private String pass = "sesame";
    
    public void Controller() {
        
    }
    
    public User getUser(String email) {
       
        User user = new User();
        try {
        Connection conn = connectToDB();
        conn = DriverManager.getConnection(DBurl, username, pass);
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            
            
            user.setUserId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
   
        }

        }

        catch(SQLException e ) {
            e.getMessage();
        }
        return user;
    }
    
    @SuppressWarnings("empty-statement")
    public Connection connectToDB() {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        
        catch (ClassNotFoundException e) {
            e.getException();
            
        }
        
        try {
    
    
   
    conn = DriverManager.getConnection(DBurl, username, pass);
    
   
   
    if (conn.isValid(10)) {
        System.out.println("Connection succesful!!");
    }
    
    else {
        System.out.println("Connection failed");
    }
    
    
        
        }
    
    catch (SQLException e) {
        for (Throwable t : e )
            t.printStackTrace();
    }
            System.out.println("returned conn obj");
           return conn;
    
    }
    
    public boolean doesEmailExist(String email) {
       
        
        boolean exists = false;
        
        /* TEMPORARY way to connect to db */
         
    
    /* END OF TEMPORARY WAY TO CONNECT TO DB */
        
    
    
    try {
        //prepare SQL query and execute
        String query = "SELECT * FROM users WHERE email = " + "\'" + email + "\'";
         
        Connection conn = connectToDB();
        conn = DriverManager.getConnection(DBurl, username, pass);
        Statement stmt = conn.createStatement();
       
        
      
        
        System.out.println("executed query");
        ResultSet result = stmt.executeQuery(query);
       
        
        if (result.first()) {
            System.out.println("result exists");
            exists = true;
        }
        
        else {
            System.out.println("result does NOT exist");
            exists = false;
        }
        
        
        }
        
        catch (SQLException e) {
            e.getMessage();
        }
        
        
       
        return exists;
    }
    
    public boolean doesPasswordExist(String password) {
        
          boolean passExists = false;
    try {
        //prepare SQL query and execute
        String query = "SELECT * FROM users WHERE password = " + "\'" + password + "\'";
         
        Connection conn = connectToDB();
        conn = DriverManager.getConnection(DBurl, username, pass);
        Statement stmt = conn.createStatement();
       
        
      
        System.out.println(password);
        System.out.println("executed query");
        ResultSet result = stmt.executeQuery(query);
       
        
        if (result.first()) {
            System.out.println("result exists");
            passExists = true;
        }
        
        else {
            System.out.println("result does NOT exist");
            passExists = false;
        }
        
        
        }
        
        catch (SQLException e) {
            e.getMessage();
        }
        
        
       
        return passExists;

    }

    
    
    public void registerUser(String firstName, String lastName, String email, String password, 
            String graduatedYear, String degree, String major) {
        
       try {
        //connect to DB
        Connection conn = connectToDB();
        conn = DriverManager.getConnection(DBurl, username, pass);
        
        String query = "INSERT INTO users "
                + "(first_name, last_name, email, password, year_graduated, degree, major)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(query);

     ps.setString(1, firstName);
     ps.setString(2, lastName);
    
     ps.setString(3, email);
     ps.setString(4, password);
     ps.setString(5, graduatedYear);
     ps.setString(6, degree);
     ps.setString(7, major);
     
     ps.executeUpdate();

       }
       
       catch (SQLException e) {
           e.getMessage();
       }
    
    }
    
    
    public void updatePersonalInfo(int userID, String phone, String description) {
        
        try {
        //connect to DB
        Connection conn = connectToDB();
        conn = DriverManager.getConnection(DBurl, username, pass);
        
        String query = "UPDATE users SET phone = ?, description = ? WHERE id = ?";
 
        PreparedStatement ps = conn.prepareStatement(query);
        
        
        ps.setString(1, phone);
        ps.setString(2, description);
        ps.setInt(3, userID);
        
        ps.executeUpdate();
        
        }
        
        
        catch (SQLException e) {
            e.getMessage();
        }
        
    }


}


