<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier équipe de projet</title>
</head>
<body>
<form action ="testapp">
   			 <button>Acceuil</button>
</form>
<form method = "post">
 <input type="hidden" name="formname" value="deleteteammember"/>
<h1>Projet : ${projectname}</h1>
<input type="hidden" name="nom" value="${projectname}"/>
<p>Description projet : <input type="text" name="description" value="${description}" /></p>
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
   <input type="hidden" name="formname" value="addteammember"/>
     <input type="hidden" name="nom" value="${projectname}"/>
  <input type="submit" value ="Ajouter un membre au projet"/>
</form>
</body>
</html>