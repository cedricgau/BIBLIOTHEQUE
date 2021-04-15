<%@page import="biblio.formbean.InscriptionFormBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="bean" class="biblio.formbean.InscriptionFormBean" scope="request"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un Adherent</title>
</head>
<body>
<p><h2>Formulaire de saisie</h2><p>

<%if(request.getParameter("duree")==null){%>
<form action="formulaireInscription" method="get">

<p>Nom :<input type="text" name="nom" value="gautier"></p>
<p>Prenom :<input type="text" name="prenom" value="cedric"></p>
<p>Mot de passe :<input type="text" name="pwd" value="cedric"></p>
<p>Pseudonyme :<input type="text" name="pseudo" value="ced"></p>
<p>Date de naissance :<input type="text" name="datenaiss" value="09/04/1973"></p>
<p>sexe : Masculin<input type="radio" name="sexe" value="masculin" checked>Féminin<input type="radio" name="sexe" value="feminin"></p>
<p>Catégorie d'utilisateur :<input type="text" name="catutil"></p>
<p>Téléphone :<input type="text" name="tel" value="0699446587"></p>
<input type="hidden" name="page" value="formulaireInscription">
<input type="submit" value="Ajouter">
</form>
<%}else{ %>

		<% bean = (InscriptionFormBean) (session.getAttribute("rerem"));%>
		
		<% out.println("<form action='formulaireInscription' method='get'><p>Nom :<input type=text name='nom' value='gautier'>");%>
		<% if(bean.getErrors().get("nom")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("nom")%><% out.println("</font>");%><%} %>		
		<% out.println("</p><p>Prenom :<input type=text name='prenom' value='cedric'>");%>
		<% if(bean.getErrors().get("prenom")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("prenom")%><% out.println("</font>");%><%} %>
		<% out.println("</p><p>Mot de passe :<input type=text name='pwd' value='cedric'>");%>
		<% if(bean.getErrors().get("pwd")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("pwd")%><% out.println("</font>");%><%} %>
		<% out.println("</p><input type='hidden' name='page' value='formulaireInscription'><p><input type='submit' value='Ajouter'></p></form>"); %>
<%} %>
</body>
</html>