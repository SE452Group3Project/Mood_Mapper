<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:17:02 PM
    Author     : Dave Messer
--%>

<%@page import="com.moodmapper.entity.MoodStatusEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="com.moodmapper.entity.GroupEntity"%>
<%@page import="java.util.Collection"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    String pageTitle = "App";
    UserEntity user = null;
    String notice = ""; 
//    String debug = "";
    if (session.getAttribute("user") != null) {
        user = (UserEntity)session.getAttribute("user"); 
        if (session.getAttribute("notice") != null){
               notice = (String) session.getAttribute("notice");

        }
//        if (session.getAttribute("log") != null){
//            out.println(session.getAttribute("log"));
//        }

    } else {
        session.setAttribute("notice", "Please login first"); 
        response.sendRedirect("signup.jsp");
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
            <div class="notice" style="margin: 0 auto; text-align: center; color: #fff;"><%= (notice.isEmpty()) ? "" : notice %> </div>
            
          <!-- Mood Status cards from other users -->
          <%
          
          if(user.getGroupsJoined() != null){
              %>
              <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">User has groups :)</div>
              <%
          }
          else {
              %>
              <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">User has no groups :(</div>
              <%
          }
 
          
          %>
          
          <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Jimmy</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Woke up and took a run this morning
              <h4>#energized (5,3)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
            </div>
          </div>

          <!-- Wide card with share menu button -->
          <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Erin</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Forgot to do my homework
              <h4>#frustrated (2,-3)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
            </div>
          </div>

          <!-- Wide card with share menu button -->
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Dave</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Rode my bike to school!
              <h4>#excited (4, 4)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
            </div>
          </div>

        </div>
      </main>
      


      <script>function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
      }</script>
      
      <script>alert("Welcome <%= (user != null) ? user.getUsername() : "!" %>");</script>

    </div>
  </body>
</body>
</html>