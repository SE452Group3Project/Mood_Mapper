/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
//@WebServlet(name = "SearchResultsServlet", urlPatterns = {"/SearchResultsServlet"})
@WebServlet("/search-results")
public class SearchResultsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchResultsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchResultsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private static UserEntity user; 
    
    
    public SearchResultsServlet() {
       super(); 
    }
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager(); 
      user = new UserEntity(); 
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
        processRequest(request, response);
        
        String searchType = request.getParameter("type"); 
        String searchQuery = request.getParameter("query"); 
        
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchQuery + "%");
        List<UserEntity> searchResults = query.getResultList();
        
        
        if(searchType == "userSearch"){
            System.out.println("User Search Results:");
            if(!searchResults.isEmpty()){
                for (UserEntity result : searchResults) {
                    System.out.println(result.getUsername() + " is available on MoodMapper!");
                    System.out.println("Id: " + result.getId());
                }
            } else {
                System.out.println("No results found for " + searchQuery);
            }
        } else if(searchType == "groupSearch"){
            System.out.println("User Search Results:");
        } else {
            System.out.println("Invalid Search Type!"); 
        }
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
        processRequest(request, response);
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
