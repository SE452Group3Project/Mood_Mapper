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
import java.util.Date;
import java.util.Enumeration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suqing
 */
@WebServlet(name = "AddStatusServlet", urlPatterns = {"/AddStatusServlet"})
public class AddStatusServlet extends HttpServlet {

    private EntityManagerFactory emf;  
    private EntityManager em;
   

    @Override
    public void init()
            throws ServletException {
        super.init(); 
        
        emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource");
        em = emf.createEntityManager();
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
        //processRequest(request, response);
        
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Enumeration<String> params = request.getParameterNames();
        String[] result = new String[5];
        int index = 0;
        while(params.hasMoreElements()) {
            String paramKey = params.nextElement();
            //out.println(paramKey);
            
            //String[] result = request.getParameterValues(paramKey);
            String[] keys = request.getParameterValues(paramKey);
            for(int i = 0; i < keys.length; i++) {
                result[index] = keys[0];
            }
            index++;
        }
        for(int i = 0; i < 5; i++) {
            out.println(result[i]);
        }
   
        UserEntity group_member1 = new UserEntity(2, "jenny", "23828937rdk", "2839797@2389.com"); 
        
        
        MoodStatusEntity status = new MoodStatusEntity();
        
        // temp id value 
        //status.setUser(group_member1);
        status.setId(1);
        status.setEnergyRating(Integer.parseInt(result[0]));
        status.setPleasantnessRating(Integer.parseInt(result[1]));
        status.setDescriptiveWord(result[2]);
        status.setReflectiveParagraph(result[3]);
        
        if(result[4] == "on")
            status.setIsPrivate(Boolean.TRUE);
        else status.setIsPrivate(Boolean.FALSE);
        Date date = new Date();
        status.setTimeStamp(new Timestamp(date.getTime()));
        
        out.println(status.toString());
        
        status.save(emf);
        emf.close();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
