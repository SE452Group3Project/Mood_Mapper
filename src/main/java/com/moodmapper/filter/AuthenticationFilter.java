package com.moodmapper.filter;

import com.moodmapper.entity.UserEntity;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faithful.okoye
 */

  
/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter("*.jsp")

public class AuthenticationFilter implements Filter {
 
	private ServletContext context;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
                HttpSession session = (HttpSession) req.getSession();
		String servletPath = req.getServletPath();
                
                
                System.err.println("servlet path: " + servletPath);

                if (servletPath.equals("/signup.jsp") || servletPath.equals("/index.jsp" ) || servletPath.equals("/about.jsp")){
                      chain.doFilter(req, resp);
                      return; 
                 }
                
                UserEntity user = null;
                if (session.getAttribute("user") != null) {
                    chain.doFilter(req, resp); 
                    return;
                } else {
                    resp.getWriter().println("Please login first"); 
                    resp.sendRedirect("signup.jsp");
                }

	}
	
    
}
