<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/stylemain.css">
<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"/>


<div class="main">
	<form name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button class ="btn" name="create" value="upvote">Cr�er un projet</button>
		</form>
		<p></p>
		<form name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button class ="btn"  name="modificate" value="upvote">Modifier un projet</button>
		</form>
		<p></p>
<form action ="testapp">
   			 <button class ="btn" >Retour acceuil</button>
</form>
</div>
<script type="text/javascript">

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='G�rer les employ�s';
	 document.getElementById("adminproject").innerHTML='G�rer les projets';
	}

	document.getElementById("welcome").innerHTML='Connect� en tant que ${name}';
</script>
</body>
</html>