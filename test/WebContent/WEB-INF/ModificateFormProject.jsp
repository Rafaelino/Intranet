<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>everBe</title>
</head>
<body>
<form action ="testapp">
   			 <button>Home</button>
</form>
<form method="post">
	<input type="hidden" name="formname" value="modificateform"/>
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Modify project information : </th>
                    </tr>
                </thead>
                
                <tbody>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="nom" value="${nom}"></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="${description}" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Save" /></td>
                    </tr>
              
                </tbody>
            </table>
            </center>
        </form>
        <form method="post">
	<input type="hidden" id="form1" name="formname" onsubmit="return validateForm(event,suppression);" value="modificateteamform"/>
            <center>
            <input type="hidden" name="nom" value="${nom}"/>
            
                  <input type="submit" value ="Modifier �quipe de projet" />
            </center>
        </form>		
        <script>
        function validateForm(event, formname) {
        	
       	 event.preventDefault();
        
       	 if(confirm('Voulez vous effectuer cette action ?')){
       		 document.document.formname.submit();
       	 }
       	
          }</script>
</body>
</html>