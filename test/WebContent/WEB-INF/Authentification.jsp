<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
<head>
<meta charset="ISO-8859-1">
       

<link type="text/css" rel="stylesheet" href="inc/style.css" />
 
<title>Connexion</title>
</head>
<body>
<div id="header"></div>
<div class="g-signin2" data-onsuccess="onSignIn">
</div>
<div class="head_text"> 
<p id="demo"></p>
</div>
<script>
$(function(){
	  $("#header").load("header-login-signup.html"); 
	  $("#footer").load("footer.html"); 
	});
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	  document.getElementById("demo").innerHTML = "Bienvenue " + profile.getName();// This is null if the 'email' scope is not present.
	  document.getElementsByClassName("deconnection").style.visibility = 'visible';
	}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
  	  document.getElementById("demo").innerHTML = "Vous êtes déconnecté";
  		document.getElementsByClassName("deconnection").style.visibility = 'hidden';
    });
    
  }
</script>
<div class="deconnection">
<a href="#" onclick="signOut();">Déconnection</a></div>
</body>
</html>