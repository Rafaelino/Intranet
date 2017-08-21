<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/pure.css">
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="main">

<h1>Projet : ${project.nom}</h1>

<h3>Autre période : Du ${projectforemploye.dateDebut} au ${projectforemploye.dateFin} en tant que ${projectforemploye.role}</h3>

<form method="post" class="pure-form pure-form-aligned">
	<input type="hidden" name="formname" value="saveperiod"/>
	<input type="hidden" name="nom" value="${project.nom}"/>
	<input type="hidden" name="employename" value="${employename}"/>
	<input type="hidden" name="projectforemploye" value="${projectforemploye}"/>
	<input type="hidden" name="olddatedebut" value="${projectforemploye.dateDebut}"/>
	<input type="hidden" name="olddatefin" value="${projectforemploye.dateFin}"/>
	<input type="hidden" name="oldrole" value="${projectforemploye.role}"/>
	<input type="hidden" name="oldimplication" value="${projectforemploye.implication}"/>
	
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Start date</label>
            <input type="date" name="datedebut" value="" />
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">End date</label>
            <input type="date" name="datefin" value="" />
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Role</label>
            <select name="role">
	    <option value="Chef de projet">Chef de projet</option>
	    <option value="Collaborateur">Collaborateur</option>
	  </select>
             <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
            <label for="name">Engagement</label>
            <input type="text" name="implication" value="" />
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
</div>
</body>
</html>