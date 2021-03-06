<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:18:50 PM
    Author     : Jase Kurasz
--%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.moodmapper.entity.GroupEntity" %>
<% GroupEntity group = (GroupEntity) request.getAttribute("group"); %>

<%
    UserEntity user = null;
    user = (UserEntity)session.getAttribute("user");  
    
String pageTitle = "Remove Group Confirmation";
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
                <h2 class="mdl-card__title-text">You are no longer a member of <%= group.getName() %></h2>
            </div>
          </div>
        </div>
      </main>
      
  </body>
</html>