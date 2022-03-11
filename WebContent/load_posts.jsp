<%@page import="java.util.List"%>
<%@page import="com.techBlog.entities.Posts"%>
<%@page import="com.techBlog.helper.ConnectionProvider"%>
<%@page import="com.techBlog.Dao.PostDao"%>
<div class="row">

<% 
  PostDao d=new PostDao(ConnectionProvider.getConnection());
   List<Posts> posts=d.getAllPosts();
   for(Posts p:posts)
   {
	   
   

%>


<div class="col-md-6 mt-2">
 <div class="card">
  <div class="card-body">
 <img class="card-img-top" src="pics/<%=p.getPic() %>" alt="Card image cap">
  
     <h3><%=p.getTitle() %></h3>
       <h3><%=p.getContent() %></h3>
    
  </div>
 </div>
</div>
 <%} %>
</div>