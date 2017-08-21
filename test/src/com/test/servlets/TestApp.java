package com.test.servlets;
import com.test.beans.Employe;
import com.test.utils.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestApp
 */
@WebServlet("/TestApp")
public class TestApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Tools tools = new Tools();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		/*if(app.ajouterEmploye(employe)){
			request.setAttribute("employe", "Nouvel employé ajouté à la base");
		}else{request.setAttribute("employe", "Employé déjà présent dans la base");
		}*/
		HttpSession session = request.getSession();
		if (!(session.getAttribute("email") == null)){
		String userEmail = (String) session.getAttribute("email");
		if (userEmail.split("@")[1].equals("everbe.com")){
			request.setAttribute("admin", session.getAttribute("admin"));
			Boolean projectmanager = app.isAProjectManager(app.getEmployeByUsername(userEmail.split("@")[0]));
			request.setAttribute("projectmanager", projectmanager);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/acceuil.jsp" ).forward( request, response );
		}else{
			session.invalidate();
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Reauthentification.jsp" ).forward( request, response );
		}
		
	}else{
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Reauthentification.jsp").forward( request, response );}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		System.out.println(request.getParameterValues("formname"));
		if(request.getParameterValues("formname") != null){
			if (request.getParameterValues("formname")[0].equals("modificate")){
				request.setAttribute("employees", app.getAllEmployes());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateEmployee.jsp" ).forward( request, response );
			}
			else if(request.getParameterValues("formname")[0].equals("modificateform")){
				Employe employe = new Employe(request.getParameter("email").split("@")[0],request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("datedenaissance"),request.getParameter("email"),request.getParameter("adresse"),request.getParameter("motdepasse"));
				app.modifierEmploye(employe);
				doGet(request, response);
			}
			else if (request.getParameterValues("formname")[0].equals("selection")){
				String name = request.getParameter("listemploye");
				Employe employe = app.getEmployeByName(name);
				request.setAttribute("nom", employe.getNom());
				request.setAttribute("prenom", employe.getPrenom());
				request.setAttribute("email", employe.getEmail());
				request.setAttribute("adresse", employe.getAdresse());
				System.out.println(employe.getPassword());
				request.setAttribute("motdepasse", employe.getPassword());
				request.setAttribute("datedenaissance", employe.getDateDeNaissance());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateFormEmployee.jsp" ).forward( request, response );
			}else if(request.getParameterValues("formname")[0].equals("logout")){
				HttpSession session = request.getSession();
				tools.logout(session, this.getServletContext(), request, response);
			}else{
			request.setAttribute("buttonName", "Youslicked");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/acceuil.jsp" ).forward( request, response );}
		}
	}

}