<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentification</title>
<h2><%= new Date() %></h2>
<% Cookie[] user = request.getCookies();

if (user!=null && user.length>0) out.print("Bienvenu(e) : "+user[0].getValue()); %>
<h3>Veuillez vous authentifier</h3>
<form action="gogogo" method="get">
<p>Nom d'utilisateur :  <input type=text length=20 value=biblio name="usr"><br>
Mot de passe :&ensp;&ensp;&ensp;  <input type=password length=20 value=bibli name="pwd"><br><br></p>
<input type="hidden" name="page" value="login">
<p><input value="VALIDER" type=submit></p>
</form>
</head>
<body>

</body>
</html>