<%@page import="com.sun.org.apache.bcel.internal.generic.INEG"%>
<%@page import="java.util.List"%>
<%@page import="com.techBlog.entities.Posts"%>
<%@page import="com.techBlog.helper.ConnectionProvider"%>
<%@page import="com.techBlog.Dao.PostDao"%>
<div class="row">

<% 
Thread.sleep(500);
  
  PostDao d=new PostDao(ConnectionProvider.getConnection());
 int cId=Integer.parseInt(request.getParameter("cid"));
System.out.println("cid ::"+ cId);
List<Posts> posts=null;
  if(cId==0){
   posts=d.getAllPosts();
  }
  else{
	  posts=d.getAllPostsById(cId);
  }
  if(posts.size()==0)
  {
	  out.println("<h3 class='display-3 text-center'>No posts in this category</h3>");
	  return;
  }
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