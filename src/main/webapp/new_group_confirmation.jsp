<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:18:50 PM
    Author     : Dave Messer
--%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.moodmapper.entity.GroupEntity" %>
<% GroupEntity newGroup = (GroupEntity) request.getAttribute("newGroup"); %>

<%
    UserEntity user = null;
    user = (UserEntity)session.getAttribute("user");  
  

String pageTitle = "New Group Confirmation";
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
          <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" >
            <div >
                <div class="mdl-card__title">
                    <h2 class="mdl-card__title-text"><%= newGroup.getName() %> created!</h2>
                </div>
                <div class="mdl-card__title">
                
                <h2 class="mdl-card__title-text" >Join Code:</h2>
                </div>
                
                <div class="mdl-card__title">
                <h2 class="mdl-card__title-text" style="color:blue"><%= newGroup.getJoinCode() %></h2>
                </div>
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

    </div>
  </body>
</body>
</html>