package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Employe;
import com.test.beans.Project;
import com.test.beans.ProjectForEmploye;
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
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/acceuil.jsp" ).forward( request, response );}
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
				HttpSession session = request.getSession();
				System.out.println("-->"+session.getAttribute("admin"));
				if (!(session.getAttribute("admin").equals("yes"))){
					request.setAttribute("projects", app2.getProjectWhereManager(app2.getEmployeByUsername(((String) session.getAttribute("email")).split("@")[0])));
			}else{
				request.setAttribute("projects", app.getAllProjectsObjects());
			}
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateProject.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("saveproject")){
				HttpSession session = request.getSession();

				Project project	= new Project(request.getParameter("nom"),request.getParameter("description"));
				app.ajouterProjet(project);
				if (!(session.getAttribute("admin").equals("yes"))){
					request.setAttribute("projects", app2.getProjectWhereManager(app2.getEmployeByUsername(((String) session.getAttribute("email")).split("@")[0])));
			}else{
				request.setAttribute("projects", app.getAllProjectsObjects());
			}
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateProject.jsp" ).forward( request, response );
			}else if (request.getParameterValues("formname")[0].equals("selection")){
				String name = request.getParameter("listemploye");
				Project projet = app.getProjectByName(name);
				request.setAttribute("nom", projet.getName());
				request.setAttribute("description", projet.getDescription());
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",name);
				request.setAttribute("projects", app.getAllProjects());
				//app2.getEmployeForProject(app.getProjectByName(name))   Get EMploye not String !!!!
				System.out.println("--->"+app2.drawStringProjectBuilder((app.getProjectByName(name))));
				request.setAttribute("employedrawlisting",app2.drawStringProjectBuilder((app.getProjectByName(name))));
				request.setAttribute("element",request.getParameter("nom"));
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(name)));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(name)));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("addperiod")){
				String role = request.getParameter("listrole");
				String employeName = request.getParameter("listemploye");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("employename", employeName);
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("project",app.getProjectByName(request.getParameter("nom")));	
				System.out.println("->"+employeName);
				request.setAttribute("projectforemploye", app.getProjectForEmploye(app2.getEmployeByUsername(employeName), app.getProjectByName(request.getParameter("nom")), role));
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
				ProjectForEmploye newproject = new ProjectForEmploye(role, request.getParameter("nom"), datedebut, datefin, implication);
			if (app.isPeriodProjectOk(app2.getEmployeByUsername(employeName), newproject)){
				app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), datedebut, datefin,role,implication);
			    app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
			    }
						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app2.getAllEmployes());
						request.setAttribute("projects", app.getAllProjects());
						
						String employelist="";
					
						
							employelist += app2.drawStringProjectBuilder((app.getProjectByName(request.getParameter("nom"))));
						
						request.setAttribute("employedrawlisting", employelist);
						this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );			
			}else if(request.getParameterValues("formname")[0].equals("modificateperiod")){
				
				
				String role = request.getParameter("role");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("projectname",request.getParameter("nom"));
				//nom projet
				ProjectForEmploye oldproject = app.getProjectForEmploye(app2.getEmployeByNameReverse(employeName), app.getProjectByName(request.getParameter("nom")), role);
				request.setAttribute("projectforemploye", oldproject);
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
				ProjectForEmploye newproject = new ProjectForEmploye(role, request.getParameter("nom"), datedebut, datefin, implication);
			
				app.modifyProjectForEmploye(app.getProjectForEmploye(app2.getEmployeByNameReverse(request.getParameter("employename")), app.getProjectByName(request.getParameter("nom")),request.getParameter("role")), app.getProjectByName(request.getParameter("nom")), app2.getEmployeByNameReverse(request.getParameter("employename")), request.getParameter("datedebut"), request.getParameter("datefin"), request.getParameter("role"), request.getParameter("implication"));

						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app2.getAllEmployes());
						request.setAttribute("projects", app.getAllProjects());
						
						String employelist="";
					
					
							employelist += app2.drawStringProjectBuilder((app.getProjectByName(request.getParameter("nom"))));
						
						request.setAttribute("employedrawlisting", employelist);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp" ).forward( request, response );
				
				
			}else if(request.getParameterValues("formname")[0].equals("deleteperiod")){
				
				
				String role = request.getParameter("role");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("projectname",request.getParameter("nom"));
				//nom projet
				ProjectForEmploye oldproject = app.getProjectForEmployeDate(app2.getEmployeByNameReverse(employeName), app.getProjectByName(request.getParameter("nom")), role, datedebut, datefin);
				request.setAttribute("projectforemploye", oldproject);
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
			    app.deleteProjectForEmploye(oldproject, app2.getEmployeByNameReverse(request.getParameter("employename")));
			    //app2.deleteEmployeForProject( app.getEmployeByNameReverse(request.getParameter("employename")), app2.getProjectByName(request.getParameter("nom")), role);
						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app2.getAllEmployes());
						request.setAttribute("projects", app.getAllProjects());
					
						String employelist="";
					
						
							employelist += app2.drawStringProjectBuilder((app.getProjectByName(request.getParameter("nom"))));
					
						request.setAttribute("employedrawlisting", employelist);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp" ).forward( request, response );
				
				
			}else if(request.getParameterValues("formname")[0].equals("modificateform")){
				Project projet = new Project(request.getParameter("nom"),request.getParameter("description"));
				app.modifierProjet(projet);
				doGet(request, response);
				
				//Inutile 
			}else if(request.getParameterValues("formname")[0].equals("deleteproject")){
			
				request.setAttribute("projectname",request.getParameter("nom"));
				app.deleteProject(app.getProjectByName(request.getParameter("nom")));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/acceuil.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}else if(request.getParameterValues("formname")[0].equals("modifyprojectforemploye")){
				request.setAttribute("employename",request.getParameter("employename"));
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("project",app.getProjectByName(request.getParameter("nom")));
				request.setAttribute("projectforemploye", app.getProjectForEmploye(app2.getEmployeByName(request.getParameter("employename")), app.getProjectByName(request.getParameter("nom")), request.getParameter("role")));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateProjectForEmploye.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}else if(request.getParameterValues("formname")[0].equals("saveprojectforemploye")){
				request.setAttribute("employename",request.getParameter("employename"));
				request.setAttribute("projectname",request.getParameter("nom"));
				app.modifyEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByName(request.getParameter("employename")), request.getParameter("oldrole"), request.getParameter("role"));
				app.modifyProjectForEmploye(app.getProjectForEmploye(app2.getEmployeByName(request.getParameter("employename")), app.getProjectByName(request.getParameter("nom")),request.getParameter("oldrole")), app.getProjectByName(request.getParameter("nom")), app2.getEmployeByName(request.getParameter("employename")), request.getParameter("datedebut"), request.getParameter("datefin"), request.getParameter("role"), request.getParameter("implication"));
				request.setAttribute("project",app.getProjectByName(request.getParameter("nom")));
				request.setAttribute("projectforemploye", app.getProjectForEmploye(app2.getEmployeByName(request.getParameter("employename")), app.getProjectByName(request.getParameter("nom")), request.getParameter("role")));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/AcceuilProjet.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}
			else if(request.getParameterValues("formname")[0].equals("modificateteamform")){
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
				System.out.println("--->"+app2.drawStringProjectBuilder((app.getProjectByName(request.getParameter("nom")))));
				request.setAttribute("employedrawlisting",app2.drawStringProjectBuilder((app.getProjectByName(request.getParameter("nom")))));
				request.setAttribute("element",request.getParameter("nom"));

					
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateTeamFormProject.jsp").forward( request, response );
				
				
				//Page de modification des membres d'un projet 
			}else if(request.getParameterValues("formname")[0].equals("addteammember")){
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
				request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
				List <Employe> listemploye = app2.getAllEmployesObjects();
				for (int i = 0; i < listemploye.size(); i++) {
					//request.setAttribute("implication"+listemploye.get(i).getUsername(),app2.getEmployeImplication(app2.getEmployeByUsername(listemploye.get(i).getUsername())));
				}
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
				String implication = request.getParameter("implication");
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
			
				if (app2.getEmployeImplication(app2.getEmployeByUsername(employeName)) >= Integer.parseInt(implication.substring(0,implication.length()-1))){
					if(!(app.employeAlreadyInProject(app2.getEmployeByUsername(employeName),app.getProjectByName(request.getParameter("nom"))))){
						app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), datedebut, datefin,role,implication);
						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
					}
				}else{
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
					request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
					this.getServletContext().getRequestDispatcher( "/WEB-INF/AddTeamMemberFormProject.jsp").forward( request, response );
					String someMessage = "L'employé n'as pas de disponibilité suffisante";
					out.println("<html><head>");
					out.println("<script type='text/javascript'>");
					out.println("alert(" + "'" + someMessage + "'" + ");</script>");
					out.println("</head><body></body></html>");
				}
		
				
			}else if(request.getParameterValues("formname")[0].equals("addperiod")){
				String role = request.getParameter("role");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("employename", employeName);
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("project",app.getProjectByName(request.getParameter("nom")));
				request.setAttribute("projectforemploye", app.getProjectForEmploye(app2.getEmployeByName(employeName), app.getProjectByName(request.getParameter("nom")), role));
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
						app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), datedebut, datefin,role,implication);
						
						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						
						this.getServletContext().getRequestDispatcher( "/WEB-INF/AddPeriodProjectForEmploye.jsp").forward( request, response );			
			}else if(request.getParameterValues("formname")[0].equals("saveperiod")){
				String role = request.getParameter("oldrole");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("employees", app2.getAllEmployes());
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app.getAllProjects());
				request.setAttribute("project",app.getProjectByName(request.getParameter("nom")));
				ProjectForEmploye oldproject = app.getProjectForEmploye(app2.getEmployeByName(employeName), app.getProjectByName(request.getParameter("nom")), role);
				request.setAttribute("projectforemploye", oldproject);
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
				ProjectForEmploye newproject = new ProjectForEmploye(role, request.getParameter("nom"), datedebut, datefin, implication);
			
				if (app.isProjectForEmployeSamePeriod(oldproject,newproject)){
					
				}else{
				app2.addProjectForEmploye(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByName(employeName), datedebut, datefin,role,implication);
				}	
						request.setAttribute("collaborateurs",app.getAllWorkers(app.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app.getAllManagers(app.getProjectByName(request.getParameter("nom"))));
						
						this.getServletContext().getRequestDispatcher( "/WEB-INF/AddPeriodProjectForEmploye.jsp").forward( request, response );			
			}
			
			
			}
	}

}
