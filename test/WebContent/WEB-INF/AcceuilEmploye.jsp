<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="assets/header-login-signup.css">
<link rel="stylesheet" href="assets/stylemain.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
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
<div class="main">


<c:set var="admin" value="${admin}"/>
	<c:if test="${admin == 'yes'}">
 		<form name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button class="btn" name="create" value="upvote">Cr�er un employ�</button>
		</form>
		<p></p>
</c:if>
		<form name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button class="btn" name="modificate" value="upvote">Modifier un employ�</button>
		</form>
				<p></p>
		
<form action ="testapp">
   			 <button class="btn" >Retour acceuil</button>
   			
</form>
		<p></p>
<script type="text/javascript">

	

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
	 document.getElementById("adminproject").innerHTML='G�rer les projets';
	}

	document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
</script>
</body>
</html>