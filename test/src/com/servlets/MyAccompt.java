package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Employe;
import com.beans.Project;
import com.beans.ProjectForEmploye;
import com.utils.CassandraEmployeUtils;
import com.utils.CassandraProjetUtils;
import com.utils.Tools;

/**
 * Servlet implementation class MyAccompt
 */
@WebServlet("/MyAccompt")
public class MyAccompt extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Tools tools = new Tools();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAccompt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CassandraEmployeUtils app1 = new CassandraEmployeUtils();
		CassandraProjetUtils app21 = new CassandraProjetUtils();
		HttpSession session = request.getSession();
		if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
		if(request.getParameter("name")!= null){
			
		
			CassandraEmployeUtils app11 = new CassandraEmployeUtils();
			CassandraProjetUtils app211 = new CassandraProjetUtils();
			if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
			Employe me = app11.getEmployeByUsername(request.getParameter("name").split(" ")[1].substring(0,1).toLowerCase()+request.getParameter("name").split(" ")[0].toLowerCase());
			request.setAttribute("admin", me.getAdmin());
			request.setAttribute("employe", me);
			request.setAttribute("disponibilité", app11.getEmployeImplication(me));
			List<ProjectForEmploye> projectforemploye = app211.getProjectsForEmploye(me);
			request.setAttribute("projects",projectforemploye);
			for (int i = 0; i < projectforemploye.size(); i++) {
				request.setAttribute("employelist"+projectforemploye.get(i).getName(),app11.getEmployeForEmpProject(projectforemploye.get(i)));
				System.out.println("->"+"employelist"+projectforemploye.get(i).getName());
			}
			this.getServletContext().getRequestDispatcher( "/WEB-INF/viewaccompt.jsp" ).forward( request, response );
		}
		}
		Employe me = app1.getEmployeByUsername(session.getAttribute("email").toString().split("@")[0]);
		request.setAttribute("admin", me.getAdmin());
		request.setAttribute("employe", me);
		request.setAttribute("disponibilité", app1.getEmployeImplication(me));
		List<ProjectForEmploye> projectforemploye = app21.getProjectsForEmploye(me);
		request.setAttribute("projects",projectforemploye);
		for (int i = 0; i < projectforemploye.size(); i++) {
			request.setAttribute("employelist"+projectforemploye.get(i).getName(),app1.getEmployeForEmpProject(projectforemploye.get(i)));
			System.out.println("->"+"employelist"+projectforemploye.get(i).getName());
		}
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/MyAccompt.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("term"));
		if (request.getParameter("term") != null){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AcceuilEmploye.jsp").forward( request, response );
		}else{
			
		
		if(request.getParameterValues("formname")[0].equals("logout")){
			HttpSession session = request.getSession();
			tools.logout(session, this.getServletContext(), request, response);
		}else if(request.getParameterValues("formname")[0].equals("modificate")){
			HttpSession session = request.getSession();
			if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
				CassandraEmployeUtils app2 = new CassandraEmployeUtils();
				CassandraProjetUtils app = new CassandraProjetUtils();
				String name = request.getParameter("projectname");
				Project projet = app.getProjectByName(name);
				request.setAttribute("nom", projet.getName());
				request.setAttribute("description", projet.getDescription());
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",name);
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(name)));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(name)));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );
			}
			
		}else if(request.getParameterValues("formname")[0].equals("modificateemploye")){
			HttpSession session = request.getSession();
			if (!(session.getAttribute("email") == null)){
			String userEmail = (String) session.getAttribute("email");
			String name = userEmail.split("@")[0];
			CassandraEmployeUtils app = new CassandraEmployeUtils();
			Employe employe = app.getEmployeByUsername(name);
			request.setAttribute("employe", employe);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateFormEmployee.jsp" ).forward( request, response );
			}
			
		}else if(request.getParameterValues("formname")[0].equals("addteammember")){
			CassandraEmployeUtils app2 = new CassandraEmployeUtils();
			CassandraProjetUtils app = new CassandraProjetUtils();
			request.setAttribute("employees", app2.getAllEmployes());
			request.setAttribute("projectname",request.getParameter("nom"));
			request.setAttribute("projects", app.getAllProjects());
			//System.out.println("->"+app.getProjectByName(request.getParameter("nom")).getNom());
			//System.out.println(app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
			request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
			request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));

			this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
			
			
			
			//Suppresion d'un manager dans un projet				
		}else if(request.getParameterValues("formname")[0].equals("deleteteammember")){
			CassandraEmployeUtils app2 = new CassandraEmployeUtils();
			CassandraProjetUtils app = new CassandraProjetUtils();
			request.setAttribute("employees", app2.getAllEmployes());
			request.setAttribute("projectname",request.getParameter("nom"));
			request.setAttribute("projects", app.getAllProjects());
			String employeName = request.getParameter("employename");
			app.deleteEmployeForProject(app2.getEmployeByName(employeName), app.getProjectByName(request.getParameter("nom")), "Chef de projet");
			app.deleteProjectForEmploye(app.getProjectForEmploye(app2.getEmployeByName(employeName),app.getProjectByName(request.getParameter("nom")), "Chef de projet"), app2.getEmployeByName(employeName));
			request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
			request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
			
			//Suppresion d'un collaborateur dans un projet				
		}else if(request.getParameterValues("formname")[0].equals("deleteteammemberchef")){
			CassandraEmployeUtils app2 = new CassandraEmployeUtils();
			CassandraProjetUtils app = new CassandraProjetUtils();
			request.setAttribute("employees", app2.getAllEmployes());
			request.setAttribute("projectname",request.getParameter("nom"));
			request.setAttribute("projects", app.getAllProjects());
			String employeName = request.getParameter("employename");
			app.deleteEmployeForProject(app2.getEmployeByName(employeName), app.getProjectByName(request.getParameter("nom")), "Collaborateur");
			app.deleteProjectForEmploye(app.getProjectForEmploye(app2.getEmployeByName(employeName),app.getProjectByName(request.getParameter("nom")), "Collaborateur"), app2.getEmployeByName(employeName));
			request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
			request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
			
			
			//Sauvegarde d'un ajout de collaborateur/manager dans un projet
		}else if(request.getParameterValues("formname")[0].equals("saveteammember")){
			CassandraEmployeUtils app2 = new CassandraEmployeUtils();
			CassandraProjetUtils app = new CassandraProjetUtils();
			String role = request.getParameter("role");
			String employeName = request.getParameter("listemploye");
			String datedebut = request.getParameter("datedebut");
			String datefin = request.getParameter("datefin");
			String implication = request.getParameter("implication");
			request.setAttribute("employees", app2.getAllEmployes());
			request.setAttribute("projectname",request.getParameter("nom"));
			request.setAttribute("projects", app.getAllProjects());
			if(!(app.employeAlreadyInProject(app2.getEmployeByUsername(employeName),app.getProjectByName(request.getParameter("nom"))))){
				app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
				app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), datedebut, datefin,role,implication);
			}
			request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
			request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
		}else if(request.getParameterValues("formname")[0].equals("modificateform")){
			CassandraEmployeUtils app = new CassandraEmployeUtils();
			Employe employe = new Employe(request.getParameter("email").split("@")[0],request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("datedenaissance"),request.getParameter("email"),request.getParameter("adresse"),request.getParameter("admindata"));
			app.modifierEmploye(employe);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/MyAccompt.jsp").forward( request, response );
		}else if(request.getParameterValues("formname")[0].substring(0, 1).equals("!")){
			CassandraEmployeUtils app = new CassandraEmployeUtils();
			CassandraProjetUtils app2 = new CassandraProjetUtils();
			HttpSession session = request.getSession();
			if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
			Employe me = app.getEmployeByUsername(request.getParameterValues("formname")[0].substring(1, request.getParameterValues("formname")[0].length()));
			request.setAttribute("admin", me.getAdmin());
			request.setAttribute("employe", me);
			request.setAttribute("disponibilité", app.getEmployeImplication(me));
			List<ProjectForEmploye> projectforemploye = app2.getProjectsForEmploye(me);
			request.setAttribute("projects",projectforemploye);
			for (int i = 0; i < projectforemploye.size(); i++) {
				request.setAttribute("employelist"+projectforemploye.get(i).getName(),app.getEmployeForEmpProject(projectforemploye.get(i)));
				System.out.println("->"+"employelist"+projectforemploye.get(i).getName());
			}
			this.getServletContext().getRequestDispatcher( "/WEB-INF/viewaccompt.jsp" ).forward( request, response );
		}
		doGet(request, response);
	}
	}
}
	}
