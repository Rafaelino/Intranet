<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cr�er Employ�</title>
</head>
<body>
<form method="post">
		<input type="hidden" name="formname" value="saveemploye"/>
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
                        <td>Pr�nom</td>
                        <td><input type="text" name="prenom" value="" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /></td>
                    </tr>
                    <tr>
                        <td>Adresse</td>
                        <td><input type="text" name="adresse" value="" /></td>
                    </tr>
                    <tr>
                        <td>Mot de passe</td>
                        <td><input type="password" name="motdepasse" value="" /></td>
                    </tr>
                     <tr>
                        <td>Date de naissance (jjmmaaaa)</td>
                        <td><input type="text" name="datedenaissance" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enregistrer" /></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
        </form>
        <button type="button" name="back" onclick="history.back()">Retour</button>
        </body>
</html>