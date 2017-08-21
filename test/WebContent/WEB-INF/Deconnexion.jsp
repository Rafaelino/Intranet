<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/header-login-signup.css">
<link rel="stylesheet" href="assets/stylemain.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"/>

<div class="main">
<h3>
You are now disconnected
 Follow this link for identification </h3>
<form action ="Authentification">
   			 <button class ="btn">Connect</button>
   			 </form>
   			 </div>
   			 <script>
   			    window.onload = function() {
   			 document.getElementById("welcome").innerHTML= '';
   			document.getElementById('search').style.display = 'none';
   			document.getElementById('demo').style.display = 'none';
   			document.getElementById('myaccompt').style.display = 'none';
   			};</script>
</body>
</html>