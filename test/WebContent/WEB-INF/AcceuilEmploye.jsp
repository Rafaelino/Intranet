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
<jsp:include page="Header.jsp"/>

<div class="main">


<c:set var="admin" value="${admin}"/>
	<c:if test="${admin == 'yes'}">
 		<form name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button class="btn" name="create" value="upvote">Créer un employé</button>
		</form>
		<p></p>
</c:if>
		<form name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button class="btn" name="modificate" value="upvote">Modifier un employé</button>
		</form>
				<p></p>
		<form action="/test/disponibility">
   			 <button class="btn">Voir les employés disponibles</button>
		</form>
				<p></p>
		
<form action ="testapp">
   			 <button class="btn" >Retour acceuil</button>
   			
</form>
		<p></p>
<script type="text/javascript">

	

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
	 document.getElementById("adminproject").innerHTML='Gérer les projets';
	}

	document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
</script>
</body>
</html>