<%-- 
    Document   : user_profile
    Created on : Oct 20, 2015, 7:30:42 PM
    Author     : faithfulokoye
--%>

<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    UserEntity user = null;
    if (session.getAttribute("user") != null) {
    user = (UserEntity)session.getAttribute("user");  
} else {
       
    out.println("Please login first"); 
    response.sendRedirect("signup.jsp");
}


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="logout">Logout</a>
        <h1>Welcome <%= (user != null) ? user.getUsername() : "" %></h1>
    </body>
</html>
