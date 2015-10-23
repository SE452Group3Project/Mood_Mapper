/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.GroupEntity;
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
@WebServlet(name = "SearchResultsServlet", urlPatterns = {"/SearchResultsServlet"})
//@WebServlet("/search-results")
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
        PrintWriter out = response.getWriter();
        out.write("doGet");
        processRequest(request, response);
        List<UserEntity> searchResults;// = (List)request.getAttribute("searchResults");
        List<GroupEntity> groupSearchResults;
        String searchType = "userSearch";//request.getParameter("type"); 
        String searchQuery = "Jenny";//request.getParameter("searchTerm"); 
        
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchQuery + "%");
        //List<UserEntity> 
        searchResults = query.getResultList();
        
        /*TypedQuery<GroupEntity> groupQuery = em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + searchQuery + "%");
       List<GroupEntity> groupSearchResults = groupQuery.getResultList();*/

       
            /*if(!searchResults.isEmpty()){
                System.out.println(searchResults.size() + "Results found!" );
                for (UserEntity result : searchResults) {
                    System.out.println(result.getUsername() + " is on MoodMapper!");
                    //System.out.println("Id: " + result.getId());
                }
            } else {
                System.out.println("No results found for " + searchQuery);
            }*/
           
        if(searchType == "userSearch"){
            //searchResults = (List)request.getAttribute("searchResults");
            System.out.println("User Search Results:");
            if(!searchResults.isEmpty()){
                for (UserEntity result : searchResults) {
                    System.out.println(result.getUsername() + " exists on MoodMapper!");
                    //System.out.println("Id: " + result.getId());
                }
            } else {
                System.out.println("No results found for " + searchQuery);
            }
        } else if(searchType == "groupSearch"){
            groupSearchResults = (List)request.getAttribute("searchResults");
            out.println("Group Search Results:");
            for (GroupEntity element : groupSearchResults) {
           out.println("Group " + element.getName() + " was found!");
           //System.out.println("Id:  " + element.getId());
               }
        } else {
            out.println("Invalid Search Type!"); 
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
