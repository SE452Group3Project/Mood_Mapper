<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:20:52 PM
    Author     : Dave Messer
--%>

<%@page import="java.util.Collection"%>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="com.moodmapper.entity.GroupEntity"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserEntity user = null;
    user = (UserEntity)session.getAttribute("user");  
     
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
                <% 
                    Collection<GroupEntity> groupsOwned = null;
                    Collection<GroupEntity> groupsJoined = null;
    
                        user = (UserEntity)session.getAttribute("user");
                        groupsOwned = user.getGroupsOwned();
                        groupsJoined = user.getGroupsJoined();
                   
                %>
                  
                <%
                    for (GroupEntity group: groupsOwned) {
                       String groupOwnedName = group.getName();
                       String groupOwnedID = group.getId().toString();
                       String groupJoinCode = group.getJoinCode();
                %>
                <tr>
                  <td class="mdl-data-table__cell--non-numeric">Owns <%= groupOwnedName%> with a Join Code of <span style="color: blue"> <%=groupJoinCode %>
                    <a href="group.jsp?groupname=<%= groupOwnedName%>"><i class="material-icons" style="float: right;">chevron_right</i></a>
                    <a href="DeleteGroupServlet?groupID=<%=groupOwnedID%>"><i class="material-icons" style="float: right; padding-right: 20px">delete</i></a>
                  </td>
                </tr>
                <% } %>
                
                <%
                    for (GroupEntity group: groupsJoined) {
                       String groupJoinedName = group.getName();
                       String groupJoinedID = group.getId().toString();
                %>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">Is a member of <%= groupJoinedName%> 
                    <a href="group.jsp?groupname=<%= groupJoinedName%>"><i class="material-icons" style="float: right;">chevron_right</i></a>
                    <a href="DeleteGroupServlet?groupID=<%=groupJoinedID%>"><i class="material-icons" style="float: right; padding-right: 20px">delete</i></a>
                  </td>
                </tr>
                <% } %>
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
  
      
    </div>
  </main>
 </body>
</html>
