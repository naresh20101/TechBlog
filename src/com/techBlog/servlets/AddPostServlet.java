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

import com.techBlog.Dao.PostDao;
import com.techBlog.entities.Posts;
import com.techBlog.entities.User;
import com.techBlog.helper.ConnectionProvider;
import com.techBlog.helper.Helper;

/**
 * Servlet implementation class AddPostServlet
 */
@MultipartConfig
@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostServlet() {
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
			 int cid=Integer.parseInt(request.getParameter("cid"));
			 String title=request.getParameter("title");
			 String content=request.getParameter("content");
			 String code=request.getParameter("code");
			 Part part=request.getPart("pic");
			// out.println(title);
			 System.out.println(content);
			 HttpSession session=request.getSession();
			 User user=(User) session.getAttribute("user");
			 Posts posts=new Posts(title, content, code, part.getSubmittedFileName(), null, cid, user.getId());
			 PostDao postDao=new PostDao(ConnectionProvider.getConnection());
			if( postDao.savePost(posts))
			{
				out.println("done");
				 String pathOldFile = request.getRealPath("/") + "pics" + File.separator + part.getSubmittedFileName();
				 Helper.saveFile(part.getInputStream(),pathOldFile);
				 out.println("done");

			}
			else {
				out.println("error");
			}
		 }
	}

}
