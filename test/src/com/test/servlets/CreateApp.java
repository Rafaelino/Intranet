package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.utils.Employe;

/**
 * Servlet implementation class CreateApp
 */
@WebServlet("/CreateApp")
public class CreateApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		app.afficherTable();
		this.getServletContext().getRequestDispatcher( "/WEB-INF/CreateEmploye.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		Employe employe = new Employe((request.getParameter("nom")+request.getParameter("prenom")).replaceAll("\\s+",""),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("datedenaissance"),request.getParameter("email"),request.getParameter("adresse"),request.getParameter("motdepasse"));
		app.ajouterEmploye(employe);
		//System.out.println(request.getParameter("fname"));
		doGet(request, response);
	}

}
