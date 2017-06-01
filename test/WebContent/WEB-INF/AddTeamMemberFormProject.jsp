<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/pure.css">
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">


<form method = "post">
 <input type="hidden" name="formname" value="deleteteammember"/>
<h1>Information pour le projet : ${projectname}</h1>
<input type="hidden" name="nom" value="${projectname}"/>

<p><h3>Chef de Projet : </h3><ul><c:forEach items="${managers}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <li> ${element}   <input class="btn_small" type="submit" value="X" /> 
 </li>
 
</c:forEach></p>
</ul>
</form>

<form method = "post">
 <input type="hidden" name="formname" value="deleteteammemberchef"/>
 <input type="hidden" name="nom" value="${projectname}"/>
<p><h3>Collaborateurs :</h3><ul><c:forEach items="${collaborateurs}" var="element"> 
 <input type="hidden" name="employename" value="${element}"/>  
 <li> ${element} <input class="btn_small" type="submit" value="X" />
 </li>
 
</c:forEach></p>
</ul>
</form>

        <div class="pure-g">
        	  <div class="pure-u-1 pure-u-md-1-3">
<form method = "post" class="pure-form pure-form-aligned">
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
du    <input type="date" name="datedebut" />
au    <input type="date" name="datefin" /> 
implication <select id="select" name="implication")>
  <option value="10%">10%</option> 
  <option value="20%">20%</option>
   <option value="30%">30%</option>
    <option value="40%">40%</option>
     <option value="50%">50%</option>
      <option value="60%">60%</option>
       <option value="70%">70%</option>
        <option value="80%">80%</option>
         <option value="90%">90%</option>
  <option value="100%" selected>100%</option>
</select>
 <input type="submit" class="btn" value="Enregistrer" > 
 <p></p>
</form>
</div>
</div>
 <form action ="testapp">
   			 <button class="btn">Accueil</button>
</form>
</div>
</body>
</html>