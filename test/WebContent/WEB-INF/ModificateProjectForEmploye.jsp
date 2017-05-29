<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<li><input type="text" id="employe" placeholder="ex: Nom Pr�nom"name="employe"/></li>
			<span class="ico ico-mglass"></span>
			 <li id="welcome"></li>
			 <li><a href="/test/myaccompt">Mon compte</a></li>
			 <li> </li>
			<!--<li><a href="#">Sign up</a></li>-->
		</ul>

	</div>

</header>
<div class="main">

<h1>Projet : ${project.nom}</h1>


<form method="post" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="saveprojectforemploye"/>
	<input type="hidden" name="nom" value="${project.nom}"/>
	<input type="hidden" name="employename" value="${employename}"/>
	<input type="hidden" name="projectforemploye" value="${projectforemploye}"/>
	<input type="hidden" name="olddatedebut" value="${projectforemploye.dateDebut}"/>
	<input type="hidden" name="olddatefin" value="${projectforemploye.dateFin}"/>
	<input type="hidden" name="oldrole" value="${projectforemploye.role}"/>
	<input type="hidden" name="oldimplication" value="${projectforemploye.implication}"/>
	
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Date de d�but</label>
            <input type="date" name="datedebut" value="${projectforemploye.dateDebut}" />
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Date de fin</label>
            <input type="date" name="datefin" value="${projectforemploye.dateFin}" />
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Role</label>
            <select name="role">
	    <option value="Chef de projet">Chef de projet</option>
	    <option value="Collaborateur">Collaborateur</option>
	  </select>
             <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Implication</label>
            <input type="text" name="implication" value="${projectforemploye.implication}" />
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
</div>
</body>
</html>