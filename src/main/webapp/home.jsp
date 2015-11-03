<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:17:02 PM
    Author     : Dave Messer
--%>

<%@page import="java.util.Set"%>
<%@page import="com.moodmapper.entity.CommentEntity"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="java.util.List"%>
<%@page import="com.moodmapper.entity.MoodStatusEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="com.moodmapper.entity.GroupEntity"%>
<%@page import="java.util.Collection"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.persistence.Persistence"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    
    String pageTitle = "App";
    UserEntity user = null;
    
    String notice = ""; 
//    String debug = "";
    if (session.getAttribute("user") != null) {
        // Session was holding only current comments within the user's mood statuses (Taken from the UserEntity in the session) and not showing new comments after forward back.
        // This is the work-around to make the new comments appear after a a new comment is posted.  
        //userCurrent = (UserEntity)session.getAttribute("user"); 
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
          
          // get the 20 most recent mood statuses
            List<MoodStatusEntity> results = new ArrayList<>(user.getMoodStatuses());
            for(MoodStatusEntity m : results){

                String userName = m.getUser().getUsername();
                String reflectiveParagraph = m.getReflectiveParagraph();
                String descriptiveWord = m.getDescriptiveWord();
                String pleasantnessRating = m.getPleasantnessRating().toString();
                String energyRating = m.getEnergyRating().toString();
                List<CommentEntity> comments = new ArrayList<>(m.getComments());
                System.out.println(comments.toString());
              
              %>
              <div class="card demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
                <div class="mdl-card__title">
                    <h2 class="mdl-card__title-text"><%=userName%></h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <%=reflectiveParagraph%>
                    <h4>#<%=descriptiveWord%> (<%=pleasantnessRating%>,<%=energyRating%>)</h4>
                </div>
                <!-- comments -->
                <% for (CommentEntity comment : comments){
                    UserEntity commenter = comment.getUser();
                    String commenterUserName = commenter.getUsername();
                    //String commenterFirstName = commenter.getFirstName();
                    //String commenterLastName = commenter.getLastName();
                    String commentContent = comment.getContent();
                    %>
                <div class="mdl-color-text--grey-600 mdl-card__supporting-text">
                    <%=commentContent%><b> <%=commenterUserName%> </b>
                </div>
                <%
                    }
                
                %>
                
                <!-- new comment -->
                <div class="mdl-card__actions mdl-card--border">
                    <form action="AddCommentServlet" >
                        <input type="hidden" name="moodStatusID" value="<%= m.getId()%>">
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="text" name="comment" placeholder="Write a comment..." />
                        </div>
                        <button type="submit" value="Comment" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            <i class="material-icons">comment</i>
                        </button>
                    </form>
                </div>
                <div class="mdl-card__menu">
                </div>
              </div>

              <%
              
          }
          
          
          %>
          
          <!-- Template for mood status card
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
          -->

        </div>
      </main>
      
      <script>alert("Welcome <%= (user != null) ? user.getUsername() : "!" %>");</script>

    </div>
  </body>
</body>
</html>