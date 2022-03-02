package com.techBlog.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	 private static Connection con;

	    public static Connection getConnection() {
	        try {
	            if (con == null) {
	                // Driver class load
	                Class.forName("com.mysql.jdbc.Driver");
	                // Create Connection
	                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/techblog", "root", "root");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error : " + e.getMessage());
	        }

	        return con;
	    }

}
