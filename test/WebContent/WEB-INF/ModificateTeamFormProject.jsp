<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="vis/dist/vis.js"></script>
  <link href="vis/dist/vis.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="assets/stylemain.css">
 	<link rel="stylesheet" href="assets/header-login-signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify project team</title>
</head>
<body>
<script src="assets/tools.js" type="text/javascript" charset="iso-8859-1"></script>
<script>
function ploting(divname,projects){
	//alert(projects.toString())
	var container = divname;
	var myobj = JSON.parse(JSON.stringify(projects));
	var data = eval('(' +projects+ ')');
	  var items = new vis.DataSet(data);
	 
	  // Configuration for the Timeline
	  var options = {};
	   // Create a Timeline
  var timeline = new vis.Timeline(container, items, options);
 
	 /*  timeline.on('select', function (properties) {
	  alert('selected items: ' + JSON.stringify(items.get(properties.items)));
	});*/
  timeline.on('doubleClick', function (properties) {
	//alert(JSON.stringify(items.get(properties.items)));
	var divformname = "itemmodification".concat(divname.id.toString());
	var datedebut = items.get(properties.item).start;
	var datefin = items.get(properties.item).end;
	var name = items.get(properties.item).content.split("(")[0];
	var role = items.get(properties.item).content.split("(")[1].split(")")[0];
	var implication = items.get(properties.item).content.split(") ")[1];
	
	if(document.getElementById(divformname).style.display == "none"){
		document.getElementById(divformname).style.display = "block";}else{
			document.getElementById(divformname).style.display = "none";
		}
	
	 document.getElementById("datedebut".concat(divname.id.toString())).value = datedebut;
	 document.getElementById("datefin".concat(divname.id.toString())).value = datefin;
	 document.getElementById("implication".concat(divname.id.toString())).value = implication;
	 document.getElementById("name".concat(divname.id.toString())).innerHTML ="Modifiy work period for "+ name+" :";
	
	 document.getElementById("data_datefin".concat(divname.id.toString())).value = datefin;
	 document.getElementById("data_datedebut".concat(divname.id.toString())).value = datedebut;
	 document.getElementById("data_implication".concat(divname.id.toString())).value = implication;
	 document.getElementById("data_role".concat(divname.id.toString())).value = role;
	 document.getElementById("data_employename".concat(divname.id.toString())).value = name;
	 
	 document.getElementById("del_data_datefin".concat(divname.id.toString())).value = datefin;
	 document.getElementById("del_data_datedebut".concat(divname.id.toString())).value = datedebut;
	 document.getElementById("del_data_implication".concat(divname.id.toString())).value = implication;
	 document.getElementById("del_data_role".concat(divname.id.toString())).value = role;
	 document.getElementById("del_data_employename".concat(divname.id.toString())).value = name;

    });
	}
	
</script>
 <jsp:include page="Header.jsp"/>

<input type="hidden" name="nom" value="${projectname}"/>
<div class ="projects" style="display:block">
<h1>Project : ${projectname}</h1>

<table>
      <caption></caption>
      <thead>
        <tr>
          <th scope="col">Description</th>
          <th scope="col">Project Managers</th>
          <th scope="col">Collaborators</th>
          
        </tr>
      </thead>
      <tfoot>
        <tr>
          <td colspan="3"></td>
        </tr>
      </tfoot>
      <tbody>
      <tr>
          <th scope="col">${description}</th>
          <th scope="col"><ul><c:forEach items="${managers}" var="element"> 
<input type="hidden" name="employename" value="${element}"/>  
 <li> ${element} 
 

 </li>
 
</c:forEach></p>
</ul></th>

          <th scope="col"><ul><c:forEach items="${collaborateurs}" var="element"> 
 <input type="hidden" name="employename" value="${element}"/>  

 
 <li> ${element} 
 
 
 
 </li>
 
