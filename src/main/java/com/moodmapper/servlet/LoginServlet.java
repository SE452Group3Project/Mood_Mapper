/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.UserEntity;
import com.moodmapper.validations.EmailValidator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author faithfulokoye
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private static UserEntity user; 
    
    
    public LoginServlet() {
       super(); 
    }
    

    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager(); 
      user = new UserEntity(); 
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html"); 
      
      PrintWriter out = response.getWriter(); 
      
      
      String usernameOrEmail = request.getParameter("username"); 
      String password = request.getParameter("password"); 
     
     
      if (EmailValidator.validate(usernameOrEmail)) {
          String email = usernameOrEmail; 
          user = UserEntity.loginByEmail(email, password, emf); 
      } else {
           String username = usernameOrEmail; 
            user = UserEntity.loginByUserName(username, password, emf); 
      }
      
     if (user == null){
         out.println("Username/Email and password do not match."); 
     } else {
         out.println("Login was successful"); 
     }
      
    

      
      
      
      
    }
    
    public static boolean isEmail(String usernameOrEmail) {
        return true; 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }
    
}
