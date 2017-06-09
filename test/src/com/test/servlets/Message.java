package com.test.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.test.beans.Employe;
import com.test.utils.CassandraEmployeUtils;
import com.test.utils.CassandraMessageUtils;
import com.test.utils.DummyDB;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CassandraEmployeUtils app = new CassandraEmployeUtils();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		request.setAttribute("employees", app.getAllEmployes());
		if (!(session.getAttribute("email") == null)){
			String userEmail = (String) session.getAttribute("email");
			String name = userEmail.split("@")[0];
			String host = userEmail.split("@")[0];
			CassandraEmployeUtils app = new CassandraEmployeUtils();
		CassandraMessageUtils mesapp = new CassandraMessageUtils();
		System.out.println(session.getAttribute("receiver"));
		if (!(session.getAttribute("receiver") == null)){
		request.setAttribute("receiver",app.getEmployeByUsername(request.getParameter("receiver")));
		request.setAttribute("receiverusername",request.getParameter("receiver"));
		mesapp.sendMessage(app.getEmployeByUsername(name), app.getEmployeByUsername(request.getParameter("receiver")), request.getParameter("message"));
		request.setAttribute("messagelist", mesapp.getMessageList(app.getEmployeByUsername(host),app.getEmployeByUsername(name)));
		}}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Message.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameterValues("formname")!=null){
			if(request.getParameterValues("formname")[0].equals("send")){
				if (!(session.getAttribute("email") == null)){
					String userEmail = (String) session.getAttribute("email");
					String name = userEmail.split("@")[0];
					String host = userEmail.split("@")[0];
					CassandraEmployeUtils app = new CassandraEmployeUtils();
				CassandraMessageUtils mesapp = new CassandraMessageUtils();
				request.setAttribute("employees", app.getAllEmployes());
				Employe employe = app.getEmployeByUsername(name);
				request.setAttribute("receiver",app.getEmployeByUsername(request.getParameter("receiver")));
				request.setAttribute("receiverusername",request.getParameter("receiver"));
				mesapp.sendMessage(app.getEmployeByUsername(name), app.getEmployeByUsername(request.getParameter("receiver")), request.getParameter("message"));
				request.setAttribute("messagelist", mesapp.getMessageList(app.getEmployeByUsername(host),app.getEmployeByUsername(name)));
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Message.jsp" ).forward( request, response );
				}
			}else if(request.getParameterValues("formname")[0].equals("selection")){
				CassandraMessageUtils mesapp = new CassandraMessageUtils();
			
				if (!(session.getAttribute("email") == null)){
				String name = request.getParameter("listemploye");
				String userEmail = (String) session.getAttribute("email");
				String host = userEmail.split("@")[0];
				Employe employe = app.getEmployeByUsername(name);
				request.setAttribute("receiver",employe);
				request.setAttribute("receiverusername",employe.getUsername());
				request.setAttribute("messagelist", mesapp.getMessageList(app.getEmployeByUsername(host),app.getEmployeByUsername(name)));
				request.setAttribute("employees", app.getAllEmployes());
				this.getServletContext().getRequestDispatcher( "/WEB-INF/Message.jsp" ).forward( request, response );
				}
				}
			}else if(!(request.getHeader("code") == null)){
				CassandraMessageUtils mesapp = new CassandraMessageUtils();
				if (!(session.getAttribute("email") == null)){
					String userEmail = (String) session.getAttribute("email");
					//response.setContentType("application/json");
					String name = request.getHeader("employename");
					String host = userEmail.split("@")[0];
					//request.setAttribute("employees", app.getAllEmployes());
					List<String> messagelist = mesapp.getMessageList(app.getEmployeByUsername(host),app.getEmployeByUsername(name));
					 String messageList = new Gson().toJson(messagelist);
		             response.getWriter().write(messageList);
					//request.setAttribute("messagelist", mesapp.getMessageList(app.getEmployeByUsername(host),app.getEmployeByUsername(name)));
					
				

				
				}
			}
		
		
	}

}
