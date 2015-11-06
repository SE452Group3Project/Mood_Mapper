<%-- 
    Document   : search_results
    Created on : Oct 21, 2015, 3:04:22 AM
    Author     : Owner
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page import="com.moodmapper.entity.MoodStatusEntity"%>
<%
    UserEntity user = null;
    if (session.getAttribute("user") != null) {
        user = (UserEntity)session.getAttribute("user");  
    } else {
        out.println("Please login first"); 
        response.sendRedirect("signup.jsp");
    }
  
String pageTitle = "Search Results";
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
          .search-card-square.mdl-card {
            width: 320px;
            /*height: 320px;*/
          }
          .demo-card-square > .mdl-card__title {
            color: #fff;
            background:
              url('../assets/demos/dog.png') bottom right 15% no-repeat #46B6AC;
          }
          </style>
          <div class="demo-card-square mdl-card mdl-shadow--2dp" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand">
               <c:choose>
                    <c:when test="${param.searchType=='userSearch'}">
                        <h2 class="mdl-card__title-text">User Search Results</h2>
                    <br />
                    </c:when>
                    
                    <c:when test="${param.searchType=='groupSearch'}">
                        <h2 class="mdl-card__title-text">Group Search Results</h2>
                    <br />
                    </c:when>

                    <c:otherwise>
                        <h2 class="mdl-card__title-text">Invalid Search Typy</h2>
                    <br />
                    </c:otherwise>
                </c:choose>
    
              
            </div>
            <div class="mdl-card__supporting-text">
                    <c:choose>
                            <c:when test="${param.searchType=='userSearch'}">
                                <h5>Results for "<c:out value="${param.searchTerm}"/>"</h5>
                                <br />
                            </c:when>
                    
                            <c:when test="${param.searchType=='groupSearch'}">
                                <h5>Results for "<c:out value="${param.searchTerm}"/>"</h5>
                                <br />
                            </c:when>

                            <c:otherwise>
                                <b>No Results</b>
                                <br />
                            </c:otherwise>
                    </c:choose>
                    <c:choose>
                    <c:when test="${fn:length(searchResults) gt 0}">
                        <c:choose>
                            <c:when test="${param.searchType=='userSearch'}">
                                <c:forEach items="${requestScope.searchResults}" var="element">
                                    <strong>
                                        The user <c:out value="${element.username}" /> has been found on Mood Mapper and has: <br/>
                                        <c:out value="${fn:length(element.moodStatuses)}" /> mood statuses,<br/>
                                        made <c:out value="${fn:length(element.comments)}" /> comments,<br/>
                                        is a member of <c:out value="${fn:length(element.groupsJoined)}" /> groups, <br/>
                                        and is the owner of <c:out value="${fn:length(element.groupsOwned)}" /> groups!
                                        
                                    </strong>
                                        </<br/><br/><hr/>
                                </c:forEach>
                                <br />
                            </c:when>
                    
                            <c:when test="${param.searchType=='groupSearch'}">
                                <c:forEach items="${requestScope.searchResults}" var="element">
                                    <strong>
                                        The group <c:out value="${element.name}"/> has been found on Mood Mapper<br/>
                                        and it has <c:out value="${fn:length(element.groupMembers)}" /> members!
                                    </strong>
                                    </<br/><br/><hr/>
                                </c:forEach>
                                <br />
                            </c:when>

                            <c:otherwise>
                                <strong>
                                    Invalid search type
                                </strong>
                                <br />
                            </c:otherwise>
                    </c:choose>
                    </c:when>
                    <c:otherwise>
                        <strong>
                            No results found for that search term.
                        </strong>
                        <br />
                    </c:otherwise>
                </c:choose>
                    
                <br/>
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


</body>
</html>
