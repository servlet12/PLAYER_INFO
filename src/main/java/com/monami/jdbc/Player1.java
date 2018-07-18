package com.monami.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Player1  {

    public static void main(String[] args) {
 
        // variables
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
 
        // Step 1: Loading or registering MySQL JDBC driver class
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading MySQL JDBC driver");
            cnfex.printStackTrace();
        }
 
        // Step 2: Opening database connection
        try {
 
            // Step 2.A: Create and get connection using DriverManager
            con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/PLAYER_INFO", "root", ""); 
 
            // create SQL query to fetch all player records
            String sql = "SELECT * FROM PLAYER";
 
            // Step 2.B: Creating JDBC Statement 
        ps = con.prepareStatement(sql);
 
            // Step 2.C: Executing SQL & retrieve data into ResultSet
            rs = ps.executeQuery();
 
            System.out.println("ID\tName\tAge\tMatches");
            System.out.println("==\t===\t===\t=======");
 
            // processing returned data and printing into console
            while(rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + 
                        rs.getString(2) + "\t" + 
                        rs.getInt(3) + "\t" +
                        rs.getInt(4));
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {
 
            // Step 3: Closing database connection
            try {
                if(null != con) {
 
                    // cleanup resources, once after processing
                    rs.close();
                    ps.close();
 
                    // and then finally close connection
                    con.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }

    }


