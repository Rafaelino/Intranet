<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button name="create" value="upvote">Créer un projet</button>
		</form>
		<form name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button name="modificate" value="upvote">Modifier un projet</button>
		</form>
		<form name="delete"  method="post">
			 <input type="hidden" name="formname" value="delete"/>
   			 <button name="delete" value="upvote">Supprimer un projet</button>
		</form>
<form action ="testapp">
   			 <button>Retour acceuil</button>
</form>
</body>
</html>