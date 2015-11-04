/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import com.moodmapper.entity.CommentEntity;
import com.moodmapper.entity.UserEntity;
import com.moodmapper.entity.MoodStatusEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Dave Messer
 */
@WebServlet(name = "AddCommentServlet", urlPatterns = {"/AddCommentServlet"})
public class AddCommentServlet extends HttpServlet {
    
    private EntityManagerFactory emf; 
    private EntityManager em;
    private UserEntity user;
    
    @Override
    public void init() {
      emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 
      em = emf.createEntityManager();
      user = new UserEntity();
    }

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
            out.println("<title>Servlet AddCommentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCommentServlet at " + request.getContextPath() + "</h1>");
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
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        //user = null;        
        if (session.getAttribute("user") != null) {
            user = (UserEntity)session.getAttribute("user");  
        } else {
            out.println("Please login first"); 
            response.sendRedirect("signup.jsp");
        }   
        
        Integer moodStatusID = (Integer) Integer.parseInt(request.getParameter("moodStatusID"));
        String comment = request.getParameter("comment");
        
        MoodStatusEntity moodStatus = em.find(MoodStatusEntity.class, moodStatusID); 
        UserEntity moodStatusOwner = moodStatus.getUser();
        UserEntity commenterID = (UserEntity)session.getAttribute("user");
        Long time = session.getLastAccessedTime();
        String stringTime = time.toString();
        Random random = new Random();
        Integer randomId = random.nextInt(Integer.MAX_VALUE);
        Integer tempId = new Integer(randomId);
        
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        //java.util.Date date= new java.util.Date();
        Date now = new Date();
        String strDate = sdfDate.format(now);
        Timestamp timeStamp= Timestamp.valueOf(strDate);
        
        // create a new Comment object
        CommentEntity newComment = new CommentEntity();
        
        newComment.setId(tempId); // Real id will be generated by the database, but a unique Integer for id must be set first so that setUser doesn't have a temper tantrum.
        newComment.setContent(comment);
        newComment.setTimeStamp(timeStamp);
        newComment.setUser(commenterID);
        newComment.setMoodStatus(moodStatus);
        newComment.save(emf);


        commenterID.addComment(newComment);
        commenterID.save(emf);
        
        moodStatus.addComment(newComment);
        moodStatus.save(emf);
        
        
        // store Comment object in the request object
        UserEntity updatedUser = em.find(UserEntity.class, user.getId());
        session.setAttribute("user", updatedUser);
        
        // forward request and response to jsp page
        String url = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        // response.sendRedirect("home.jsp");
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
