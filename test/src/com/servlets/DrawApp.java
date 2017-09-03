 package com.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
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
 * Servlet implementation class DrawApp
 */
@WebServlet("/DrawApp")
public class DrawApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tools tools = new Tools();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawApp() {
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
	
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		CassandraProjetUtils app2 = new CassandraProjetUtils();
		request.setAttribute("employes",app.getAllEmployesObjects());
		request.setAttribute("employes",app.getAllEmployesObjects());
		
		request.setAttribute("employees", app.getAllEmployes());
		request.setAttribute("projects", app2.getAllProjects());
		List<String> projectlist = app2.getAllProjects();
		String employelist="";
	
		for (int i = 0; i < projectlist.size(); i++) {
			employelist += app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
		}
		System.out.println(employelist);

		request.setAttribute("employedrawlisting", employelist);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		CassandraProjetUtils app2 = new CassandraProjetUtils();
		request.setAttribute("employes",app.getAllEmployesObjects());
		request.setAttribute("employees",app.getAllEmployes());
		if(request.getParameterValues("formname")!=null){
			if(request.getParameterValues("formname")[0].equals("employes")){
				List<Employe> employelist = app.getAllEmployesObjects();
				request.setAttribute("employes",employelist);
				String projectlisting ="";
				for (int i = 0; i < employelist.size(); i++) {
					projectlisting += app.drawStringBuilder(employelist.get(i));
				}
				String[] projectListPerEmploye = projectlisting.split("@");
				for (int i = 0; i < projectListPerEmploye.length; i++) {
					//String uname = projectListPerEmploye[i].split(";")[0];
					//request.setAttribute(uname+"draw", projectListPerEmploye[i].split(";")[1]);
				
				}
				System.out.println(projectlisting);
				request.setAttribute("projectdrawlisting", projectlisting);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawEmploye.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("myprojects")){
				request.setAttribute("employees", app.getAllEmployes());
				request.setAttribute("projects", app2.getAllProjects());
				List<String> projectlist = app2.getAllProjects();
				String employelist="";
				HttpSession session = request.getSession();
				for (int i = 0; i < projectlist.size(); i++) {
					List<Employe> employelistspe = app.getEmployeForProject(app2.getProjectByName(projectlist.get(i)));
					for (int j = 0; j < employelistspe.size(); j++) {
						if (app.getEmployeByUsername(employelistspe.get(j).getUsername()).getUsername().equals(session.getAttribute("email").toString().split("@")[0])){
							String employetemp = app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
							employelist += employetemp;

						}
					}

					}
				
				
				request.setAttribute("employedrawlisting", employelist);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("addperiod")){
				String role = request.getParameter("listrole");
				String employeName = request.getParameter("listemploye");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("employees", app.getAllEmployes());
				request.setAttribute("employename", employeName);
				request.setAttribute("projectname",request.getParameter("nom"));
				request.setAttribute("projects", app2.getAllProjects());
				request.setAttribute("project",app2.getProjectByName(request.getParameter("nom")));	
				System.out.println("->"+employeName);
				request.setAttribute("projectforemploye", app2.getProjectForEmploye(app.getEmployeByUsername(employeName), app2.getProjectByName(request.getParameter("nom")), role));
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
				ProjectForEmploye newproject = new ProjectForEmploye(role, request.getParameter("nom"), datedebut, datefin, implication);
			if (app2.isPeriodProjectOk(app.getEmployeByUsername(employeName), newproject)){
				app.addProjectForEmploye(app2.getProjectByName(request.getParameter("nom")), app.getEmployeByUsername(employeName), datedebut, datefin,role,implication);
			    app2.addEmployeForProject(app2.getProjectByName(request.getParameter("nom")), app.getEmployeByUsername(employeName), role);
			    }
						request.setAttribute("collaborateurs",app2.getAllWorkers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app2.getAllManagers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app.getAllEmployes());
						request.setAttribute("projects", app2.getAllProjects());
						List<String> projectlist = app2.getAllProjects();
						String employelist="";
					
						for (int i = 0; i < projectlist.size(); i++) {
							employelist += app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
						}
						request.setAttribute("employedrawlisting", employelist);
						this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp").forward( request, response );			
			}else if(request.getParameterValues("formname")[0].equals("projects")){
				request.setAttribute("employees", app.getAllEmployes());
				request.setAttribute("projects", app2.getAllProjects());
				List<String> projectlist = app2.getAllProjects();
				String employelist="";
			
				for (int i = 0; i < projectlist.size(); i++) {
					employelist += app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
				}
				System.out.println(employelist);

				request.setAttribute("employedrawlisting", employelist);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("modificateperiod")){
				
				
				String role = request.getParameter("role");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("projectname",request.getParameter("nom"));
				//nom projet
				ProjectForEmploye oldproject = app2.getProjectForEmploye(app.getEmployeByNameReverse(employeName), app2.getProjectByName(request.getParameter("nom")), role);
				request.setAttribute("projectforemploye", oldproject);
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
				ProjectForEmploye newproject = new ProjectForEmploye(role, request.getParameter("nom"), datedebut, datefin, implication);
			
				app2.modifyProjectForEmploye(app2.getProjectForEmploye(app.getEmployeByNameReverse(request.getParameter("employename")), app2.getProjectByName(request.getParameter("nom")),request.getParameter("role")), app2.getProjectByName(request.getParameter("nom")), app.getEmployeByNameReverse(request.getParameter("employename")), request.getParameter("datedebut"), request.getParameter("datefin"), request.getParameter("role"), request.getParameter("implication"));

						request.setAttribute("collaborateurs",app2.getAllWorkers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app2.getAllManagers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app.getAllEmployes());
						request.setAttribute("projects", app2.getAllProjects());
						List<String> projectlist = app2.getAllProjects();
						String employelist="";
					
						for (int i = 0; i < projectlist.size(); i++) {
							employelist += app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
						}
						request.setAttribute("employedrawlisting", employelist);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );
				
				
			}else if(request.getParameterValues("formname")[0].equals("deleteperiod")){
				
				
				String role = request.getParameter("role");
				String employeName = request.getParameter("employename");
				String datedebut = request.getParameter("datedebut");
				String datefin = request.getParameter("datefin");
				String implication = request.getParameter("implication");
				request.setAttribute("projectname",request.getParameter("nom"));
				//nom projet
				ProjectForEmploye oldproject = app2.getProjectForEmployeDate(app.getEmployeByNameReverse(employeName), app2.getProjectByName(request.getParameter("nom")), role, datedebut, datefin);
				request.setAttribute("projectforemploye", oldproject);
						// Non nécessaire ??
						//app.addEmployeForProject(app.getProjectByName(request.getParameter("nom")), app2.getEmployeByUsername(employeName), role);
						//if '(periode distinctes if disponibilité intersection projet < dispo + 10
			    app2.deleteProjectForEmploye(oldproject, app.getEmployeByNameReverse(request.getParameter("employename")));
			    //app2.deleteEmployeForProject( app.getEmployeByNameReverse(request.getParameter("employename")), app2.getProjectByName(request.getParameter("nom")), role);
						request.setAttribute("collaborateurs",app2.getAllWorkers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("managers",app2.getAllManagers(app2.getProjectByName(request.getParameter("nom"))));
						request.setAttribute("employees", app.getAllEmployes());
						request.setAttribute("projects", app2.getAllProjects());
						List<String> projectlist = app2.getAllProjects();
						String employelist="";
					
						for (int i = 0; i < projectlist.size(); i++) {
							employelist += app.drawStringProjectBuilder((app2.getProjectByName(projectlist.get(i))));
						}
						request.setAttribute("employedrawlisting", employelist);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );
				
				
			}else if(request.getParameterValues("formname")[0].equals("downloadcsv")){
				response.setContentType("text/csv");
				if (request.getParameterValues("origin")[0].equals("employe")){
				    response.setHeader("Content-Disposition", "attachment; filename=\"Employees_projects_listing.csv\"");
				    List<Employe> employelist = app.getAllEmployesObjects();
				    try
				    {
				        OutputStream outputStream = response.getOutputStream();
				        String outputResult = "Employee;Project;Role;Implication;Start date;End date\n";
				        for (int i = 0; i < employelist.size(); i++) {
				        	outputResult += employelist.get(i).getNom() +" "+ employelist.get(i).getPrenom() +"\n";
				        	List<ProjectForEmploye> projectlist = app2.getProjectsForEmploye(employelist.get(i));
				        	for (int j = 0; j < projectlist.size(); j++) {
			        			outputResult += ";"+projectlist.get(j).getName()+"; "+projectlist.get(j).getRole()+";"+projectlist.get(j).getImplication()+";"+projectlist.get(j).getDateDebut()+";"+projectlist.get(j).getDateFin()+"\n";
							}
				        /*	List<Employe> employelistspe = app.getEmployeForProject(app2.getProjectByName(projectlist.get(i)));
				        		for (int j = 0; j < employelistspe.size(); j++) {
				        			ProjectForEmploye projectdetail = app2.getProjectForEmploye(employelistspe.get(j), app2.getProjectByName(projectlist.get(i)), "Chef de projet");
				        			if (projectdetail == null){
					        			projectdetail = app2.getProjectForEmploye(employelistspe.get(j), app2.getProjectByName(projectlist.get(i)), "Collaborateur");
				        			}if(projectdetail != null){
				        			outputResult += ";"+employelistspe.get(j).getNom()+" "+employelistspe.get(j).getPrenom()+";"+projectdetail.getRole()+";"+projectdetail.getDateDebut()+";"+projectdetail.getDateFin()+"\n";
				        			}	
				        		}*/
				        }
				        outputStream.write(outputResult.getBytes());
				        outputStream.flush();
				        outputStream.close();
				    }
				    catch(Exception e)
				    {
				    	e.printStackTrace(System.out);
				    }
				}else{
			    response.setHeader("Content-Disposition", "attachment; filename=\"Project_employees_listing.csv\"");
				List<String> projectlist = app2.getAllProjects();
			    try
			    {
			        OutputStream outputStream = response.getOutputStream();
			        String outputResult = "Project;Employee;Role;Implication;Start date;End date\n";
			        for (int i = 0; i < projectlist.size(); i++) {
			        	outputResult += app2.getProjectByName(projectlist.get(i)).getName()+"\n";
			        	List<Employe> employelistspe = app.getEmployeForProject(app2.getProjectByName(projectlist.get(i)));
			        		for (int j = 0; j < employelistspe.size(); j++) {
			        			List<ProjectForEmploye> projectdetail = app2.getProjectsForEmploye(employelistspe.get(j), app2.getProjectByName(projectlist.get(i)));
			        			if(!(projectdetail.isEmpty())){
			        				for (int k = 0; k <projectdetail.size(); k++) {
					        			outputResult += ";"+employelistspe.get(j).getNom()+" "+employelistspe.get(j).getPrenom()+";"+projectdetail.get(k).getRole()+";"+projectdetail.get(k).getImplication()+";"+projectdetail.get(k).getDateDebut()+";"+projectdetail.get(k).getDateFin()+"\n";
										}
				        			}
			        			/*projectdetail = app2.getProjectForEmploye(employelistspe.get(j), app2.getProjectByName(projectlist.get(i)), "Collaborateur");
			        			if (projectdetail != null){
				        			outputResult += ";"+employelistspe.get(j).getNom()+" "+employelistspe.get(j).getPrenom()+";"+projectdetail.getRole()+";"+projectdetail.getImplication()+";"+projectdetail.getDateDebut()+";"+projectdetail.getDateFin()+"\n";
			        			}*/	
			        		}
			        }
			        outputStream.write(outputResult.getBytes());
			        outputStream.flush();
			        outputStream.close();
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace(System.out);
			    }
			}
				}else if(request.getParameterValues("formname")[0].equals("exportdata")){
					response.setContentType("text/plain");
				
				
			
					response.setHeader("Content-Disposition", "attachment; filename=\"database_export.cql\"");
					
					    try
					    {
					        OutputStream outputStream = response.getOutputStream();
					        String outputResult = "";
					        List<Employe> employelist = app.getAllEmployesObjects();
					        for (int i = 0; i < employelist.size(); i++) {
								List<ProjectForEmploye> projectlist = app2.getProjectsForEmploye(employelist.get(i));
								String projectlisting ="[ ";
								for (int j = 0; j < projectlist.size(); j++) {
									projectlisting +="{name: '"+projectlist.get(j).getName()+"', role: '"+projectlist.get(j).getRole()+"', datedebut: '"+projectlist.get(j).getDateDebut()+"', datefin: '"+projectlist.get(j).getDateFin()+"', implication :'"+projectlist.get(j).getImplication()+"'}," ; 
								}
								projectlisting = projectlisting.substring(0, projectlisting.length()-1);
								projectlisting +="]";
								outputResult += "INSERT INTO myfirstcassandradb.employees (username,adresse,datedenaissance,email,nom,password,prenom,projectslist,root) VALUES ('"+employelist.get(i).getUsername()+"','"+employelist.get(i).getAdresse()+"','"+employelist.get(i).getDateDeNaissance()+"', '"+employelist.get(i).getEmail()+"', '"+employelist.get(i).getNom()+"', '"+employelist.get(i).getPassword()+"', '"+employelist.get(i).getPrenom()+"', "+projectlisting+"	,'"+employelist.get(i).getAdmin()+"');\n\n";	   

							}
					      
					        List<Project> projectlist = app2.getAllProjectsObjects();
					        for (int i = 0; i < projectlist.size(); i++) {
								outputResult += "INSERT INTO myfirstcassandradb.projects (name,description,employelist) VALUES ('"+projectlist.get(i).getName()+"','"+projectlist.get(i).getDescription()+"',"+app2.getAllProjectsForExport(projectlist.get(i))+");\n\n";	   

							}
					      
					        outputResult = Normalizer.normalize(outputResult, Normalizer.Form.NFD);
					        outputResult = outputResult.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
					           
					        
					        outputStream.write(outputResult.getBytes("CP1252"));
					        outputStream.flush();
					        outputStream.close();
					    }
					    catch(Exception e)
					    {
					    	e.printStackTrace(System.out);
					    }
					
					}
		}
	}

}
