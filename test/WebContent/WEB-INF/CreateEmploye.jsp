<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="assets/tools.js"></script>
<link rel="stylesheet" href="assets/pure.css">
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Créer Employé</title>
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
		

			<script>
			
			</script>
			<!-- <a href="#">Pricing</a> -->
		</nav>
		<ul>
			<li><input type="text" id="employe" placeholder="ex: Nom Prénom"name="employe"/></li>
			<span class="ico ico-mglass"></span>
			 <li id="welcome"></li>
			 <li><a href="/test/myaccompt">Mon compte</a></li>
			 <li> </li>
			<!--<li><a href="#">Sign up</a></li>-->
		</ul>

	</div>

</header>
<div class="main">
<h2>Ajouter un nouvel employé</h2>
<form name="formica" method="post" onsubmit="return validateForm(event);" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="saveemploye"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Nom</label>
            <input id="name" name="nom" type="text" placeholder="Nom">
            <span id ="nomspan" name="nomspan" class="pure-form-message-inline">Champ Obligatoire</span>
        </div>
        <div class="pure-control-group">
            <label for="name">Prénom</label>
            <input id="name" name="prenom"type="text" placeholder="Prénom">
            <span id="prenomspan" class="pure-form-message-inline">Champ Obligatoire</span>
        </div>
        <div class="pure-control-group">
            <label for="name">Email</label>
            <input id="name" name ="email" type="text" placeholder="exemple@everbe.com">
            <span id="emailspan" class="pure-form-message-inline">Champ Obligatoire</span>
        </div>
         <div class="pure-control-group">
            <label for="name">Adresse</label>
            <input id="name" name ="adresse" type="text" placeholder="Rue exemple 33000 Bordeaux">
            <span class="pure-form-message-inline"></span>
        </div>
        <div class="pure-control-group">
            <label for="name">Date de naissance</label>
            <input id="name" name ="datedenaissance" type="text" placeholder="JJMMAAAA">
            <span class="pure-form-message-inline"></span>
        </div>
         ${creation} 
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
  </div>

        </body>
        <script type="text/javascript">

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
	 document.getElementById("adminproject").innerHTML='Gérer les projets';
	}

	document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
	
	function validateForm(event) {
		event.preventDefault();
	    var x = document.forms["formica"]["nom"].value;
	    var y = document.forms["formica"]["prenom"].value;
	    var z = document.forms["formica"]["email"].value;
	    var err=0;
	    if (x == "") {
	    	 document.getElementById("nomspan").style.color='red';
	    	 document.getElementById("nomspan").innerHTML='Ce champ doît être renseigné';
	        err = 1;
	    }
	    if (y == "") {
	    	 document.getElementById("prenomspan").style.color='red';
	    	 document.getElementById("prenomspan").innerHTML='Ce champ doît être renseigné';
	    	 err = 1;
	    }
	    if (z == "" || z.split('@')[1] != 'everbe.com') {
	    	 document.getElementById("emailspan").style.color='red';
	    	 document.getElementById("emailspan").innerHTML='Ce champ doît être renseigné';
	    	 err = 1;
	    }
	    if (z !="" && z.split('@')[1] != 'everbe.com'){
	    	{
		    	 document.getElementById("emailspan").style.color='red';
		    	 document.getElementById("emailspan").innerHTML='Adresse invalide, domaine everbe.com requis';
		    	 err = 1;
		    }
	    }
	   if (err == 1){
		   return false;
	   }else{
		   document.formica.submit();
	   }
	}
</script>
</html>