package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Employe;
import com.utils.CassandraEmployeUtils;
import com.utils.Tools;

/**
 * Servlet implementation class CreateApp
 */
@WebServlet("/CreateApp")
public class EmployeApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tools tools = new Tools();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		HttpSession session = request.getSession();
		if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
	
		request.setAttribute("admin", app.getEmployeByUsername(session.getAttribute("email").toString().split("@")[0]).getAdmin());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/acceuil.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		HttpSession session = request.getSession();
		request.setAttribute("admin", app.getEmployeByUsername(session.getAttribute("email").toString().split("@")[0]).getAdmin());
		if(request.getParameterValues("formname")!=null){
			if(request.getParameterValues("formname")[0].equals("create")){
				this.getServletContext().getRequestDispatcher( "/WEB-INF/CreateEmploye.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("modificate")){
				request.setAttribute("employees", app.getAllEmployes());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateEmployee.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("saveemploye")){
			Employe employe = new Employe(request.getParameter("email").split("@")[0],request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("datedenaissance"),request.getParameter("email"),request.getParameter("adresse"),request.getParameter("motdepasse"));
			app.ajouterEmploye(employe);
			request.setAttribute("creation", "Employé crée avec succés");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/CreateEmploye.jsp" ).forward( request, response );}
			else if(request.getParameterValues("formname")[0].equals("suppression")){
				String name = request.getParameter("nom");
				app.supprimerEmploye(app.getEmployeByUsername(name));
				request.setAttribute("employees", app.getAllEmployes());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateEmployee.jsp" ).forward( request, response );
			}
			else if (request.getParameterValues("formname")[0].equals("selection")){
				String name = request.getParameter("listemploye");
				Employe employe = app.getEmployeByUsername(name);
				request.setAttribute("username", name);
				request.setAttribute("nom", employe.getNom());
				request.setAttribute("prenom", employe.getPrenom());
				request.setAttribute("email", employe.getEmail());
				request.setAttribute("adresse", employe.getAdresse());
				System.out.println(employe.getPassword());
				request.setAttribute("motdepasse", employe.getPassword());
				request.setAttribute("employe", employe);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateFormEmployee.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("modificateform")){
				Employe employe = new Employe(request.getParameter("email").split("@")[0],request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("datedenaissance"),request.getParameter("email"),request.getParameter("adresse"),request.getParameter("admindata"));
				System.out.println("+++"+employe.getAdmin());
				request.setAttribute("employees", app.getAllEmployes());
				app.modifierEmploye(employe);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateEmployee.jsp" ).forward( request, response );

			}
			}	
		
	}

}