</c:forEach>
</ul></th>
       
        </tr>
     </tbody>
    </table>
    
    
<p></p>


<script>
			  			var projectlisting = "[";
			  	</script>
			  	
			  			<input type="hidden" id="listing" name="x" value="${employedrawlisting}">
			
			  
			

		

			 <div class="drawing" id="${projectname}" value="${projectname}" >
			
			 <h2><button class ="btn_small" onclick="display('itemadd${projectname}')" >Add work period</button> </h2>
			 
			 <%-- <form name="create" class="draw" method="post">
			 	<input type="hidden" name="formname" value="addperiod"/>
   			    <button class ="btn_small" name="create" value="upvote">+</button>
			</form> --%>
			
			 </div>
			<div id="itemmodification${projectname}" class="itemform" style="display:none">
<form name ="formica${projectname}" id ="formica${projectname}" method="post" onsubmit="return validateForm(event,'${projectname}');" class="pure-form">

		<input type="hidden" name="formname" value="modificateperiod"/>
		<input type="hidden" id="data_employename${projectname}" name="employename" value=""/>
		<input type="hidden" id="data_datedebut${projectname}" value=""/>
		<input type="hidden" id="data_datefin${projectname}" value=""/>
		<input type="hidden" id="data_implication${projectname}" value=""/>
		<input type="hidden" id="data_role${projectname}" name="role" value=""/>
		<input type="hidden"  name="nom" value="${projectname}"/>
		
   <fieldset>
       
		<div id="name${projectname}">Modify work period for <p></p>
		
		</div>
        Start date : <input type="date" id="datedebut${projectname}" name="datedebut" placeholder="Date de début">
        End date : <input type="date" id="datefin${projectname}" name="datefin" placeholder="Date de fin">
        Implication : <input type="text" id="implication${projectname}" name="implication" placeholder="Implication">            <span id="implicationspan${projectname}" class="pure-form-message-inline"></span>
        



        <button type="submit" class="pure-button pure-button-primary">Save</button>
    </fieldset>
    
        </form>
        <form method="post" class="pure-form">

		<input type="hidden" name="formname" value="deleteperiod"/>
		<input type="hidden" id="del_data_employename${projectname}" name="employename" value=""/>
		<input type="hidden" id="del_data_datedebut${projectname}" name="datedebut" value=""/>
		<input type="hidden" id="del_data_datefin${projectname}" name="datefin" value=""/>
		<input type="hidden" id="del_data_implication${projectname}" name="implication" value=""/>
		<input type="hidden" id="del_data_role${projectname}" name="role" value=""/>
		<input type="hidden"  name="nom" value="${projectname}"/>
		
        <button type="submit" class="pure-button pure-button-primary">Delete</button>
 
    
        </form>
        </div>
        <div id="itemadd${projectname}" class="itemform" style="display:none">
        	
        
       <form name="ajout${projectname}" id="ajout${projectname}" method="post" onsubmit="return validateFormAdd(event,'${projectname}');" class="pure-form">

		<input type="hidden" name="formname" value="addperiod"/>
		<input type="hidden" id="add_data_employename${projectname}" name="employename" value=""/>
		<input type="hidden" id="add_data_datedebut${projectname}" value=""/>
		<input type="hidden" id="add_data_datefin${projectname}" value=""/>
		<input type="hidden" id="add_data_implication${projectname}" value=""/>
		<input type="hidden" id="add_data_role${projectname}" name="role" value=""/>
		<input type="hidden"  name="nom" value="${projectname}"/>
		
   <fieldset>
       
		<div id="add_name${projectname}">Add work period <p></p>
		
		</div>
		
		Employee : <select name="listemploye">
	    <option value="1"></option>
	    <%
		 List<String> employees = (List<String>) request.getAttribute("employees");
		 for (String employe : employees)
		 { 
		out.print("<option value='"+employe.split("@")[1]+"'>"+employe.split("@")[0]+"</option>");
		
		 }
		%>
	  </select>
        Start date : <input type="date" id="add_datedebut${projectname}" name="datedebut" placeholder="Date de début">
        End date : <input type="date" id="add_datefin${projectname}" name="datefin" placeholder="Date de fin">
        Implication : <input type="text" id="add_implication${projectname}" name="implication" placeholder="Implication">  <span id="implicationaddspan${projectname}" class="pure-form-message-inline"></span>
		Role : <select name="listrole">
	    <option value="Collaborator">Collaborator</option>
	    <option value="Project manager">Project manager</option>
	   
	  </select>


        <button type="submit" class="pure-button pure-button-primary">Save</button>
    </fieldset>
        </form>
        </div>


	 

	
