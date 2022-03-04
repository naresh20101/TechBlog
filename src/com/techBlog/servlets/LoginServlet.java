package com.techBlog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techBlog.Dao.UserDao;
import com.techBlog.entities.Message;
import com.techBlog.entities.User;
import com.techBlog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		 try (PrintWriter out = response.getWriter()) {
			// login
			// fetch username and password from request
			String userEmail = request.getParameter ("email");
			String userPassword = request.getParameter ("password");
			UserDao dao=new UserDao (ConnectionProvider.getConnection ());
			User u=dao.getUserByUserEmailAndPassword(userEmail, userPassword);
			if(u==null)
			{
				//error
				//out.println("Invalid Data");
				Message msg=new Message("Invalid Details! Try with another", "error", "alert-danger");
				HttpSession s=request.getSession();
				s.setAttribute("msg", msg);
				response.sendRedirect("login_page.jsp");
			}
			else
			{
				HttpSession s=request.getSession();
				s.setAttribute("user", u);
				response.sendRedirect("profile.jsp");
			}
		 }
		 
		 
	}

}
