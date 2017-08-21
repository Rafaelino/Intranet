<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.List" %>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/stylemain.css">
  <link rel="stylesheet" href="assets/pure.css">
 
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">
<form method="post" class="pure-form pure-form-stacked">
    <fieldset>
        <legend>Select a project</legend>

        <div class="pure-g">

	<input type="hidden" name="formname" value="selection"/>
	  <div class="pure-u-1 pure-u-md-1-3">
	   <label for="state"></label>

	  <select name="listemploye">
	    <option value="1"></option>
	    <c:forEach items="${projects}" var="element"> 
	    <option value='${element.name}'>${element.name}</option>
	    </c:forEach>
	  </select>
	  <p></p>
  <input class="btn" type="submit" value="Validate" >
  </div>
  </div>
  </fieldset>
</form>
</div>
</body>
</html>