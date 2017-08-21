<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="assets/tools.js"></script>
<link rel="stylesheet" href="assets/pure.css">
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">
<h2>Add a new employee</h2>
<form name="formica" method="post" onsubmit="return validateForm(event);" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="saveemploye"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Last name</label>
            <input id="name" name="nom" type="text" placeholder="Last name">
            <span id ="nomspan" name="nomspan" class="pure-form-message-inline">Mandatory field</span>
        </div>
        <div class="pure-control-group">
            <label for="name">First name</label>
            <input id="name" name="prenom"type="text" placeholder="First name">
            <span id="prenomspan" class="pure-form-message-inline">Mandatory field</span>
        </div>
        <div class="pure-control-group">
            <label for="name">Email</label>
            <input id="name" name ="email" type="text" placeholder="example@everbe.com">
            <span id="emailspan" class="pure-form-message-inline">Mandatory field</span>
        </div>
         <div class="pure-control-group">
            <label for="name">Address</label>
            <input id="name" name ="adresse" type="text" placeholder="Street name PostalCode City">
            <span class="pure-form-message-inline"></span>
        </div>
        <div class="pure-control-group">
            <label for="name">Birth date</label>
            <input id="name" name ="datedenaissance" type="date" >
            <span class="pure-form-message-inline"></span>
        </div>
         ${creation} 
        <div class="pure-control-group">
            <input class ="btn" type="submit" value="Save" />
            <span class="pure-form-message-inline"></span>
        </div>
        <div class="pure-control-group">
            <button class ="btn" type="button" name="back" onclick="history.back()">Back</button>
            <span class="pure-form-message-inline"></span>
        </div>
        </fieldset>
  </form>
  </div>

        </body>
        <script type="text/javascript">

if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
	 document.getElementById("adminproject").innerHTML='Gérer les projets';
	}

	document.getElementById("welcome").innerHTML='Connecté en tant que ${name}';
	
	function validateForm(event) {
		event.preventDefault();
	    var x = document.forms["formica"]["nom"].value;
	    var y = document.forms["formica"]["prenom"].value;
	    var z = document.forms["formica"]["email"].value;
	    var err=0;
	    if (x == "") {
	    	 document.getElementById("nomspan").style.color='red';
	    	 document.getElementById("nomspan").innerHTML='This field is required';
	        err = 1;
	    }
	    if (y == "") {
	    	 document.getElementById("prenomspan").style.color='red';
	    	 document.getElementById("prenomspan").innerHTML='This field is required';
	    	 err = 1;
	    }
	    if (z == "" || z.split('@')[1] != 'everbe.com') {
	    	 document.getElementById("emailspan").style.color='red';
	    	 document.getElementById("emailspan").innerHTML='This field is required';
	    	 err = 1;
	    }
	    if (z !="" && z.split('@')[1] != 'everbe.com'){
	    	{
		    	 document.getElementById("emailspan").style.color='red';
		    	 document.getElementById("emailspan").innerHTML='Invalid address, everbe.com domain required';
		    	 err = 1;
		    }
	    }
	   if (err == 1){
		   return false;
	   }else{
		   document.formica.submit();
	   }
	}
</script>
</html>