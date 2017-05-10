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
<title>Mon Compte</title>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">

	<link rel="stylesheet" href="assets/demo.css">
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


<div class = "main">
<h1>Bienvenue dans votre espace </h1>
<h2>Mes Infos 	<form method="post" >
				 <input type="hidden" name="formname" value="modificate"/>
   				 <button class="btn_small">Modifier</button>
		</form> </h2>
<p> Mon nom :  ${employe.prenom} ${employe.nom}</p>
<p> Mon email :  ${employe.email} </p>
<p> Mon adresse :  ${employe.adresse} </p>
<p> Ma date de naissance :  ${employe.dateDeNaissance} </p>
<h2>Mes projets</h2>
 <input type="hidden" id="usernameid" value="Connect�e en tant que ${name}"/>
 	<form method="post" >
				 <input type="hidden" name="formname" value="logout"/>
   				 <button class="btn">D�connexion</button>
		</form> 
</div>
<script type="text/javascript">

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
	 document.getElementById("adminproject").innerHTML='G�rer les projets';
	}

	document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
</script>
</body>
</html>