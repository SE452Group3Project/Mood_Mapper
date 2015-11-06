/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.GroupEntity;
import com.moodmapper.entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jasekurasz
 */
@WebServlet(name = "JoinGroupServlet", urlPatterns = {"/JoinGroupServlet"})
public class JoinGroupServlet extends HttpServlet {
    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private PrintWriter out;
    private HttpSession session;
    
    public JoinGroupServlet(){
        super(); 
    }
    
    @Override
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html"); 
      out = response.getWriter(); 
      session = request.getSession(true);
      
      //get the join code and find the group that corresponds to it
      String joinCode = request.getParameter("joinCode");
      Query findByJoinCode = em.createNamedQuery("GroupEntity.findByJoinCode").setParameter("joinCode", joinCode);
      GroupEntity group = (GroupEntity) findByJoinCode.getSingleResult();
      
      if(group != null){
        //get the user using the session
        UserEntity user = (UserEntity) session.getAttribute("user");

        //add current user to that group
        group.addGroupMember(user); 
        group.save(emf);

        String url = "/my_groups.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
      }
    }
}
