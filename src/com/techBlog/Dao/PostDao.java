package com.techBlog.Dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.techBlog.entities.Categories;

import java.sql.*;

public class PostDao {
	 private Connection con;

	    public PostDao(Connection con) {
	        this.con = con;
	    }
	    public ArrayList<Categories> getAllCategories()
	    {
	    	ArrayList<Categories> list=new ArrayList<>();
	    	try {
	    		String query="select * from categories";
	    		Statement s=this.con.createStatement();
	    		ResultSet set=s.executeQuery(query);
	    		while(set.next())
	    		{
	    			int cId=set.getInt("cid");
	    			String name=set.getString("name");
	    			String description=set.getString("description");
	    			Categories c=new Categories(cId, name, description);
	    			list.add(c);
	    		}
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	return list;
	    }

}
