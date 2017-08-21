<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <link rel="stylesheet" href="assets/stylemain.css">
 <link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">

	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="profilepic">
<!-- <img src="/test/profilepicture${employe.username}.jpg" alt="profilepicture${employe.username}.jpg" > -->
</div>
<div class="flex-rectangle">
 	<div class="drawer">
 <ul>
  <li id="liinfo" class ="active"><button class="inv_button" onclick="display('informations','liinfo')">My informations</button></a></li>
  <li id="liprojet" class ="not_active"><button class="inv_button" onclick="display('projets','liprojet')">My projects</button></a>  </li>
  
  
</ul>
</div>
</div>

<h1> </h1>
		<c:set var = "datenaissance" value = "${employe.dateDeNaissance}"/>


<div id="informations" style="display:block">
<div class ="projects" style="display:block">

<h2>My Informations 	<form method="post" >
				 <input type="hidden" name="formname" value="modificateemploye"/>
   				 <button class="btn_small">Modify</button>
		</form> </h2>
		
<table>
      <caption></caption>
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Email</th>
          <th scope="col">Address</th>
          <th scope="col">Birth date</th>
        </tr>
      </thead>
      <tfoot>
        <tr>
          <td colspan="3"></td>
        </tr>
      </tfoot>
      <tbody>
      <tr>
          <th scope="col">${employe.prenom} ${employe.nom}</th>
          <th scope="col">${employe.email}</th>
          <th scope="col">${employe.adresse}</th>
          <th scope="col">${fn:substring(datenaissance,8,10)}/${fn:substring(datenaissance,5,7)}/${fn:substring(datenaissance,0,4)}</th>
        </tr>
     </tbody>
    </table>
    <p></p>
 	<form method="post" >
				 <input type="hidden" name="formname" value="logout"/>
   				 <button class="btn_small">Sign out</button>
		</form> 
</div>
</div>


<div class ="projects" id ="projets" style="display:none">
<h2>My Projects</h2>
<table>
      <caption></caption>
      <thead>
        <tr>
          <th scope="col">Project</th>
          <th scope="col">Role</th>
          <th scope="col">Start date</th>
          <th scope="col">End date</th>
          <th scope="col">Team members</th>
        </tr>
      </thead>
      <tfoot>
        <tr>
          <td colspan="3"></td>
        </tr>
      </tfoot>
      <tbody>
      
<c:forEach items="${projects}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <c:set var = "salary" scope = "session" value = "${element.role}"/>
    
 <c:set var = "employelistname" value = "employelist${element.name}"/>
<c:set var = "datedebut" value = "${element.dateDebut}"/>
<c:set var = "dateFin" value = "${element.dateFin}"/>

        <tr>
          <th scope="col">${element.name}</th>
          <th scope="col">${element.role}</th>
          <th scope="col">${fn:substring(datedebut,8,10)}/${fn:substring(datedebut,5,7)}/${fn:substring(datedebut,0,4)}</th>
          <th scope="col">${fn:substring(dateFin,8,10)}/${fn:substring(dateFin,5,7)}/${fn:substring(dateFin,0,4)}</th>
          <th scope="col"><ul><c:forEach items="${requestScope[employelistname]}" var="employelisting"> 
 	<c:set var = "username" scope = "session" value = "${employelisting.username}"/>
 	 	<c:set var = "username2" scope = "session" value = "${employe.username}"/>
 	
 	<c:if test = "${username != username2}">
 	<li><p><form method="post">
   <a href="javascript:;" onclick="parentNode.submit();">${employelisting.nom} ${employelisting.prenom}  </a>
    <input type="hidden" name="formname" value="!${employelisting.username}"/>
</form>

 		</li>
 		</c:if>
 	</c:forEach></ul></th>
        </tr>
  
        
  
 	
 <c:if test = "${salary == 'Chef de projet'}">
         <form method="post" >
				 <input type="hidden" name="formname" value="modificate"/>
				  <input type="hidden" name="projectname" value="${element.name}"/>
				 
   			
		</form>
      </c:if> 

</c:forEach>

     </tbody>
    </table>

 <input type="hidden" id="usernameid" value="Connectée en tant que ${name}"/>
 </div>

<script type="text/javascript">

function display(divformname,liname) {
	document.getElementById('projets').style.display = "none";
	document.getElementById('liprojet').className = "not_active";
	document.getElementById('liinfo').className = "not_active";
	document.getElementById('informations').style.display = "none";
	if(document.getElementById(divformname).style.display == "none"){
		if(liname === "liprojet"){
	document.getElementById(liname).className = "active2";}else{
		document.getElementById(liname).className = "active";
	}
	document.getElementById(divformname).style.display = "block";}else{
		document.getElementById(divformname).style.display = "none";
	}
}
if( '${admin}' == "yes"){
	 document.getElementById("adminemploye").innerHTML='Gérer les employés';
	 document.getElementById("adminproject").innerHTML='Gérer les projets';
	}

</script>
</body>
</html>