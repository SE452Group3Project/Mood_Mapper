<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:15:08 PM
    Author     : Dave Messer
--%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
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
           
            <a class="mdl-button mdl-js-button mdl-button--accent" href="signup.jsp"
               style="
                    position: fixed;
                    background-color: #009178;
                    font-weight: 300; 
                    color: #fff; 
                    border: 5px solid #009178; 
                    top: 40%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    ">SIGN UP OR LOGIN</a>
             
            <p
                 style="
                    position: fixed;
                    color: #fff; 
                    top: 45%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    "
                >Google Login In Beta. Try internal signup</p>

            <div class="mdl-button mdl-js-button mdl-button--accent g-signin2" data-onsuccess="onSignIn"
                 style="
                    position: fixed;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    ">

            </div>
        </div>
      </main>

      <script>
          
      function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token;
        
         var data = 'idtoken=' + id_token + "&username=" + profile.getName() + "&email=" + profile.getEmail(); 
        var url = 'google_sign_in'; 
       
        
        var dataType = 'application/x-www-form-urlencoded'; 
        
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataType: dataType
          }).done(function() {
                console.log("it worked");
                window.location = 'home.jsp';
            });
        
//        var xhr = new XMLHttpRequest();
//        xhr.open('POST', 'google_sign_in');
//        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//        xhr.onload = function() {
//             console.log('Signed in as: ' + xhr.responseText);
//        };
//        xhr.send('idtoken=' + id_token + "&username=" + profile.getName() + "&email=" + profile.getEmail());
//
//        console.log(googleUser); 
//
//        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
//        console.log('Name: ' + profile.getName());
//        console.log('Image URL: ' + profile.getImageUrl());
//        console.log('Email: ' + profile.getEmail());
      }</script>

    </div>
  </body>
</body>
</html>
