<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/stylemain.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">


	<link rel="stylesheet" href="assets/header-login-signup.css">
	</head>
<body>

<div class="buttonsmenu">
<div class="pad"></div>
<div class="pad"></div>


<form action="drawapp" name="create" method="post">
			 <input type="hidden" name="formname" value="projects"/>
   			 <button class ="btn" name="create" value="upvote">Project view</button>
		</form>
		<div class="pad"></div>
	
		<form action="drawapp" name="modificate"  method="post">
			 <input type="hidden" name="formname" value="employes"/>
   			 <button class ="btn"  name="modificate" value="upvote">Employee view</button>
		</form>
		<div class="pad"></div>
</div>

</body>
<script>
</script>
</html>