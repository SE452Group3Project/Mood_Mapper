<%-- 
    Document   : submitTest
    Created on : Oct 21, 2015, 11:39:56 AM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="mdl-card__supporting-text">
              <form action="SubmitTestServlet" method="post">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                     
                    
                  <input class="mdl-textfield__input" type="text" id="searchTerm" name="searchTerm" required />
                  <label class="mdl-textfield__label" for="searchBox">Enter Search Term</label>
                  <br/>
                  
                </div>
                  <div>
                      <label for="searchtype">
                        
                        <input type="radio" id = "searchType" name="searchType" value="usersearch"  for="searchBox" checked>Users<br>
                        <input type="radio" id = "searchType" name="searchType" value="groupsearch" for="searchBox">Groups
                    </label>
                      
                  </div>
                 <div class="mdl-card__actions mdl-card--border">
                    <input type="submit" value="Search" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                 </div>
              </form>
    </body>
</html>
