<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Répartition des projets par employé</title>
</head>
<body>
<ul>

	<c:forEach items="${employes}" var="element"> 

		
			 <li>
			  ${element.nom} ${element.prenom}:    <c:forEach items="${element.projects}" var="projects"> 
			  			
			  			<p>
			  			<c:set var="string" value="${fn:split(projects,';')}" />
			  			${string[0]} en tant que ${string[1]} du ${string[2]} au ${string[3]}
			  			</p>
			  </c:forEach>
			 </li>
	 
	</c:forEach>
</ul>
</body>
</html>