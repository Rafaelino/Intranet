<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="vis/dist/vis.js"></script>
  <link href="vis/dist/vis.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>R�partition des projets par employ�</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
	

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/header-login-signup.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
<header class="header-login-signup">

	<div class="header-limiter">

		<h1><img src="assets/everbe.png" width="110" height="70"  alt="arrow" ></h1>

		<nav>
			<a href="/test/testapp">Accueil</a>
			<a href="/test/drawapp">Afficher les projets</a>
			<a href="/test/employeapp" id="adminemploye"></a>
			<a href="/test/projectapp" id="adminproject"></a>
			<!-- <a href="#">Pricing</a> -->
		</nav>
		<ul>
			 <li id="welcome"></li>
			 <li><a href="/test/myaccompt">Mon compte</a></li>
			 <li> </li>
			<!--<li><a href="#">Sign up</a></li>-->
		</ul>

	</div>

</header>
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
<div id="visualization"></div>
</body>
<script>
if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
	 document.getElementById("adminproject").innerHTML='G�rer les projets';
	}

	document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
	
	var container = document.getElementById('visualization');

	  // Create a DataSet (allows two way data-binding)
	  var items = new vis.DataSet([
	    {id: 1, content: 'item 1', start: '2013-04-20'},
	    {id: 2, content: 'item 2', start: '2013-04-14'},
	    {id: 3, content: 'item 3', start: '2013-04-18'},
	    {id: 4, content: 'item 4', start: '2013-04-16', end: '2013-04-19'},
	    {id: 5, content: 'item 5', start: '2013-04-25'},
	    {id: 6, content: 'item 6', start: '2013-04-27'}
	  ]);

	  // Configuration for the Timeline
	  var options = {};

	  // Create a Timeline
</script>
</html>