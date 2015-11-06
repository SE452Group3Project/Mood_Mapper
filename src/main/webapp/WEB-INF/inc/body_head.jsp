<%-- 
    Document   : body_head
    Created on : Oct 22, 2015, 6:23:58 PM
    Author     : faithfulokoye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <main class="mdl-layout__content">
        <div class="page-content">
          <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--6-col mdl-cell--8-col-tablet" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand" style="margin: auto;">
              <h2 class="mdl-card__title-text"><%= (request.getParameter("body_heading") == null) ? "" : request.getParameter("body_heading")  %></h2>
            </div>
            <div class="mdl-card__supporting-text" style="margin: auto;" >
                
                   <div class="notice"><%= request.getParameter("notice") %> </div>
    <div class="notice error"><%=request.getParameter("error") %> </div>