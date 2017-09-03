<%@page import="com.beans.Employe"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="assets/stylemain.css">
 <script src="vis/dist/vis.js"></script>
  <link href="vis/dist/vis.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
	

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/header-login-signup.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
<script src="assets/autocompleter.js" type="text/javascript" charset="iso-8859-1"></script>

<jsp:include page="Header.jsp"/>
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
	<div class="draw">
<form name="create" method="post">
			 <input type="hidden" name="formname" value="projects"/>
   			 <button class ="btn_small" name="create" value="upvote">Project view</button>
		</form>
		<form name="create" class="draw" method="post">
			 <input type="hidden" name="formname" value="downloadcsv"/>
			 <input type="hidden" name="origin" value="employe"/>
   			 <button class ="btn_small" name="create" value="upvote">Export CSV</button>
		</form>
	<ul>

	<c:forEach items="${employes}" var="element" varStatus="employeloop"> 
		
				<script>
			  			var projectlisting = "[";
			  	</script>
			 <li>
			  <h2> ${element.nom} ${element.prenom}:  </h2> 
			  
			  <c:forEach items="${element.projects}" var="projects" varStatus="loop"> 
			  			
			  			<p>
			  		
			  			<c:set var="uname" value="${element.username}" />
			  			<c:set var="projectlistout" value="{id: ${loop.index}, content: '${string[0]}', start: '${string[2]}', end: '${string[3]}'}," />
			  			
			  			<input type="hidden" id="listing" name="x" value="${projectdrawlisting}">
			 
			  			
			  </c:forEach>
			  
			 </li>

			 
		
			 <div class="drawing" id="${element.username}" value="${element.username}"></div>
			
	 
	</c:forEach>
	
</ul>

<div class="drawing" id="visualization"></div>
</div>
</body>
<script>
//if( '${admin}' == "yes"){
//	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
//	 document.getElementById("adminproject").innerHTML='Gérer les projets';
//	}

	
	 var input = document.getElementById("listing").value;
		
			 var fields = input.split('@');
			for (var i = 0; i < fields.length; i++) {
				var projects = fields[i].split(';');	
				if(projects[1].length > 0){
				ploting(document.getElementById(projects[0]),projects[1]);}
			}
</script>
</html>