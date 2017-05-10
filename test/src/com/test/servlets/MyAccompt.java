package com.test.servlets;

import java.io.IOException;
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
		CassandraEmployeUtils app = new CassandraEmployeUtils();
		HttpSession session = request.getSession();
		if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
		Employe me = app.getEmployeByUsername(session.getAttribute("email").toString().split("@")[0]);
		request.setAttribute("admin", me.getAdmin());
		request.setAttribute("employe", me);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/MyAccompt.jsp" ).forward( request, response );}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameterValues("formname")[0].equals("logout")){
			HttpSession session = request.getSession();
			tools.logout(session, this.getServletContext(), request, response);
		}else if(request.getParameterValues("formname")[0].equals("modificate")){
			HttpSession session = request.getSession();
			if(tools.isSessionOverRedirection(session, this.getServletContext(), request, response)){}else{
				CassandraEmployeUtils app = new CassandraEmployeUtils();
				Employe me = app.getEmployeByUsername(session.getAttribute("email").toString().split("@")[0]);
				request.setAttribute("admin", me.getAdmin());
				request.setAttribute("employe", me);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/ModificateFormEmployee.jsp" ).forward( request, response );}
		}
		
		doGet(request, response);
	}

}
