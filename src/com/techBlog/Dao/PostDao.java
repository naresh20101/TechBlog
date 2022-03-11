package com.techBlog.Dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.techBlog.entities.Categories;
import com.techBlog.entities.Posts;

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
	    public boolean savePost(Posts posts) {
	    	boolean f=false;
	    	try {
	    		String query="insert into posts(title,content,code,pic,cid,userId) values(?,?,?,?,?,?)";
	    		PreparedStatement ps=con.prepareStatement(query);
	    		ps.setString(1, posts.getTitle());
	    		ps.setString(2, posts.getContent());
	    		ps.setString(3, posts.getCode());
	    		ps.setString(4, posts.getPic());
	    		ps.setInt(5, posts.getcId());
	    		ps.setInt(6, posts.getUserId());
	    		ps.executeUpdate();
	    		f=true;
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    
	    	return f;
	    	
	    }
	    public List<Posts> getAllPosts()
	    {
	    	
	    	List<Posts> list=new ArrayList<>();
	    	try {
	    		String query="select * from posts order by id desc";
	    		PreparedStatement p=con.prepareStatement(query);
	    		ResultSet set=p.executeQuery();
	    		while(set.next())
	    		{
	    			int pId=set.getInt("id");
	    			String title=set.getString("title");
	    			String content=set.getString("content");
	    			String code=set.getString("code");
	    			String pic=set.getString("pic");
	    			Timestamp date=set.getTimestamp("Date");
	    			int cId=set.getInt("cid");
	    			int userId=set.getInt("userId");
	    			Posts posts=new Posts(pId, title, content, code, pic, date, cId, userId);
	    			list.add(posts);
	    			
	    		}
	    		
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	return list;
	    }
	    public List<Posts> getAllPostsById(int cId)
	    {
	    	List<Posts> list=new ArrayList<>();
	    	try {
	    		String query="select * from posts where cid=?";
	    		PreparedStatement p=con.prepareStatement(query);
	    		p.setInt(1, cId);
	    		ResultSet set=p.executeQuery();
	    		while(set.next())
	    		{
	    			int pId=set.getInt("id");
	    			String title=set.getString("title");
	    			String content=set.getString("content");
	    			String code=set.getString("code");
	    			String pic=set.getString("pic");
	    			Timestamp date=set.getTimestamp("Date");
	    			
	    			int userId=set.getInt("userId");
	    			Posts posts=new Posts(pId, title, content, code, pic, date, cId, userId);
	    			list.add(posts);
	    			
	    		}
	    		
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	return list;
	    }

}
