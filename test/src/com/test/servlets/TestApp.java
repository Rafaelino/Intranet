package com.test.servlets;
import com.test.utils.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestApp
 */
@WebServlet("/TestApp")
public class TestApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		Employe employe = new Employe("RadianFetita","Fetita de Sousa","Radian","14061987","rfetita@everbe.com","32 rue Polia 33400 Marmande","radian");
		app.modifierEmploye(employe);
		/*if(app.ajouterEmploye(employe)){
			request.setAttribute("employe", "Nouvel employé ajouté à la base");
		}else{request.setAttribute("employe", "Employé déjà présent dans la base");
		}*/
		String responsereq = app.afficherTable();	
		System.out.println("gett");
		request.setAttribute( "test", responsereq );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		System.out.println(request.getParameterValues("formname"));
		if (request.getParameterValues("formname")[0].equals("modificate")){
			request.setAttribute("employees", app.getAllEmployes());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateEmployee.jsp" ).forward( request, response );
		}
		else if (request.getParameterValues("formname")[0].equals("selection")){
			String Name = request.getParameter("listemploye");
			//getemployebyName 
			request.setAttribute("employees", app.getAllEmployes());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateFormEmployee.jsp" ).forward( request, response );
		}else{
		request.setAttribute( "buttonName", "Youclicked" );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );}
	}

}