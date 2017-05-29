<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/pure.css">
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier équipe de projet</title>
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


<h1>Information pour le projet : ${projectname}</h1>
<input type="hidden" name="nom" value="${projectname}"/>
<p>Description du projet : <input type="text" name="description" value="${description}" /></p>
<p><h3>Chef de Projet :</h3> <ul><c:forEach items="${managers}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <li> ${element}  <form method = "post">
 <input type="hidden" name="formname" value="deleteteammember"/> <input type="hidden" name="employename" value="${element}"/>  
   <input type="hidden" name="nom" value="${projectname}"/>
 
  <input class="btn_small" type="submit" value="supprimer" /> </form>
 <form method = "post">
 <input type="hidden" name="employename" value="${element}"/>  
   <input type="hidden" name="nom" value="${projectname}"/>
  <input type="hidden" name="role" value="Chef de projet"/>  
 <input type="hidden" name="formname" value="modifyprojectforemploye"/>
 <input class="btn_small"type="submit" value="modifier" />

</form>
 </li>
 
</c:forEach></p>
</ul>
</form>


 <input type="hidden" name="nom" value="${projectname}"/>
<p><h3>Collaborateurs :</h3><ul><c:forEach items="${collaborateurs}" var="element"> 
 <input type="hidden" name="employename" value="${element}"/>  
 <li> ${element} <form method = "post">
 <input type="hidden" name="formname" value="deleteteammemberchef"/>
  <input type="hidden" name="employename" value="${element}"/>  
   <input type="hidden" name="nom" value="${projectname}"/>
 
 <input class="btn_small"type="submit" value="supprimer" /> </form>
 <form method = "post">
  <input type="hidden" name="nom" value="${projectname}"/>
  <input type="hidden" name="employename" value="${element}"/>  
  <input type="hidden" name="role" value="Collaborateur"/> 
 <input type="hidden" name="formname" value="modifyprojectforemploye"/>
 <input class="btn_small"type="submit" value="modifier" />

</form>
 </li>
 
</c:forEach>
</ul>

<form method = "post">
   <input type="hidden" name="formname" value="addteammember"/>
     <input type="hidden" name="nom" value="${projectname}"/>
  <input type="submit" class="btn" value ="Ajouter un membre au projet"/>
</form>
<form method = "post">
   <input type="hidden" name="formname" value="deleteproject"/>
     <input type="hidden" name="nom" value="${projectname}"/>
  <input type="submit" class="btn" value ="Supprimer le projet"/>
</form>
<p></p>
<form  action ="testapp">
   			 <button class="btn">Accueil</button>
</form>
</div>
</body>
</html>