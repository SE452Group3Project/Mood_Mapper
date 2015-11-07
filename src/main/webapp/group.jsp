<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:18:09 PM
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
    UserEntity user = null;
    if (session.getAttribute("user") != null) {
        user = (UserEntity)session.getAttribute("user");  
    } else {
        out.println("Please login first"); 
        response.sendRedirect("signup.jsp");
    } 
    
    EntityManagerFactory emf; 
    EntityManager em;
    
    emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
    em = emf.createEntityManager();
    
    String groupName  = request.getParameter("groupname");
    session.setAttribute("currentgroup", groupName);
    
    GroupEntity group = new GroupEntity();
    
    TypedQuery<GroupEntity> query = em.createNamedQuery("GroupEntity.findByGroupName", GroupEntity.class).setParameter("name", groupName);
    //em.refresh(group);
    group = query.getSingleResult();
    em.refresh(group);
    
    if (group == null){
        session.setAttribute("notice", "no group specified or found");  
        response.sendRedirect("my_groups.jsp");
    }
    
    List<UserEntity> users = (List)group.getGroupMembers();
    String pageTitle = group.getName();
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
              <h2 class="mdl-card__title-text " style="margin: 0 auto; text-align: center;"><%= group.getName() %> Group</h2>
              
            </div>
              <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width:100%;">
              <tbody>
                  <tr>
                   <td class="mdl-data-table__cell--non-numeric">
                       <strong><a href="group_mood_statuses.jsp?groupname=<%= groupName %>">Click here to see all of group <%= groupName %>'s mood statuses</a></strong>
                  </td> 
                </tr>
                  <%
                for(UserEntity groupUser : users){
                 String userName = groupUser.getUsername();
                 //Integer userId = groupUser.getId();
              
                %>
                  
                <tr>
                  <td class="mdl-data-table__cell--non-numeric"><%= userName %>
                    <a href=""><i class="material-icons" style="float: right;"><i id="unfollow" class="material-icons">person</i></a>
                  </td>
                </tr>
                <% 
                }
                %>
                
                
              </tbody>
            </table>
            <!-- Right aligned menu on top of button  -->
            <div class=""></div>

        </div>
      </main>
    </div>
  </body>
</body>
</html>
