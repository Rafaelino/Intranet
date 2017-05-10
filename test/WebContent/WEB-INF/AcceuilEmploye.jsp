<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="header"></div>
<c:set var="admin" value="${admin}"/>
	<c:if test="${admin == 'yes'}">
 		<form name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button name="create" value="upvote">Créer un employé</button>
		</form>
</c:if>
		<form name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button name="modificate" value="upvote">Modifier un employé</button>
		</form>
<form action ="testapp">
   			 <button>Retour acceuil</button>
</form>
<script type="text/javascript">
$(function(){
	  $("#header").load("header-login-signup.html"); 
	  $("#footer").load("footer.html"); 
	});
	</script>
</body>
</html>