</ul>

			
<div id="visualization"></div>










<form name = "suppression" method = "post" onsubmit="return validateFormDelete(event,suppression);" >
   <input type="hidden" name="formname" value="deleteproject"/>
     <input type="hidden" name="nom" value="${projectname}"/>
  <input type="submit" class="btn_small" value ="Delete the project"/>
</form>
<form  action ="testapp">
   			 <button class="btn_small">Home</button>
</form>
    </div>


<script>
var input = document.getElementById("listing").value;

var fields = input.split('@');
for (var i = 0; i < fields.length; i++) {
	var employees = fields[i].split(';');	
	if(typeof employees[1] !== 'undefined'){
	if(employees[1].length > 0){
		document.getElementById(employees[0]).style.visibility = 'visible';
	ploting(document.getElementById(employees[0]),employees[1]);}
	}
}
function display(divformname) {
	
	if(document.getElementById(divformname).style.display == "none"){
	document.getElementById(divformname).style.display = "block";}else{
		document.getElementById(divformname).style.display = "none";
	}
}

function validateForm(event,divname) {
	event.preventDefault();
	var str1 = "formica";
	var res = str1.concat(divname);
	var str2 = "implicationspan";
	var res2 = str2.concat(divname);
   var x = document.forms[res]["implication"].value;
   var datedebut = document.forms[res]["datedebut"].value.replace("-","");
   var datefin = document.forms[res]["datefin"].value.replace("-","");
   var err=0;
   if (x == "") {
   	 document.getElementById(res2).style.color='red';
   	 document.getElementById(res2).innerHTML=' Implication format incorect, must be (0-100%)';
       err = 1;
   }
   if (datedebut>=datefin){
   	 document.getElementById(res2).style.color='red';
   	 document.getElementById(res2).innerHTML='Start date must be anterior to end date';
       err = 1;
   	
   }
  
  if (err == 1){
	   return false;
  }else{
	   document.getElementById(res).submit();
	 

  }

}
function validateFormAdd(event,divname) {
	event.preventDefault();
	var str1 = "ajout";
	var res = str1.concat(divname);
	var str2 = "implicationaddspan";
	var res2 = str2.concat(divname);
   var x = document.forms[res]["implication"].value;
   var employe = document.forms[res]["listemploye"].value;
   var datedebut = document.forms[res]["datedebut"].value.replace("-","");
   var datefin = document.forms[res]["datefin"].value.replace("-","");
   var err=0;
   if (x == "") {
   	 document.getElementById(res2).style.color='red';
   	 document.getElementById(res2).innerHTML=' Implication format is incorect, must be ( 0-100%)';
       err = 1;
   }
   if (datedebut>=datefin){
   	 document.getElementById(res2).style.color='red';
   	 document.getElementById(res2).innerHTML='Start date must be anterior to end date';
       err = 1;
   	
   }
  if (employe == "1"){
		 document.getElementById(res2).style.color='red';
   	 document.getElementById(res2).innerHTML='An employee has to be selected';
       err = 1;
  }
  if (err == 1){
	   return false;
  }else{
	  
	   document.getElementById(res).submit();

  }

}
</script>

</body>
</html>