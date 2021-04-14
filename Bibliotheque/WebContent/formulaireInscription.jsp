<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un Adherent</title>
</head>
<body>
<p><h2>Formulaire de saisie</h2><p>
<form action="formulaireInscription" method="get">

<p>Nom :<input type="text" name="nom"></p>
<p>Prenom :<input type="text" name="prenom"></p>
<p>Mot de passe :<input type="text" name="pwd"></p>
<p>Pseudonyme :<input type="text" name="pseudo"></p>
<p>Date de naissance :<input type="text" name="datenaiss"></p>
<p>sexe : Masculin<input type="radio" name="sexe" value="masculin" checked>Féminin<input type="radio" name="sexe" value="feminin"></p>
<p>Catégorie d'utilisateur :<input type="text" name="catutil"></p>
<p>Téléphone :<input type="text" name="tel"></p>
<input type="hidden" name="page" value="formulaireInscription">
<input type="submit" value="Ajouter">
</form>
</body>
</html>