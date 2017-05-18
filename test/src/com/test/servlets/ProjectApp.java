package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Employe;
import com.test.beans.Project;
import com.test.utils.CassandraEmployeUtils;
import com.test.utils.CassandraProjetUtils;
import com.test.utils.Tools;

/**
 * Servlet implementation class ProjectApp
 */
@WebServlet("/ProjectApp")
public class ProjectApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tools tools = new Tools();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		HttpSession session = request.getSession();
		if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/AcceuilProjet.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CassandraProjetUtils app = new CassandraProjetUtils();
		CassandraEmployeUtils app2 = new CassandraEmployeUtils();
		if(request.getParameterValues("formname")!=null){
			if(request.getParameterValues("formname")[0].equals("create")){
				this.getServletContext().getRequestDispatcher( "/WEB-INF/CreateProject.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("modificate")){
				request.setAttribute("projects", app.getAllProjects());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateProject.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("saveproject")){
				Project project	= new Project(request.getParameter("nom"),request.getParameter("description"));
				app.ajouterProjet(project);
			}else if (request.getParameterValues("formname")[0].equals("selection")){
				String name = request.getParameter("listemploye");
				Project projet = app.getProjectByName(name);
				request.setAttribute("nom", projet.getNom());
				request.setAttribute("description", projet.getDescription());
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",name);
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(name)));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(name)));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("modificateform")){
				Project projet = new Project(request.getParameter("nom"),request.getParameter("description"));
				app.modifierProjet(projet);
				doGet(request, response);
				
				//Inutile 
			}else if(request.getParameterValues("formname")[0].equals("deleteproject")){
			
				request.setAttribute("projectname",request.getParameter("nom"));
				app.deleteProject(app.getProjectByName(request.getParameter("nom")));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/AcceuilProjet.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}else if(request.getParameterValues("formname")[0].equals("modificateteamform")){
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}else if(request.getParameterValues("formname")[0].equals("addteammember")){
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
				String role = request.getParameter("role");
				String employeName = request.getParameter("listemploye");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				if(!(app.employeAlreadyInProject(app2.getEmployeByUsername(employeName),app.getProjectByName(request.getParameter("nom"))))){
					app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
					app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), datedebut, datefin,role);
				}
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
			}
			
			
			}
	}

}
