<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="assets/pure.css">

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <link rel="stylesheet" href="assets/stylemain.css">
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mon Compte</title>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">

	<link rel="stylesheet" href="assets/demo.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="Header.jsp"/>
<form method="post" class="pure-form pure-form-stacked">
    <fieldset>
        <legend></legend>

        <div class="pure-g">

	<input type="hidden" name="formname" value="selection"/>
	  <div class="pure-u-1 pure-u-md-1-3">
	   <label for="state"></label>
	  <select  onchange="this.form.submit()" name="listemploye">
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
	  <p></p>
 
  </div>
  </div>
  </fieldset>
  
</form>
<div class="messageframe">
<div class="messagelist">

</div>
</div>
<div class="main">
A ${receiver.prenom} ${receiver.nom}<p></p>

<div id="chat" class="chat">

	<% List<String> messagelist = (List<String>) request.getAttribute("messagelist");
 	if(!(messagelist == null)){
		 for (String message : messagelist)
		 { 
			 System.out.println(request.getAttribute("receiverusername"));
			 System.out.println((message.split(";")[0]));
			 if(message.split(";")[0].equals(request.getAttribute("receiverusername"))){
			out.print("<p class=\"fromout\">" +message.split(";")[1] +"</p>");
			 }else{
				 out.print("<p class=\"fromin\">"+ message.split(";")[1]+"</p>");
			 }
		 }
 	}
		%>
</div>
<form method="post" >

				 <input type="hidden" name="formname" value="send"/>
				 <input type="hidden" id="employename" name="receiver" value="${receiver.username}"/>
				<textarea id="message" name="message" rows="5" cols="40" tabindex="2"></textarea>
				
   				 <button class="btn">Envoyer</button>
		</form> 
</div>
<script type="text/javascript">


	
	(function refresh() {
		  $.ajax({
			type: 'POST',
			url: 'message', 
			 headers: {
                 'code': 'refresh','employename': document.getElementById("employename").value
               },
		    success: function(data) {
	//   alert(data);
		     refreshMessageListData(data);
		    },
		    complete: function() {
		      // Schedule the next request when the current one's complete
		      setTimeout(refresh, 5000);
		    }
		  });
		})();
	
	function refreshMessageListData(data){
		var messages = data.split(",");
		document.getElementById("chat").innerHTML = "";
		/*for (var i = 1; i < messages.length-1; i++) {
			//alert(messages[i].split(";")[1].substring(0,messages[i].split(";")[1].length-1));
			messages[i].split(";")[1] = messages[i].split(";")[1].substring(0,messages[i].split(";")[1].length-1);
		}*/
		if(messages[0].split(";")[0].substring(2,messages[0].length) == document.getElementById("employename").value){
			document.getElementById("chat").innerHTML +="<p class=\"fromout\">" +messages[0].split(";")[1].substring(0,messages[0].split(";")[1].length-1) +"</p>";
		}else{
			document.getElementById("chat").innerHTML += "<p class=\"fromin\">" +messages[0].split(";")[1].substring(0,messages[0].split(";")[1].length-1) +"</p>";

		}
		for (var i = 1; i < messages.length-1; i++) {
			if(messages[i].split(";")[0].substring(1,messages[i].length) == document.getElementById("employename").value){
				document.getElementById("chat").innerHTML +="<p class=\"fromout\">" +messages[i].split(";")[1].substring(0,messages[i].split(";")[1].length-1) +"</p>";
			}else{
				document.getElementById("chat").innerHTML += "<p class=\"fromin\">" +messages[i].split(";")[1].substring(0,messages[i].split(";")[1].length-1) +"</p>";

			}
		}
		if(messages[messages.length-1].split(";")[0].substring(1,messages[0].length) == document.getElementById("employename").value){
			document.getElementById("chat").innerHTML +="<p class=\"fromout\">" +messages[messages.length-1].split(";")[1].substring(0,messages[messages.length-1].split(";")[1].length-2) +"</p>";
		}else{
			document.getElementById("chat").innerHTML += "<p class=\"fromin\">" +messages[messages.length-1].split(";")[1].substring(0,messages[messages.length-1].split(";")[1].length-2) +"</p>";

		}
		//document
	}
		
	
</script>
</body>
</html>