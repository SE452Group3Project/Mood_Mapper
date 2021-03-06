/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.moodmapper.entity.GroupEntity;
import com.moodmapper.entity.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dave Messer
 */
@WebServlet(name = "CreateGroupServlet", urlPatterns = {"/CreateGroupServlet"})
public class CreateGroupServlet extends HttpServlet {

    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    
    @Override
    public void init() {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get parameters from the request
        String groupName = request.getParameter("groupName");
//        UserEntity ownerID = new UserEntity();
//        ownerID.setEmail("email@email.com");
//        ownerID.setFirstName("ownerName");
        HttpSession session = request.getSession();
        UserEntity owner = (UserEntity) session.getAttribute("user");
        
        // create a new Group object
        GroupEntity newGroup = new GroupEntity();
        
        newGroup.setId(1);
        newGroup.setName(groupName);
        newGroup.setOwner(owner); 
        newGroup.create(emf); 
        
        // store Group object in the request object
        request.setAttribute("newGroup", newGroup);
        
        // forward request and response to jsp page
        String url = "/new_group_confirmation.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
