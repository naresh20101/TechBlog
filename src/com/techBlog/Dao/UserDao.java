package com.techBlog.Dao;
import com.techBlog.entities.*;
import java.sql.*;

import com.techBlog.entities.User;

public class UserDao {

	 private Connection con;

	    public UserDao(Connection con) {
	        this.con = con;
	    }

	    //method to insert user to data base:
	    public boolean saveUser(User user) {
	        boolean f = false;
	        try {
	            //user -->database

	            String query = "insert into user(user_name,email,password,gender,about) values (?,?,?,?,?)";
	            PreparedStatement pstmt = this.con.prepareStatement(query);
	            pstmt.setString(1, user.getName());
	            pstmt.setString(2, user.getEmail());
	            pstmt.setString(3, user.getPassword());
	            pstmt.setString(4, user.getGender());
	            pstmt.setString(5, user.getAbout());

	            pstmt.executeUpdate();
	            f = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return f;

	    }

}
