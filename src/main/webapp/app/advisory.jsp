<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:18:09 PM
    Author     : Dave Messer
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String pageTitle = "Advisory";
%>
<!DOCTYPE html>
<html>
<jsp:include page="../../WEB-INF/inc/head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
    <jsp:include page="../../WEB-INF/inc/background.jsp"/>
    <jsp:include page="../../WEB-INF/inc/navbar.jsp"/>
    <jsp:include page="../../WEB-INF/inc/navdrawer.jsp"/>

      <main class="mdl-layout__content">
        <div class="page-content">
         
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Advisory</h2>
            </div>
              <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width:100%;">
              <tbody>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Aaron
                    <a href=""><i class="material-icons" style="float: right;"><i id="unfollow" class="material-icons">person</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Ben
                    <a href=""><i class="material-icons" style="float: right;"><i id="unfollow1" class="material-icons">person</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Courtney
                    <a href=""><i class="material-icons" style="float: right;"><i id="follow" class="material-icons">person_outline</i></a>
                  </td>
                </tr>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Dave
                    <a href=""><i class="material-icons" style="float: right;"><i id="follow1" class="material-icons">person_outline</i></a>
                  </td>
                </tr>
                
                <div class="mdl-tooltip" for="unfollow">
                Unfollow
                </div>
                <div class="mdl-tooltip" for="unfollow1">
                Unfollow
                </div>
                <div class="mdl-tooltip" for="follow">
                Follow
                </div>
                <div class="mdl-tooltip" for="follow1">
                Follow
                </div>
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