package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		doGet(request, response);
	}

}
