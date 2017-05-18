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
<h2>Ajouter un nouveau projet</h2>
<form method="post" class="pure-form pure-form-aligned">
<input type="hidden" name="formname" value="saveproject"/>
	<input type="hidden" name="formname" value="saveemploye"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Nom</label>
            <input id="name" name="nom" type="text" placeholder="Nom">
            <span class="pure-form-message-inline">Champ Obligatoire</span>
        </div>
         <div class="pure-control-group">
            <label for="name">Description</label>
            <input id="name" name="description" type="text" placeholder="Description">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
           
            <input class="btn" type="submit" value="Enregistrer" />
            <span class="pure-form-message-inline"></span>
        </div>
        </fieldset>
        </form>
        </div>

</body>
</html>