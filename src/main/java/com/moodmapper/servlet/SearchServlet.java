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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Owner
 */
//@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
//@WebServlet("/search")
//@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static EntityManagerFactory emf; 
    private EntityManager em;
    private static UserEntity user; 
    private PrintWriter out;
    private HttpSession session;
    
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager(); 
      user = new UserEntity(); 
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //processRequest(request, response);
        
        PrintWriter out = response.getWriter();
        //out.println("doGet");
        //processRequest(request, response);
        List<UserEntity> searchResults;// = (List)request.getAttribute("searchResults");
        List<GroupEntity> groupSearchResults;
        String searchType = request.getParameter("searchType"); 
        String searchQuery = request.getParameter("searchTerm");
        
        
        
        TypedQuery<UserEntity> query; //= em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchQuery + "%");
        //List<UserEntity> 
        //searchResults = query.getResultList();
        
        TypedQuery<GroupEntity> groupQuery; // = em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + searchQuery + "%");
       //List<GroupEntity> groupSearchResults = groupQuery.getResultList();*/

       
            /*if(!searchResults.isEmpty()){
                System.out.println(searchResults.size() + "Results found!" );
                for (UserEntity result : searchResults) {
                    System.out.println(result.getUsername() + " is on MoodMapper!");
                    //System.out.println("Id: " + result.getId());
                }
            } else {
                System.out.println("No results found for " + searchQuery);
            }*/
            out.println("");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Results</h1>");
            out.println("<div>");
            
            if(searchType.equals( "userSearch")){
                query = em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchQuery + "%");
                searchResults = query.getResultList();
                System.out.println("User Search Results:");
                if(!searchResults.isEmpty()){
                    for (UserEntity result : searchResults) {
                        out.println(result.getUsername() + " is on MoodMapper <br/>");
                        out.println("<b>and is a part of " + result.getGroupsJoined().size() + " groups! </b><br/><br/>");

                    } 
                } else {
                    out.println("No results found for any user matching " + searchQuery);
                }
            } else if(searchType.equals( "groupSearch")){
                groupQuery = em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + searchQuery + "%");
                groupSearchResults = groupQuery.getResultList();//(List)request.getAttribute("searchResults");
                if(!groupSearchResults.isEmpty()){
                out.println("Group Search Results:");
                for (GroupEntity element : groupSearchResults) {
                out.println("Group " + element.getName() + " was found<br/>");
                out.println("<b>and it has " + element.getGroupMembers().size() + " members!</b><br/><br/>");

                   } 
                } else { out.println("No Groups matching that name!");}
            } else {
                out.write("Invalid Search Type!"); 
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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

        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType"); 
        
        TypedQuery<UserEntity> query; //= em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchTerm + "%");
        List<UserEntity> searchResults; //= query.getResultList();
        
        TypedQuery<GroupEntity> groupQuery; //= em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + searchTerm + "%");
        List<GroupEntity> groupSearchResults; //= groupQuery.getResultList();

       
           
        if(searchType == "userSearch"){
            query = em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + searchTerm + "%");
            searchResults = query.getResultList();
            request.setAttribute("searchResults", searchResults);
            
        } else if(searchType == "groupSearch"){
            
            groupQuery = em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + searchTerm + "%");
            groupSearchResults = groupQuery.getResultList();
            request.setAttribute("searchResults", groupSearchResults);
            
            /*System.out.println("User Search Results:");
            for (GroupEntity element : groupSearchResults) {
           System.out.println("Group " + element.getName() + " was found!");
           //System.out.println("Id:  " + element.getId());
               }*/
        } 
        //out.println("You searched for " + searchTerm);
        /*String url = "/search_results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);*/
        
        doGet(request, response);
        //String url = "/user_profile.jsp";
        /*String url = "/search-results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);*/
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
