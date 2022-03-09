package com.techBlog.entities;

import java.sql.Timestamp;

public class Posts {
	private int pId;
	private String title;
	private String content;
	private String code;
	private String pic;
	private Timestamp date;
	private int cId;
	private int userId;
	public Posts(int pId, String title, String content, String code, String pic, Timestamp date, int cId, int userId) {
		super();
		this.pId = pId;
		this.title = title;
		this.content = content;
		this.code = code;
		this.pic = pic;
		this.date = date;
		this.cId = cId;
		this.userId=userId;
	}
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Posts(String title, String content, String code, String pic, Timestamp date, int cId,int userId) {
		super();
		this.title = title;
		this.content = content;
		this.code = code;
		this.pic = pic;
		this.date = date;
		this.cId = cId;
		this.userId=userId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
