package com.utils;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tools {

	public Boolean isSessionOver(HttpSession session ){
		if (session.getAttribute("email") == null){
			return true;
	}else{
			return false;
			}
		}
	
	public Boolean isSessionOverRedirection(HttpSession session, ServletContext context, HttpServletRequest request, HttpServletResponse response ){
		System.out.println(session.getAttribute("email"));
		if (session.getAttribute("email") == null ){
			try {
				System.out.println("hello");
				context.getRequestDispatcher( "/WEB-INF/Reauthentification.jsp" ).forward(request, response );
				return true;
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return false;
			}
		return false;
		}
	
	public void logout(HttpSession session, ServletContext context, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (session.getAttribute("email") == null ){
			context.getRequestDispatcher( "/WEB-INF/Deconnexion.jsp" ).forward(request, response );
		}else{
			session.invalidate();
			context.getRequestDispatcher( "/WEB-INF/Deconnexion.jsp" ).forward(request, response );
		}
	}
	
}
