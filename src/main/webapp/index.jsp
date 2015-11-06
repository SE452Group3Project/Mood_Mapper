<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:15:08 PM
    Author     : Dave Messer
--%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserEntity user = null;
    if (session.getAttribute("user") != null) {
        user = (UserEntity)session.getAttribute("user");  
    } else {
        out.println("Please login first"); 
        response.sendRedirect("signup.jsp");
    }
String pageTitle = "Home";
%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/inc/head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
    <jsp:include page="/WEB-INF/inc/background.jsp"/>
    <jsp:include page="/WEB-INF/inc/navbarhome.jsp"/>
      <main class="mdl-layout__content">
        <div class="page-content">
            <div class="mdl-button mdl-js-button mdl-button--accent g-signin2" data-onsuccess="onSignIn"
                 style="
                    position: fixed;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    ">
            </div>
            <a class="mdl-button mdl-js-button mdl-button--accent" href="signup.jsp"
               style="
                    position: fixed;
                    background-color: #fff;
                    top: 60%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    ">Signup/Login</a>
        </div>
      </main>

      <script>function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
      }</script>

    </div>
  </body>
</body>
</html>
