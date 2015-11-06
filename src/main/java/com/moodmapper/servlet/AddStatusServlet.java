/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.MoodStatusEntity;
import com.moodmapper.entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
 * @author Suqing
 */
@WebServlet(name = "AddStatusServlet", urlPatterns = {"/AddStatusServlet"})
public class AddStatusServlet extends HttpServlet {

    private EntityManagerFactory emf;  
    //private EntityManager em;
    private UserEntity user;

    @Override
    public void init()
            throws ServletException {
        super.init(); 
        
        emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource");
        user = new UserEntity();
        //em = emf.createEntityManager();
    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
      
        HttpSession session = request.getSession();
        //user = null;        
        if (session.getAttribute("user") != null) {
            user = (UserEntity)session.getAttribute("user");  
        } else {
            out.println("Please login first"); 
            response.sendRedirect("signup.jsp");
        }    
        
        int energyRating = Integer.parseInt(request.getParameter("energy_level"));
        int pleasantnessRating = Integer.parseInt(request.getParameter("pleasantness_level"));
        String describeWord = request.getParameter("describe_word");
        String reason = request.getParameter("reason");
        String isPrivate = request.getParameter("is_private");
   
        
        MoodStatusEntity status = new MoodStatusEntity();
        
        // temp id value 
        
        status.setId(1);
        status.setEnergyRating(energyRating);
        status.setPleasantnessRating(pleasantnessRating);
        status.setDescriptiveWord(describeWord);
        status.setReflectiveParagraph(reason);
        
        if(isPrivate == "on")
            status.setIsPrivate(Boolean.TRUE);
        else status.setIsPrivate(Boolean.FALSE);
        
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        //java.util.Date date= new java.util.Date();
        Date now = new Date();
        String strDate = sdfDate.format(now);
        Timestamp complainDate= Timestamp.valueOf(strDate);
        
        status.setCreatedOn(complainDate);
        
        if (user != null) { 
            //first instantiate the user by calling it randomly: user.getUsername();
            //out.println("Saving your moodstatus, " + user.getUsername()); 
            status.setUser(user); 
        } 
       
        status.create(emf);
        
        response.sendRedirect("mood_maps.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
