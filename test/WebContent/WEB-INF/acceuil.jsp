<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<head>
<link rel="stylesheet" type="text/css" href="assets/jquery.autocomplete.css" />
<script src="http://www.google.com/jsapi"></script>
<script>
	google.load("jquery", "1");
</script>
<script src="js/jquery.autocomplete.js"></script>
<style>
	input {
	font-size: 80%;
	}
</style>
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
      
		<%-- <input type="button" value="Créer un employé" name="createemploye"
		onclick="openPage('createemploye')" />--%>
		
		<%--
		<form action ="projectapp">
   			 <button>Gérer les projets</button>
		</form>
		<form action ="drawapp">
   			 <button>Afficher répartition projets</button>
		</form>--%>
        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function button1()
            {
                document.form1.buttonName.value = "button 1"
                form1.submit()
            }    
            function button2()
            {
                document.form1.buttonName.value = "button 2"
                form1.submit()
            }    
            // --> 
        </SCRIPT>
        <script type="text/javascript">
		 function openPage(pageURL)
			 {
			 window.location.href = pageURL;
			 }
		
			 document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
			if( '${admin}' == "yes"){
				 document.getElementById("adminemploye").innerHTML='Gérer les employés';
				 document.getElementById("adminproject").innerHTML='Gérer les projets';
				}
			
			$("#employe").autocomplete("getData.jsp");
		
			
			
			
			</script>
        
       
        
</body>
</html>