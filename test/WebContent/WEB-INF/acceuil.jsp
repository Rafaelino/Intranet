<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<script src="assets/autocompleter.js" type="text/javascript" charset="iso-8859-1"></script>
<header class="header-login-signup">

	<div class="header-limiter">

		<h1><img src="assets/everbelogo.jpg" width="110" height="70"  alt="arrow" ></h1>

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
			<li><input type="text" id="search" class="search" placeholder="ex: Nom Pr�nom" name="search"/></li>
			<span class="ico ico-mglass"></span>
			 <li id="welcome"></li>
			 <li><a href="/test/myaccompt">Mon compte</a></li>
			 <li> </li>
			<!--<li><a href="#">Sign up</a></li>-->
		</ul>

	</div>

</header>
<div class="main">
<h2>everBe</h2>
<p>Bievenue sur le site intranet d'everBe</p>
</div>
		<p>
            <% 
            String attribut = (String) request.getAttribute("test");
           // out.println(attribut);
            %>
           <%--  ${employe}
            ${test}
            <% 
            String attribu = (String) request.getAttribute("buttonName");
            //out.println(attribu);
           	 %>
            --%>
        </p>	
         <p>
             <% 
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
      		  %>
          
           
      	  <%
             }
      	  %>
      
		<%-- <input type="button" value="Cr�er un employ�" name="createemploye"
		onclick="openPage('createemploye')" />--%>
		
		<%--
		<form action ="projectapp">
   			 <button>G�rer les projets</button>
		</form>
		<form action ="drawapp">
   			 <button>Afficher r�partition projets</button>
		</form>--%>
       
        <script type="text/javascript">
		 function openPage(pageURL)
			 {
			 window.location.href = pageURL;
			 }
		
			 document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
			 //alert('${admin}');
			if( '${admin}' == "yes"){
				 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
				 document.getElementById("adminproject").innerHTML='G�rer les projets';
				}
			
			
		
			
			
			
			</script>
        
       
        
</body>
</html>