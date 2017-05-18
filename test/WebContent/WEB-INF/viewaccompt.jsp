<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <link rel="stylesheet" href="assets/stylemain.css">
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Compte</title>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">

	<link rel="stylesheet" href="assets/demo.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compte</title>
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
<div class = "main">
<h1>Bienvenue dans l'espace de ${employe.prenom} ${employe.nom} </h1>
<h2>Ses Informations 	</h2>
<p> Nom :  ${employe.prenom} ${employe.nom}</p>
<p> Email :  ${employe.email} </p>
<p> Adresse :  ${employe.adresse} </p>
<p> Date de naissance :  ${employe.dateDeNaissance} </p>
<h2>Projets</h2>
<ul><c:forEach items="${projects}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <li> <h3>${element.name}</h3>  <c:set var = "salary" scope = "session" value = "${element.role}"/>
    
 <p> Début :  ${element.dateDebut}   Fin : ${element.dateFin} </p>
 <p> Role : ${element.role}</p>
 <c:set var = "employelistname" value = "employelist${element.name}"/>
 <p>Autre membre de l'équipe : </p>
 	<ul><c:forEach items="${requestScope[employelistname]}" var="employelisting"> 
 	<c:set var = "username" scope = "session" value = "${employelisting.username}"/>
 	 	<c:set var = "username2" scope = "session" value = "${employe.username}"/>
 	
 	<c:if test = "${username != username2}">
 	<li><p><form method="post">
   <a href="javascript:;" onclick="parentNode.submit();">${employelisting.nom} ${employelisting.prenom}  </a>
    <input type="hidden" name="formname" value="!${employelisting.username}"/>
</form>

 		</li>
 		</c:if>
 	</c:forEach></ul>
 	
 	
 <c:if test = "${salary == 'Chef de projet'}">
      
      </c:if> 
 </li>
</c:forEach>
</ul>
 <input type="hidden" id="usernameid" value="Connectée en tant que ${name}"/>
 	
</div>
<script type="text/javascript">

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
	 document.getElementById("adminproject").innerHTML='Gérer les projets';
	}

	document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
</script>
</body>
</html>