package com.test.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Coyote;

public class Connexion extends HttpServlet{
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
	}
}
