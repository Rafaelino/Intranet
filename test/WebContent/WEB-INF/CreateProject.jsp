<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<input type="hidden" name="formname" value="saveproject"/>
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Ajouter un nouvel employ� : </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nom</td>
                        <td><input type="text" name="nom" value="" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="" /></td>
                    </tr>        
                    <tr>
                        
                        <td><input type="submit" value="Enregistrer" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
</body>
</html>