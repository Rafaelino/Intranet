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
<title>Répartition des projets par employé</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
	

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/header-login-signup.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
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
	
<form name="create" method="post">
			 <input type="hidden" name="formname" value="employes"/>
   			 <button class ="btn_small" name="create" value="upvote">Vue par employés</button>
		</form>
		
	<ul>

	<c:forEach items="${projects}" var="element" varStatus="employeloop"> 
		
				<script>
			  			var projectlisting = "[";
			  	</script>
			  
			 <li>
			 <h2> ${element}     </h2>
			  
			  			
			  			<input type="hidden" id="listing" name="x" value="${employedrawlisting}">
			
			  
			 </li>

		

			 <div class="drawing" id="${element}" value="${element}"></div>
			
	 
	</c:forEach>
	
</ul>

			
<div id="visualization"></div>
</body>
<script>
//if( '${admin}' == "yes"){
//	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
//	 document.getElementById("adminproject").innerHTML='Gérer les projets';
//	}

	document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
	
	 var input = document.getElementById("listing").value;
		
	 var fields = input.split('@');
	for (var i = 0; i < fields.length; i++) {
		var employees = fields[i].split(';');	
		if(employees[1].length > 0){
		ploting(document.getElementById(employees[0]),employees[1]);}
	}
</script>
</html>