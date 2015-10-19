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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author faithfulokoye
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    
    public SignUpServlet(){
        super(); 
    }
    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private static UserEntity user; 
    
    @Override
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperDevPU--noDataSource"); 
      em = emf.createEntityManager(); 
      user = new UserEntity(); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html"); 
      
      PrintWriter out = response.getWriter(); 
      
      String username = request.getParameter("username"); 
      String password = request.getParameter("password"); 
      String email = request.getParameter("email"); 
      
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);
      
     
      if (user.hasUniqueEmail(emf)) {
          if (user.hasUniqueUsername(emf)) {
                 try {
                    user.create(emf);
                    out.println("Account created successfully. Please activate your account."); 
                 } catch (Exception e){
                     Logger.getLogger(Arrays.toString(e.getStackTrace())); 
                 }
          } else {
              out.println("Username has already been taken."); 
          }
      } else {
          out.println("Email already exists"); 
      }
     
      
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    
}
