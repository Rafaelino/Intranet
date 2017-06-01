<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="assets/stylemain.css">
  <link rel="stylesheet" href="assets/pure.css">
 <script src="assets/tools.js" type="text/javascript" charset="iso-8859-1"></script>
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<div class="main">
<h2>Modifier l'employé</h2>
<form method="post" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="modificateform"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Nom</label>
            <input id="name" name="nom" type="text" value="${employe.nom}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Prénom</label>
            <input id="name" name="prenom" type="text" value="${employe.prenom}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Email</label>
            <input id="name" name="email" type="text" value="${employe.email}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Adresse</label>
            <input id="name" name="adresse" type="text" value="${employe.adresse}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Date de Naissance</label>
            <input id="name" name="datedenaissance" type="text" value="${employe.dateDeNaissance}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <input class ="btn" type="submit" value="Enregistrer" />
            <span class="pure-form-message-inline"></span>
        </div>
        <div class="pure-control-group">
            <button class ="btn" type="button" name="back" onclick="history.back()">Retour</button>
            <span class="pure-form-message-inline"></span>
        </div>
        </fieldset>
        </form>
         <form name="suppression" method="post" onsubmit="return validateForm(event,suppression);" class="pure-form pure-form-aligned">
         <fieldset>
	        <input type="hidden" name="formname" value="suppression"/>
	        <input type="hidden" name="nom" value="${username}">
	       
	       <input class="btn" type="submit" value="Supprimer l'employé"/>
	      
        </fieldset>
        </form>
        </div>
        
       
</body>
</html>