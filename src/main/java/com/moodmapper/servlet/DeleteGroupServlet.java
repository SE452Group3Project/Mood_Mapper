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
import java.util.ArrayList;
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
@WebServlet(name = "DeleteGroupServlet", urlPatterns = {"/DeleteGroupServlet"})
public class DeleteGroupServlet extends HttpServlet {
    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private PrintWriter out;
    
    public DeleteGroupServlet(){
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
      HttpSession session = request.getSession();
      
      //get the group id and find the group that corresponds to it
      int groupID = Integer.parseInt(request.getParameter("groupID"));
      Query findById = em.createNamedQuery("GroupEntity.findById").setParameter("id", groupID);
      GroupEntity group = (GroupEntity) findById.getResultList().get(0);
      
      //get the user using the session
      UserEntity user = (UserEntity) session.getAttribute("user");
      
      //remove the user from that group
//      out.println(user.getGroupsJoined());
      group.removeGroupMember(user);
      group.save(emf);
      user.save(emf);
//      out.println(user.getGroupsJoined());

      // store Group object in the request object
      request.setAttribute("group", group);

      // forward request and response to jsp page
      String url = "/remove_group_confirmation.jsp";
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
      dispatcher.forward(request, response);
    }
    
}
