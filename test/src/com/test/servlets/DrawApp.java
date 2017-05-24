package com.test.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Employe;
import com.test.utils.CassandraEmployeUtils;
import com.test.utils.Tools;

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
		request.setAttribute("employes",app.getAllEmployesObjects());
		request.setAttribute("employes",app.getAllEmployesObjects());
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/ProjectRepartition.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		request.setAttribute("employes",app.getAllEmployesObjects());
		request.setAttribute("employes",app.getAllEmployesObjects());
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
				
					//System.out.println(projectListPerEmploye[i].split(";")[1]);
				}
				System.out.println(projectlisting);
				request.setAttribute("projectdrawlisting", projectlisting);
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawEmploye.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("projects")){
				request.setAttribute("employees", app.getAllEmployes());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/DrawProject.jsp" ).forward( request, response );
			}
		}
	}

}
