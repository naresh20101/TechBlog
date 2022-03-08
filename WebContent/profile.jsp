<%@page import="java.util.ArrayList"%>
<%@page import="com.techBlog.helper.ConnectionProvider"%>
<%@page import="com.techBlog.Dao.PostDao"%>
<%@page import="com.techBlog.entities.*"%>
<%@page errorPage="error_page.jsp" %>
<%
User u=(User)session.getAttribute("user");
if(u==null)
{
	response.sendRedirect("login_page.jsp");
}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/mycss.css" rel="stylesheet" type="text/css"/>
<title>Insert title here</title>
</head>
<body>

<!--  Start of Navbar  -->
<nav class="navbar navbar-expand-lg navbar-dark primary_background">
  <a class="navbar-brand" href="#"><span class="fa fa-asterisk"></span>Teck Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"><span class="fa fa-bell-o"></span>Learn code with Naresh <span  class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <span class="fa fa-check-square-o"></span>Categories
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Programming Language</a>
          <a class="dropdown-item" href="#">Project Implementation</a>
          <div class="dropdown-divider"> </div>
          <a class="dropdown-item" href="#">Data Structure</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><span class="fa fa-address-book-o"></span>Contact</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#"  data-toggle="modal" data-target="#doPostModel"><span class="fa fa-asterisk"></span>Post</a>
      </li>
      
    </ul>
    <ul class="navbar-nav mr-right">
     <li class="nav-item">
        <a class="nav-link" href="" data-toggle="modal" data-target="#exampleModal" ><span class="fa fa-user-circle"></span> <%=u.getName() %></a>
      </li>
     <li class="nav-item">
        <a class="nav-link" href="LogoutServlet"><span class="fa fa-user-plus"></span>  Logout</a>
      </li>
    </ul>
    
  </div>
</nav>
<!-- End of Navbar -->
  <%
         Message m=(Message)session.getAttribute("msg");
         if(m!=null)
         {
        	 %>
        	  <div class="alert <%=m.getCssClass() %>" role="alert">
				  
				  <%=m.getContent() %>
		</div>
		<% 
		  session.removeAttribute("msg");
         }
        
         
         
         %>


 <!-- Model  -->
 
 

<div class="container mt-auto">
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header primary_background text-white">
        <h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
       <div class="container text-center">
       <img src="pics/<%=u.getProfile() %>"   style="border-radius:50%; max-width: 150px">
      
       
        <h5 class="modal-title" id="exampleModalLabel"><%=u.getName() %></h5>
        <!-- Details -->
       <div id="profile_Details">
       
        <table class="table">
 
  <tbody>
    <tr>
      <th scope="row">ID</th>
      <td><%=u.getId() %></td>
      
    </tr>
    <tr>
      <th scope="row">Email</th>
      <td><%=u.getEmail() %></td>
     
    </tr>
    <tr>
      <th scope="row">Gender</th>
      <td><%=u.getGender() %></td>
     
    </tr>
     <tr>
      <th scope="row">About</th>
      <td><%=u.getAbout() %></td>
     
    </tr>
  </tbody>
</table>
</div>
<div id="editProfile" style="display: none;">
                
                 <h3 class="mt-2">Please Edit Carefully</h3>
                                <form action="EditServlet" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>ID :</td>
                                            <td><%= u.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Email :</td>
                                            <td> <input type="email" class="form-control" name="user_email" value="<%= u.getEmail()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Name :</td>
                                            <td> <input type="text" class="form-control" name="user_name" value="<%= u.getName()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td> <input type="password" class="form-control" name="user_password" value="<%= u.getPassword()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Gender :</td>
                                            <td> <%= u.getGender().toUpperCase()%> </td>
                                        </tr>
                                        <tr>
                                            <td>About  :</td>
                                            <td>
                                                <textarea rows="3" class="form-control" name="user_about" ><%= u.getAbout()%>
                                                </textarea>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>New Profile:</td>
                                            <td>
                                                <input type="file" name="image" class="form-control" >
                                            </td>
                                        </tr>

                                    </table>

                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-primary">Save</button>
                                    </div>

                                </form>    
                
                
                
			</div>
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" id="edit_button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div>
</div>
<!-- Post Model -->

<div class="modal fade" id="doPostModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Provide the post details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="AddServlet" method="post">
           <div class="form-group">
           <select class="form-control">
             <option selected disabled>----Select Categories----</option>
             <%
               PostDao postDao=new PostDao(ConnectionProvider.getConnection());
             ArrayList<Categories> list=postDao.getAllCategories();
             for(Categories c:list)
             {%>
               <option><%=c.getName() %></option>
            <%  }%>
            
             
           </select>
          </div>
          <div class="form-group">
           <input type="text" placeholder="Enter post title" class="form-control">
           </div>
           <div class="form-group">
           <textarea type="text" placeholder="Enter your content" class="form-control"></textarea>
          </div>
           <div class="form-group">
           <textarea type="text" placeholder="Enter your programm(if any)" class="form-control"></textarea>
          </div>
           <div class="form-group">
           <label>Select your pic</label>
         <input type="file">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<!-- Java script -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script type="text/javascript">

 $(document).ready(function(){
	 let statusbtn=false;
	 // alert("testing");
	 $('#edit_button').click(function(){
		 if(statusbtn==false)
			{
				$('#profile_Details').hide();
				$('#editProfile').show();
				$(this).text("Back");
				statusbtn=true;
				
			}
		 else
			 {
			 $('#profile_Details').show();
				$('#editProfile').hide();
				$(this).text("Edit");
				statusbtn=false;
			 }
          //alert("button clicked");
		 })
	 });
</script>





</body>
</html>