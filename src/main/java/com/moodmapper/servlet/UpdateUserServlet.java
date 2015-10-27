/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author faithfulokoye
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/update_user"})
public class UpdateUserServlet extends HttpServlet {

    private static EntityManagerFactory emf; 
    private EntityManager em;
    private static UserEntity user; 
    
    @Override
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager(); 
      user = new UserEntity(); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html"); 
      
      PrintWriter out = response.getWriter(); 
      
      String first_name = request.getParameter("fname"); 
      String last_name = request.getParameter("lname"); 
      String new_password = request.getParameter("password"); 
      new_password = (new_password == null) ? "" : new_password; 
      String current_password = request.getParameter("current_password"); 
      
      UserEntity userObject = new UserEntity(); 
      userObject.setFirstName(first_name);
      userObject.setLastName(last_name);
      userObject.setPassword(new_password);

      
      HttpSession session = request.getSession(); 
      UserEntity user = (UserEntity) session.getAttribute("user");  
      out.println(current_password);
      if (user.verifyPassword(current_password)) {
          user.updateUser(userObject);
          user.save(emf);
          session.setAttribute("notice", "Profile updated successfully");
          String url = "/home.jsp";
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
      } else {
          session.setAttribute("error", "Password does not match our database");
          String url = "/user_profile.jsp";
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
          dispatcher.forward(request, response);
      }
     
      
      
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }


}
