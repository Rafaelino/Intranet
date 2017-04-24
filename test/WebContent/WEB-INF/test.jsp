<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Ceci est une page générée depuis une JSPL.</p>
		<p>
            <% 
            String attribut = (String) request.getAttribute("test");
           // out.println( attribut );
            %>
            ${employe}
            ${test}
            <% 
            String attribu = (String) request.getAttribute("buttonName");
            //out.println( attribu );
           	 %>
            
        </p>	
         <p>
             <% 
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
      		  %>
            You clicked 
            <%= request.getParameter("buttonName") %>
      	  <%
             }
      	  %>
        <FORM NAME="form1" METHOD="POST">
            <INPUT TYPE="HIDDEN" NAME="buttonName">
            <INPUT TYPE="BUTTON" VALUE="Button 1" ONCLICK="button1()">
            <INPUT TYPE="BUTTON" VALUE="Button 2" ONCLICK="button2()">
        </FORM>
		<input type="button" value="Créer un employé" name="CreateCourse" 
		onclick="openPage('createemploye')" />
		<form name="modificate" action="testapp" method="post">
			 <input type="hidden" name="formname" value="modificate"/>
   			 <button name="modificate" value="upvote">Modifier un employé</button>
		</form>
        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function button1()
            {
                document.form1.buttonName.value = "button 1"
                form1.submit()
            }    
            function button2()
            {
                document.form1.buttonName.value = "button 2"
                form1.submit()
            }    
            // --> 
        </SCRIPT>
        <script type="text/javascript">
		 function openPage(pageURL)
			 {
			 window.location.href = pageURL;
			 }
		</script>
        
        </p>
        
</body>
</html>