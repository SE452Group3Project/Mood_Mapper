<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:20:52 PM
    Author     : Dave Messer
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String pageTitle = "My Groups";
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
         
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">My Groups</h2>
            </div>
              <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width:100%;">
              <tbody>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Advisory
                    <a href="advisory.jsp"><i class="material-icons" style="float: right;">chevron_right</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">1st Period
                    <a href=""><i class="material-icons" style="float: right;">chevron_right</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Friends
                    <a href=""><i class="material-icons" style="float: right;">chevron_right</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Create Group
                    <a href="create_group.jsp"><i class="material-icons" style="float: right;">add</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Join Group
                    <a href="join_group.jsp"><i class="material-icons" style="float: right;">group_add</i></a>
                  </td>
                </tr>
              </tbody>
            </table>
            <!-- Right aligned menu on top of button  -->
            <div class=""></div>

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