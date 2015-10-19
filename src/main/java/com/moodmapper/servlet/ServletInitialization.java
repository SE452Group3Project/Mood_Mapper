/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.servlet;

import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 *
 * @author faithfulokoye
 */
@WebListener

public class ServletInitialization implements ServletContextListener {
    
    /**
     * Default constructor.
     */
    public ServletInitialization() {
    }

    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        try {
//            ServletContext sc = event.getServletContext(); 
//            String datasourceString = sc.getInitParameter("datasource");
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            DataSource ds = (DataSource) envContext.lookup("datasourceString");
//            
//            
//            sc.setAttribute("datasourceObject", ds);

            Logger.getLogger("Creating Data Source /Initial Context");
           
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();           
            Logger.getLogger("Error occured");

            
        }
            Logger.getLogger("Made it outside the try/catch for data source/servlet initialization");
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }
    
}
