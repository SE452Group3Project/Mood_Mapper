<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:21:26 PM
    Author     : Dave Messer
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    String pageTitle = "New Status";
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
<jsp:include page="/WEB-INF/inc/head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
    <jsp:include page="/WEB-INF/inc/background.jsp"/>
    <jsp:include page="/WEB-INF/inc/navbar.jsp"/>
    <jsp:include page="/WEB-INF/inc/navdrawer.jsp"/>

      <main class="mdl-layout__content">
        <div class="page-content">
          <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--6-col mdl-cell--8-col-tablet" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand" style="margin: auto;">
              <h2 class="mdl-card__title-text">New Status</h2>
            </div>
            <div class="mdl-card__supporting-text" style="margin: auto;">
                <h3>Welcome <%= (user != null) ? user.getUsername() : "!" %></h3>
              <form method="GET" action="./AddStatusServlet" style="margin: auto;">
                
                <!-- Energy -->
                <div class="mdl-card__supporting-text">
                  On a scale of -5 to 5, what is your energy level?
                </div>
                <input name="energy_level" class="mdl-slider mdl-js-slider" type="range" min="-5" max="5" value="0" tabindex="1"/>
                
                <!-- Pleasantness -->
                <div class="mdl-card__supporting-text">
                  On a scale of -5 to 5, what is your pleasantness level?
                </div>
                <input name="pleasantness_level" class="mdl-slider mdl-js-slider" type="range" min="-5" max="5" value="0" tabindex="1"/>
                
                <!-- Descriptive Word -->
                <div class="mdl-card__supporting-text" style="margin-bottom: 0;">
                  One word that describes how you feel:
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%;">
                  <input name="describe_word" class="mdl-textfield__input" type="text" id="sample3" />
                  <label class="mdl-textfield__label" for="sample3">#</label>
                </div>

                <!-- Reflection Paragraph -->
                <div class="mdl-card__supporting-text">
                  Why do you feel this way?
                </div>
                <div class="mdl-textfield mdl-js-textfield" style="width: 100%;">
                  <textarea name="reason" class="mdl-textfield__input" type="text" rows= "3" id="sample5"></textarea>
                  <label class="mdl-textfield__label" for="sample5">I feel this way because...</label>
                </div>

                <!-- Sharing -->
                <div class="mdl-card__supporting-text">
                  Sharing
                </div>
                <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-1">
                  <input name="is_private" type="checkbox" id="switch-1" class="mdl-switch__input" checked />
                  <span class="mdl-switch__label"></span>
                </label>
                <div class="mdl-card__actions mdl-card--border">
                    <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" style="float: right;">
                        Submit
                    </button>
                </div>
              </form>
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
