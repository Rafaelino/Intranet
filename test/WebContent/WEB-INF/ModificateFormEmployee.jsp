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
	<input type="hidden" name="formname" value="modificateform"/>
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Mofifier informations Employé : </th>
                    </tr>
                </thead>
                
                <tbody>
                    <tr>
                        <td>Nom</td>
                        <td><input type="text" name="nom" value="${employe.nom}"></td>
                    </tr>
                    <tr>
                        <td>Prénom</td>
                        <td><input type="text" name="prenom" value="${employe.prenom}" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="${employe.email}" /></td>
                    </tr>
                    <tr>
                        <td>Adresse</td>
                        <td><input type="text" name="adresse" value="${employe.adresse}" /></td>
                    </tr>
                    <tr>
                        <td>Mot de passe</td>
                        <td><input type="password" name="motdepasse" value="${employe.password}" /></td>
                    </tr>
                     <tr>
                        <td>Date de naissance (jjmmaaaa)</td>
                        <td><input type="text" name="datedenaissance" value="${employe.dateDeNaissance}" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Enregistrer" /></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
        </form>
        <form method="post">
        <input type="hidden" name="formname" value="suppression"/>
        <input type="hidden" name="nom" value="${username}">
        <center>
        Autre action :  <td><input type="submit" value="Supprimer l'employé"/></td>
        </center>
        </form>
</body>
</html>