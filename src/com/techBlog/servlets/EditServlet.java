package com.techBlog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.techBlog.Dao.UserDao;
import com.techBlog.entities.Message;
import com.techBlog.entities.User;
import com.techBlog.helper.ConnectionProvider;
import com.techBlog.helper.Helper;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		 try (PrintWriter out = response.getWriter()) {
			 String userName=request.getParameter("user_name");
			 String userEmail=request.getParameter("user_email");
			 String userPassword=request.getParameter("user_password");
			 String about=request.getParameter("user_about");
			 Part part=request.getPart("image");
			 String image=part.getSubmittedFileName();
			 //get the user from the session
			 HttpSession s=request.getSession();
			 User u=(User)s.getAttribute("user");
			 u.setEmail(userEmail);
			 u.setName(userName);
			 u.setPassword(userPassword);
			 u.setAbout(about);
			 String oldFile = u.getProfile();
			 u.setProfile(image);
			 UserDao uDao=new UserDao(ConnectionProvider.getConnection());
			 boolean result=uDao.updateUser(u);
			 if(result)
			 {
				 out.println("Updated....");
				 
				 String path=request.getRealPath("/")+"pics"+File.separator+u.getProfile();
				 String pathOldFile = request.getRealPath("/") + "pics" + File.separator + oldFile;

	                if (!oldFile.equals("default.png")) {
	                    Helper.deleteFile(pathOldFile);
	                }
				
					 if(Helper.saveFile(part.getInputStream(), path)) {
						// out.println("Profile update...");
						 Message msg=new Message("Profile Photo Updated", "success", "alert-success");
							
							s.setAttribute("msg", msg);
					 }
					 else {
						 Message msg=new Message("Something went wrong!", "error", "alert-danger");
							
							s.setAttribute("msg", msg);
						// out.println("Profile not saved update...");
					 }
				 }
				
			 
			 
			 else
			 {
				 //out.println("Not updated");
				 Message msg=new Message("Something went wrong!r", "error", "alert-danger");
					//HttpSession s=request.getSession();
					s.setAttribute("msg", msg);
			 }
			 response.sendRedirect("profile.jsp");
		 }
	}

}
