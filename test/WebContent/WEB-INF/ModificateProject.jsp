<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<input type="hidden" name="formname" value="selection"/>
	  <select name="listemploye">
	    <option value="1"></option>
	    <%
	    List<String> projets = (List<String>) request.getAttribute("projects");
		 for (String projet : projets)
		 { 
		out.print("<option value='"+projet+"'>"+projet+"</option>");
		 }
		%>
	  </select>
  <input type="submit" value="Submit" >
</form>
</body>
</html>