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
<h2>Ajouter un nouveau projet</h2>
<form name="formica" method="post" onsubmit="return validateForm(event);" class="pure-form pure-form-aligned">
<input type="hidden" name="formname" value="saveproject"/>
	<input type="hidden" name="formname" value="saveemploye"/>
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Nom</label>
            <input id="name" name="nom" type="text" placeholder="Nom">
            <span id="nomspan" class="pure-form-message-inline">Champ Obligatoire</span>
        </div>
         <div class="pure-control-group">
            <label for="name">Description</label>
            <input id="name" name="description" type="text" placeholder="Description">
            <span class="pure-form-message-inline"></span>
        </div>
         <div class="pure-control-group">
           
            <input class="btn" type="submit" value="Enregistrer" />
            <span class="pure-form-message-inline"></span>
        </div>
        </fieldset>
        </form>
        </div>
<script>
function validateForm(event) {
	event.preventDefault();
    var x = document.forms["formica"]["nom"].value;
    var err=0;
    if (x == "") {
    	 document.getElementById("nomspan").style.color='red';
    	 document.getElementById("nomspan").innerHTML='Ce champ doît être renseigné';
        err = 1;
    }
   if (err == 1){
	   return false;
   }else{
	   document.formica.submit();
   }
}
</script>
</body>
</html>