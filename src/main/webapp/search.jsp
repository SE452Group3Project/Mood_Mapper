<%-- 
    Document   : search
    Created on : Oct 21, 2015, 3:06:53 AM
    Author     : Owner
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String pageTitle = "Search";
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
              <h2 class="mdl-card__title-text">Search</h2>
            </div>
            <div class="mdl-card__supporting-text">
              <form action="SearchServlet" method="post">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                     
                    
                  <input class="mdl-textfield__input" type="text" id="searchTerm" name="searchTerm" required />
                  <label class="mdl-textfield__label" for="searchBox">Enter Search Term</label>
                  <br/>
                  
                </div>
                  <div>
                      <label for="searchtype">
                        
                        <!--<input type="radio" id = "searchType" name="searchType" value="usersearch"  for="searchBox" checked>Users<br>
                        <input type="radio" id = "searchType" name="searchType" value="groupsearch" for="searchBox">Groups -->
                    </label>
                      
                  </div>
                 <div class="mdl-card__actions mdl-card--border">
                     <input type="submit" value="Search" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                 </div>
              </form>
            </div>
          </div>
        </div>
      </main>

