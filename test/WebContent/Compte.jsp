<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="inc/style.css">
<link rel="stylesheet" href="assets/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mon compte</title>
</head>
<body>
<div id="header"></div>
<div class="accompted">
<p>
Mon compte : <br>
<br>
Nom:  <br>
<br>
Email:	<br>
<br>	
Role:	<br>
<br>
Date de Naissance: <br>
</p>
</div>
<script>
$(function(){
	  $("#header").load("header-login-signup.html"); 
	  $("#footer").load("footer.html"); 
	});
</script>
</body>
</html>