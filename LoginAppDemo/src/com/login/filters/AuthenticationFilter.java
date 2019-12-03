package com.login.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */

public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	
	    HttpSession session=req.getSession(false);
	    String uri = req.getRequestURI();
	    System.out.println(" uri is "+uri);
	    System.out.println("uri: "+uri.endsWith("errorpage.jsp"));
	    if(session == null) {
	    	if((uri.endsWith("login.html") || uri.endsWith("login"))) {
	    		System.out.println("session null, uri: "+uri + "session: "+session);
		    	
		    	// pass the request along the filter chain
		    	chain.doFilter(request, response);
			} 
				 else if(uri.endsWith("errorPage.jsp")) { 
					 System.out.println("errorPage");
				 chain.doFilter(request, response); 
				 }
				 
	    	else {
	    		res.sendRedirect("login.html");
	    	}
	    }
	    else {
	    	if((uri.endsWith("login.html") || uri.endsWith("login"))) {
	    		System.out.println("session exists, page is login.html");
	    		res.sendRedirect("home.jsp");
	    	}else {
	    		chain.doFilter(request, response);
	    	}		    	
	    	
	    	// res.sendRedirect(uri);
	    }
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
