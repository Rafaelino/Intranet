<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="assets/stylemain.css">
  <link rel="stylesheet" href="assets/pure.css">
 <script src="assets/tools.js" type="text/javascript" charset="iso-8859-1"></script>
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">
<h2>Modify employee information </h2>
<form method="post" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="modificateform"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Last name</label>
            <input id="name" name="nom" type="text" value="${employe.nom}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">First name</label>
            <input id="name" name="prenom" type="text" value="${employe.prenom}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Email</label>
            <input id="name" name="email" type="text" value="${employe.email}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Address</label>
            <input id="name" name="adresse" type="text" value="${employe.adresse}">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Birth date</label>
            <input id="name" name="datedenaissance" type="date" value="${employe.dateDeNaissance}">
            <span class="pure-form-message-inline"></span>
        </div>
           <div class="pure-control-group">
            <label for="name">Administrator</label>
            <input id ="admindata" type="hidden" name="admindata" value="${employe.admin}"/>          
            <input type="checkbox" name="admin"  id="admin" onchange="savechange(this)">
            <span class="pure-form-message-inline"></span>
        </div>
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
         <form name="suppression" method="post" onsubmit="return validateForm(event,suppression);" class="pure-form pure-form-aligned">
         <fieldset>
	        <input type="hidden" name="formname" value="suppression"/>
	        <input type="hidden" name="nom" value="${username}">
	       
	       <input class="btn" type="submit" value="Delete"/>
	      
        </fieldset>
        </form>
        </div>
        <script>
        	if (document.getElementById('admindata').value === "yes"){
        		document.getElementById('admin').checked = true
        	}
        	function savechange(checkboxElem) {
        		  if (checkboxElem.checked) {
        			
        			  document.getElementById('admindata').value = "yes";
        		  } else {
        			  document.getElementById('admindata').value = "null";

        		  }
        		}
        </script>
       
</body>
</html>