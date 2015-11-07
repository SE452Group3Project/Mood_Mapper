<%-- 
    Document   : index
    Created on : Nov 6, 2015, 10:32:09 AM
    Author     : faithful okoye
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
    
    String pageTitle = "About"; 

    String error = ""; 
    String notice = ""; 
    
%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/inc/head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
    <jsp:include page="/WEB-INF/inc/background.jsp"/>
    
    <% if (session.getAttribute("user") == null) { %>
        <jsp:include page="/WEB-INF/inc/navbarhome.jsp"/>
    <% } else { %>
        <jsp:include page="/WEB-INF/inc/navbar.jsp"/>
    <% } %>
    
     <jsp:include page="/WEB-INF/inc/body_head.jsp" flush="true">
        <jsp:param name="error" value="<%=error%>" />
        <jsp:param name="notice" value="<%=notice%>" />
    </jsp:include>
    
    <h3 style="text-align: center;">Mood Mapper</h3>
    <p>The Mood Mapper project is an educational app that facilitates the development of emotional intelligence for students.  Based on the “Mood Meter” work from Yale University, the Mood Mapper tracks students’ emotional states by asking students to identify their energy level, identify their pleasantness, select a word to describe their mood, and describe what has made them feel the way they feel.  By reflecting on their mood, students are able to become aware of their emotional states and the causes of those emotional states.  As students continue to practice of developing emotional awareness, they will learn to better describe and regulate their emotions.  As an additional features, teachers are able to see the emotional ‘status’ of their students.  By offering data on how students are feeling, teachers can better support students emotionally by targeting who to check in with and offer additional attention.</</p>
    <p style="font-style: italic; font-weight:300; text-align: center;">Developed by DePaul University students: <br/>Geoff Brown, Suqing Huang, Jase Kurasz, David Messer, Uchenna Okoye </p>
                
<jsp:include page="/WEB-INF/inc/footer.jsp"/>
