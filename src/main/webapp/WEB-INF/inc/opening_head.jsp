<%-- 
    Document   : opening_head
    Created on : Oct 21, 2015, 5:04:50 PM
    Author     : faithfulokoye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- Header -->
<head>

   <meta charset="utf-8">
   <meta name="google-signin-client_id" content="823629571123-jnn58qjcabua37341qvb2co1foorh5n7.apps.googleusercontent.com">

   <!-- Google OAuth -->
   <script src="https://apis.google.com/js/platform.js" async defer></script>

   <!-- Material Design Lite -->
   <script src="https://storage.googleapis.com/code.getmdl.io/1.0.5/material.min.js"></script>
   <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.5/material.blue_grey-blue.min.css" /> 

   <!-- Material Design icon font -->
   <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <!-- D3.js Library -->
    <!--<script src="http://d3js.org/d3.v3.min.js"></script>-->

    <title>Mood Mapper | <%=request.getParameter("pageTitle")%></title>
