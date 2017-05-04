<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="testapp">
   			 <button>Acceuil</button>
</form>
<form method = "post">
 <input type="hidden" name="formname" value="deleteteammember"/>
<h1>Projet : ${projectname}</h1>
<input type="hidden" name="nom" value="${projectname}"/>

<p>Chef de Projet : <ul><c:forEach items="${managers}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <li> ${element}   <input type="submit" value="X" /> 
 </li>
 
</c:forEach></p>
</ul>
</form>

<form method = "post">
 <input type="hidden" name="formname" value="deleteteammemberchef"/>
 <input type="hidden" name="nom" value="${projectname}"/>
<p>Collaborateurs :<ul><c:forEach items="${collaborateurs}" var="element"> 
 <input type="hidden" name="employename" value="${element}"/>  
 <li> ${element} <input type="submit" value="X" />
 </li>
 
</c:forEach></p>
</ul>
</form>
<form method = "post">
 <input type="hidden" name="formname" value="saveteammember"/>
 <input type="hidden" name="nom" value="${projectname}"/>
Employé :  <select name="listemploye">
	    <option value="1"></option>
	    <%
		 List<String> employees = (List<String>) request.getAttribute("employees");
		 for (String employe : employees)
		 { 
		out.print("<option value='"+employe.split("@")[1]+"'>"+employe.split("@")[0]+"</option>");
		 }
		%>
	  </select>		  
en tant que : 
<select name="role">
	    <option value="1"></option>
	    <option value="Chef de projet">Chef de projet</option>
	    <option value="Collaborateur">Collaborateur</option>
	  </select>
du    <input type="text" name="datedebut" />
au    <input type="text" name="datefin" /> 
 <input type="submit" value="Enregistrer" > 
</form>
</body>
</html>