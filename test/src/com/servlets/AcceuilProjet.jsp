<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/stylemain.css">
<link rel="stylesheet" href="assets/header-login-signup.css">
  <link rel="stylesheet" href="assets/pure.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="buttonsmenu">
		<div class="pad"></div>
		<div class="pad"></div>
		
		<form action="projectapp" name="modificate"  method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button class ="btn"  name="modificate" value="upvote">Modify a project</button>
		</form>
		<div class="pad"></div>
<div id="create">
	<form action="projectapp" name="create" method="post">
			 <input type="hidden" name="formname" value="create"/>
   			 <button class ="btn" name="create" value="upvote">Create a project</button>
		</form></div>
			
		
			<div class="pad"></div>

</div>
<script type="text/javascript">

if( '${admin}' == "yes"){
}else{
document.getElementById("create").style.visibility = "hidden";
}
</script>
</body>
</html>