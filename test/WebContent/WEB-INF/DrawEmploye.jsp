<%@page import="com.test.beans.Employe"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/stylemain.css">
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
<script>
function ploting(divname,projects){
	//alert(projects.toString())
	var container = divname;
	var myobj = JSON.parse(JSON.stringify(projects));
	var data = eval('(' +projects+ ')');
	  var items = new vis.DataSet(data);
	 
	  // Configuration for the Timeline
	  var options = {};
	   // Create a Timeline
  var timeline = new vis.Timeline(container, items, options);
	}
	
	</script>
<form name="create" method="post">
			 <input type="hidden" name="formname" value="projects"/>
   			 <button class ="btn_small" name="create" value="upvote">Vue par projets</button>
		</form>
		
	<ul>

	<c:forEach items="${employes}" var="element" varStatus="employeloop"> 
		
				<script>
			  			var projectlisting = "[";
			  	</script>
			 <li>
			  ${element.nom} ${element.prenom}:    
			  
			  <c:forEach items="${element.projects}" var="projects" varStatus="loop"> 
			  			
			  			<p>
			  			<c:set var="string" value="${fn:split(projects,';')}" />
			  			<c:set var="uname" value="${element.username}" />
			  			<c:set var="projectlistout" value="{id: ${loop.index}, content: '${string[0]}', start: '${string[2]}', end: '${string[3]}'}," />
			  			${string[0]} en tant que ${string[1]} du ${string[2]} au ${string[3]}	
	  			
			  			<input type="hidden" id="listing" name="x" value="${projectdrawlisting}">
			 
			  			
			  </c:forEach>
			  
			 </li>

			 
		
			 <div class="drawing" id="${element.username}" value="${element.username}"></div>
			
	 
	</c:forEach>
	
</ul>

<div class="drawing" id="visualization"></div>
</body>
<script>
if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
	 document.getElementById("adminproject").innerHTML='G�rer les projets';
	}

	document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
	

	
	 var input = document.getElementById("listing").value;
		
			 var fields = input.split('@');
			for (var i = 0; i < fields.length; i++) {
				var projects = fields[i].split(';');	
				if(projects[1].length > 0){
				ploting(document.getElementById(projects[0]),projects[1]);}
			}
</script>
</html>