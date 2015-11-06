<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:19:36 PM
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
String pageTitle = "Join Group";
String postError = (String)session.getAttribute("error");
String showError;
if(postError == null){
    showError = "";
} else {
    showError = postError;
}
%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/inc/head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
    <jsp:include page="/WEB-INF/inc/background.jsp"/>
    <jsp:include page="/WEB-INF/inc/navbar.jsp"/>
    <jsp:include page="/WEB-INF/inc/navdrawer.jsp"/>

      <main class="mdl-layout__content">
        <div class="page-content">
         <style>
          .demo-card-square.mdl-card {
            width: 320px;
            height: 320px;
          }
          .demo-card-square > .mdl-card__title {
            color: #fff;
            background:
              url('../assets/demos/dog.png') bottom right 15% no-repeat #46B6AC;
          }
          </style>
          <div class="demo-card-square mdl-card mdl-shadow--2dp" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand">
              <h2 class="mdl-card__title-text">Join Group</h2>
            </div>
            
            <div class="mdl-card__supporting-text">
              <form action="JoinGroupServlet" method="POST">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" id="joinCode" name="joinCode" required />
                  
                  <label class="mdl-textfield__label" for="joinCode">Join Code</label>
                  
                </div>
                 <div class="mdl-card__actions mdl-card--border">
                    <input type="submit" value="Join" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                 </div>
              </form>
              <div>
                <span class = "error"> 
                    <strong><%= showError %></strong>
                </span>
            </div>
            </div>
          </div>
        </div>
      </main>
      
                    <% 
                        // Reset error
                        session.setAttribute("error", "");  %>

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