package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.RequestDispatcher;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  	
		
		String name= request.getParameter("userName");
    	String password=request.getParameter("password");
		
		System.out.println("in controller, name: "+request.getParameter("userName") + "password: " +password);
		
		// Check whether user is authorized or not
		if (name.equals("admin") && password.equals("admin")) {

			// out.println("in controller, userName" + name);
			
			HttpSession session=request.getSession();
			
			System.out.println("session created: "+session);
			
	    	session.setAttribute("userName", request.getParameter("userName"));
	    	session.setAttribute("password", request.getParameter("password"));

			response.sendRedirect("home.jsp");

		} else {
			System.out.println("not an authorised user");
			response.sendRedirect("errorPage.jsp");
						
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
