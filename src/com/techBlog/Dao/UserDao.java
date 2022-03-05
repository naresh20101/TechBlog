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
	    public User getUserByUserEmailAndPassword(String email,String Password)
	    {
	    	User user=null;

	        try {

	            String query = "select * from user where email =? and password=?";
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setString(1, email);
	            pstmt.setString(2, Password);

	            ResultSet set = pstmt.executeQuery();

	            if (set.next()) {
	                user = new User();

//	             data from db
	                String name = set.getString("user_name");
//	             set to user object
	                user.setName(name);

	                user.setId(set.getInt("user_id"));
	                user.setEmail(set.getString("email"));
	                user.setPassword(set.getString("password"));
	                user.setGender(set.getString("gender"));
	                user.setAbout(set.getString("about"));
	               // user.setDateTime(set.getTimestamp("rdate"));
	                user.setProfile(set.getString("profile"));

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	return user;
	    }
	    public boolean updateUser(User user)
	    {
	    	boolean f=false;
	    
	    	try {
	    		String query="update user set user_name=? , email=? , password=? , gender=? ,about=? , profile=? where  user_id =?";
	    		 PreparedStatement p = con.prepareStatement(query);
	    		 p.setString(1, user.getName());
	    		 p.setString(2, user.getEmail());
	    		 p.setString(3, user.getPassword());
	    		 p.setString(4, user.getGender());
	    		 p.setString(5, user.getAbout());
	    		 p.setString(6, user.getProfile());
	    		 p.setInt(7, user.getId());
	    		 p.executeUpdate();
;	    		 f=true;
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	return f;
	    }

}
