<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<style>
	input {
	font-size: 80%;
	}
</style>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
 <link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
	<title>Login, Sign up Header</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
<header class="header-login-signup">

	<div class="header-limiter">
	
	<div class="leftlist">
	<ul class="boxinleftlist">
	<li class="accueil"><a href="/test/testapp">Accueil</a></li>
<li class="moncompte"><a href="/test/myaccompt">Mon compte</a></li>
</ul>
</div>

		<img src="assets/everbelogo.jpg" class="logo"/>
			
			<!-- <a href="#">Pricing</a> -->

		
    <div class="rightlist">
    <ul class="boxinrightlist">
			<li class="recherche"><input type="text" id="employe" placeholder="ex: Nom Prénom"name="employe"/></li>
			 <li id="welcome" class="bonjour"></li>
			 <li></li>

			<!--<li><a href="#">Sign up</a></li>-->
			</ul>
		</div>

	</div>

</header>
</body>
    <script type="text/javascript">
		 function openPage(pageURL)
			 {
			 window.location.href = pageURL;
			 }
		
			 document.getElementById("welcome").innerHTML='Bonjour ${name}';
			 //alert('${admin}');
			if( '${admin}' == "yes"){
				 document.getElementById("adminemploye").innerHTML='Gérer les employés';
				 document.getElementById("adminproject").innerHTML='Gérer les projets';
				}
			
			
		
			
			
			
			</script>
        
</html>