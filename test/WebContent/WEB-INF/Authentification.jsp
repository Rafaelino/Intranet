<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
<head>
<meta charset="ISO-8859-1">
       <link rel="stylesheet" href="assets/stylemain.css">

<link type="text/css" rel="stylesheet" href="inc/style.css" />
 
<title>Connexion</title>
 <style type="text/css">
            #customBtn {
                display: inline-block;
                background: white;
                color: #444;
                width: 190px;
                border-radius: 5px;
                border: thin solid #888;
                box-shadow: 1px 1px 1px grey;
                white-space: nowrap;
                position: absolute;
	top: 30%;
	left:120%;
            }
            #customBtn:hover {
                cursor: pointer;
            }
            span.label {
                font-family: serif;
                font-weight: normal;
            }
            span.icon {
                background: url('https://developers.google.com/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
                display: inline-block;
                vertical-align: middle;
                width: 42px;
                height: 42px;
            }
            span.buttonText {
                display: inline-block;
                vertical-align: middle;
                padding-left: 42px;
                padding-right: 42px;
                font-size: 14px;
                font-weight: bold;
                /* Use the Roboto font that is loaded in the <head> */
                font-family: 'Roboto', sans-serif;
            }
            
        </style>
</head>
<body>
<jsp:include page="Header.jsp"/>

 <div class="main">
 <h2>Bienvenue sur le site intranet d'everBe</h2>
 <p>Pour commencer identifiez vous avec votre compte Google</p>
 <div class="idbutton">
 <div id="gSignInWrapper" >
            <span class="label"></span>
            <div id="customBtn" class="customGPlusSignIn">
                <span class="icon"></span>
                <span class="buttonText">Google</span>
            </div>
        </div>
        </div>
 </div>


        <div id="log"></div>

        <script type="text/javascript">
        
        
        
            var auth2;

            function start() {
				
                gapi.load('auth2', function () {
                    auth2 = gapi.auth2.init({
                        client_id: "812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com",
                        scope: 'profile'
                    });
                });
                $('#customBtn').click(function () {
                	
                    auth2.grantOfflineAccess({'redirect_uri' : 'postmessage', 'approval_prompt' : 'force'}).then(signInCallback);

                });
            }

            function signInCallback(authResult) {

                console.log(authResult['code']);
                if (authResult['code']) {

                    // Hide the sign-in button now that the user is authorized, for example:
                    $('#signinButton').attr('style', 'display: none');

                    // Send the code to the server
                    $.ajax({
                      type: 'POST',
                      url: 'Authentification',
                      // Always include an `X-Requested-With` header in every AJAX request,
                      // to protect against CSRF attacks.
                      headers: {
                        'X-Requested-With': 'XMLHttpRequest', 'data' : authResult['code']
                      },
                      contentType: 'application/octet-stream; charset=utf-8',
                      success: function(result) {
                    	  window.location.replace("http://localhost:8080/test/testapp");
                      },
                      processData: false,
                      data:{"code":authResult['code']},
                      data: authResult['code']
                    });
                  } else {
                    // There was an error.
                  }

            }
            window.onload = function() {
   			 document.getElementById("welcome").innerHTML= '';

   			};
        </script>
<!--  
<div class="g-signin2" data-onsuccess="onSignIn">
</div>
<div class="head_text"> 
<p id="demo"></p>
</div>
<script>
	window.onbeforeunload = function(e){
    gapi.auth2.getAuthInstance().signOut();
  };
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
	 
	  alert("hello");
	  //using jquery to post data dynamically
	  var form = $('<form method="post">' +
	                   '<input type="text" name="id_token" value="' +
	                    googleUser.getAuthResponse().id_token + '" />' +
	                                                         '</form>');
	  $('body').append(form);
	  form.submit();
	
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
<a href="#" onclick="signOut();">Déconnection</a></div>-->
         <script src="https://apis.google.com/js/platform.js?onload=start" parsetags="explicit" async defer></script>

</body>
</html>