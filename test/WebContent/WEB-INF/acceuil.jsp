<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<style>
	input {
	font-size: 80%;
	}
</style>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
 <link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="812436705620-ia2pj458gq3m4eqs63dgee9fsrrrlq7u.apps.googleusercontent.com">
	<title>Login, Sign up Header</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	
</head>
<body>
<script src="assets/autocompleter.js" type="text/javascript" charset="iso-8859-1"></script>


<jsp:include page="Header.jsp"/>

<div class="main">

<p>Bievenue sur le site intranet d'everBe</p>

<div class="navigate">
			
			<div class="box"><svg class="svg-icon" viewBox="0 0 20 20">
							<path d="M17.431,2.156h-3.715c-0.228,0-0.413,0.186-0.413,0.413v6.973h-2.89V6.687c0-0.229-0.186-0.413-0.413-0.413H6.285c-0.228,0-0.413,0.184-0.413,0.413v6.388H2.569c-0.227,0-0.413,0.187-0.413,0.413v3.942c0,0.228,0.186,0.413,0.413,0.413h14.862c0.228,0,0.413-0.186,0.413-0.413V2.569C17.844,2.342,17.658,2.156,17.431,2.156 M5.872,17.019h-2.89v-3.117h2.89V17.019zM9.587,17.019h-2.89V7.1h2.89V17.019z M13.303,17.019h-2.89v-6.651h2.89V17.019z M17.019,17.019h-2.891V2.982h2.891V17.019z"></path>
						</svg><a class="boxlink" href="/test/drawapp">Afficher les projets</a></div>
			<div class="box" id="adminemployediv"><svg class="svg-icon" viewBox="0 0 20 20">
					<path d="M15.573,11.624c0.568-0.478,0.947-1.219,0.947-2.019c0-1.37-1.108-2.569-2.371-2.569s-2.371,1.2-2.371,2.569c0,0.8,0.379,1.542,0.946,2.019c-0.253,0.089-0.496,0.2-0.728,0.332c-0.743-0.898-1.745-1.573-2.891-1.911c0.877-0.61,1.486-1.666,1.486-2.812c0-1.79-1.479-3.359-3.162-3.359S4.269,5.443,4.269,7.233c0,1.146,0.608,2.202,1.486,2.812c-2.454,0.725-4.252,2.998-4.252,5.685c0,0.218,0.178,0.396,0.395,0.396h16.203c0.218,0,0.396-0.178,0.396-0.396C18.497,13.831,17.273,12.216,15.573,11.624 M12.568,9.605c0-0.822,0.689-1.779,1.581-1.779s1.58,0.957,1.58,1.779s-0.688,1.779-1.58,1.779S12.568,10.427,12.568,9.605 M5.06,7.233c0-1.213,1.014-2.569,2.371-2.569c1.358,0,2.371,1.355,2.371,2.569S8.789,9.802,7.431,9.802C6.073,9.802,5.06,8.447,5.06,7.233 M2.309,15.335c0.202-2.649,2.423-4.742,5.122-4.742s4.921,2.093,5.122,4.742H2.309z M13.346,15.335c-0.067-0.997-0.382-1.928-0.882-2.732c0.502-0.271,1.075-0.429,1.686-0.429c1.828,0,3.338,1.385,3.535,3.161H13.346z"></path>
				</svg><a class="boxlink" href="/test/employeapp" id="adminemploye"></a></div>
			<div class="box" id="adminprojectdiv"><svg class="svg-icon" viewBox="0 0 20 20">
							<path d="M10.25,2.375c-4.212,0-7.625,3.413-7.625,7.625s3.413,7.625,7.625,7.625s7.625-3.413,7.625-7.625S14.462,2.375,10.25,2.375M10.651,16.811v-0.403c0-0.221-0.181-0.401-0.401-0.401s-0.401,0.181-0.401,0.401v0.403c-3.443-0.201-6.208-2.966-6.409-6.409h0.404c0.22,0,0.401-0.181,0.401-0.401S4.063,9.599,3.843,9.599H3.439C3.64,6.155,6.405,3.391,9.849,3.19v0.403c0,0.22,0.181,0.401,0.401,0.401s0.401-0.181,0.401-0.401V3.19c3.443,0.201,6.208,2.965,6.409,6.409h-0.404c-0.22,0-0.4,0.181-0.4,0.401s0.181,0.401,0.4,0.401h0.404C16.859,13.845,14.095,16.609,10.651,16.811 M12.662,12.412c-0.156,0.156-0.409,0.159-0.568,0l-2.127-2.129C9.986,10.302,9.849,10.192,9.849,10V5.184c0-0.221,0.181-0.401,0.401-0.401s0.401,0.181,0.401,0.401v4.651l2.011,2.008C12.818,12.001,12.818,12.256,12.662,12.412"></path>
						</svg><a class="boxlink" href="/test/projectapp" id="adminproject"></a></div>
	</div>	

			
			<!-- <a href="#">Pricing</a> -->
		
</div>
		<p>
            <% 
            String attribut = (String) request.getAttribute("test");
           // out.println(attribut);
            %>
           <%--  ${employe}
            ${test}
            <% 
            String attribu = (String) request.getAttribute("buttonName");
            //out.println(attribu);
           	 %>
            --%>
        </p>	
         <p>
             <% 
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
      		  %>
          
           
      	  <%
             }
      	  %>
      
		<%-- <input type="button" value="Créer un employé" name="createemploye"
		onclick="openPage('createemploye')" />--%>
		
		<%--
		<form action ="projectapp">
   			 <button>Gérer les projets</button>
		</form>
		<form action ="drawapp">
   			 <button>Afficher répartition projets</button>
		</form>--%>
       
        <script type="text/javascript">
		/* function openPage(pageURL)
			 {
			 window.location.href = pageURL;
			 }*/
		
			// document.getElementById("welcome").innerHTML='Bonjour ${name}';
			 //alert('${admin}');
			if( '${admin}' == "yes"){
				 document.getElementById("adminemploye").innerHTML='Gérer les employés';
				 document.getElementById("adminproject").innerHTML='Gérer les projets';
				}else{
					 document.getElementById("adminemployediv").style.visibility = "hidden";
					 document.getElementById("adminprojectdiv").style.visibility = "hidden";
				}
				

			</script>
        
       
        
</body>
</html>