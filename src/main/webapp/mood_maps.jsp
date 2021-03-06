<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:20:13 PM
    Author     : Dave Messer
--%>

<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="com.moodmapper.entity.MoodStatusEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    UserEntity user = null;
    user = (UserEntity)session.getAttribute("user");  
    

String pageTitle = "Mood Maps";
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
            background: bottom right 15% no-repeat #46B6AC;
            background-image: url(http://i.stack.imgur.com/f48Tj.png);
            background-size:300px;
            background-position: center;
          }
          .dot {
              width: 10px;
              height: 10px;
              position: absolute;
          }
          .comment {
              color: red;
              text-align: center;
              font-style: italic;
              font-weight: bold;
          }
          
          .imgDescription {
              position:fixed;
              alignment-adjust: middle;
              top:510px;
              visibility: hidden;
              opacity: 0;
              background:grey;
              border:1px solid black;
          }

          .imgWrap:hover .imgDescription {
              visibility: visible;
              opacity: 1;
          }
          
          </style>
          <div class="demo-card-square mdl-card mdl-shadow--2dp" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand">
                <%
                    ArrayList<MoodStatusEntity> moods = new ArrayList<>(user.getMoodStatuses());
                    for(MoodStatusEntity mood: moods) { %>
                    <div class="imgWrap">
                        <img class="dot" src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Red_pog.svg/2000px-Red_pog.svg.png"
                              style="left: <%= (mood.getEnergyRating()+5) * 28 + 7 %>px; top: <%= 301-(mood.getPleasantnessRating()+5)*28 %>px;" alt="polaroid">
                        <table class="imgDescription">
                            <tr>
                                <th>Create Date</th>
                                <td><%= mood.getCreatedOn() %></td>
                            </tr>
                            <tr>
                                <th>Descriptive Word</th>
                                <td><%= mood.getDescriptiveWord() %></td>
                            </tr>
                            <tr>
                                <th>Reflective Paragraph</th>
                                <td><%= mood.getReflectiveParagraph() %></td>
                            </tr>
                        </table>
                    </div>
                             
                  <% } %>
<!--              <img class="dot" src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Red_pog.svg/2000px-Red_pog.svg.png"
                   style="left: 0px; top: 0px;">-->
              <h2 class="mdl-card__title-text">My Mood Maps</h2>
            </div>
            
          </div>
          <div class="comment">    
              <h5>x means Energy Rating, y means Pleasantness Rating.</h5>
          </div>
        </div>
      </main>
      


    </div>
  </body>
</body>
</html>