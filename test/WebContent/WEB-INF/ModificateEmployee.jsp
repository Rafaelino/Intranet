<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier employé</title>
</head>
<body>
<form method="post">
	<input type="hidden" name="formname" value="selection"/>
	  <select name="listemploye">
	    <option value="1"></option>
	    <%
		 List<String> employees = (List<String>) request.getAttribute("employees");
		 for (String employe : employees)
		 { 
		out.print("<option value='"+employe.split("@")[1]+"'>"+employe.split("@")[0]+"</option>");
		
		System.out.println("<option value='"+employe.split("@")[0]+"'>"+employe.split("@")[1]+"</option>");
		 }
		%>
	  </select>
  <input type="submit" value="Submit" >
</form>
</body>
</html>