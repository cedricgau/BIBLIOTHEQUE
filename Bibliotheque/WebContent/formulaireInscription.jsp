<%@page import="biblio.formbean.InscriptionFormBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="bean" class="biblio.formbean.InscriptionFormBean" scope="session"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un Adherent</title>
</head>
<body>
<p><h2>Formulaire de saisie</h2><p>

<%if(request.getParameter("nom")==null){%>
<form action="formulaireInscription" method="get">

<p>Nom :<input type="text" name="nom" value="gautier"></p>
<p>Prenom :<input type="text" name="prenom" value="cedric"></p>
<p>Mot de passe :<input type="text" name="pwd" value="135"></p>
<p>Pseudonyme :<input type="text" name="pseudo" value="grostoto"></p>
<p>Date de naissance :<input type="text" name="datenaiss" value="1973-04-09"></p>
<p>sexe : Masculin<input type="radio" name="sexe" value="masculin" checked>Féminin<input type="radio" name="sexe" value="feminin"></p>
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
		<% out.println("</p><p>Mot de passe :<input type=text name='pwd' value='135'>");%>
		<% if(bean.getErrors().get("pwd")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("pwd")%><% out.println("</font>");%><%} %>
		<% out.println("</p><p>Pseudonyme :<input type=text name='pseudo' value='grostoto'>");%>
		<% if(bean.getErrors().get("pseudo")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("pseudo")%><% out.println("</font>");%><%} %>
		<% out.println("</p><p>Date de naissance :<input type=text name='datenaiss' value='1973-04-09'>");%>
		<% if(bean.getErrors().get("datenaiss")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("datenaiss")%><% out.println("</font>");%><%} %>
		<% out.println("</p><p>Sexe :Masculin<input type='radio' name='sexe' value='masculin' checked>Féminin <input type='radio' name='sexe' value='feminin'>");%>
		
		<% out.println("</p><p>Téléphone :<input type='text' name='tel' value='0699446587'>");%>
		<% if(bean.getErrors().get("tel")!=null){%><% out.println("<font color=#FF0000>");%><%=bean.getErrors().get("tel")%><% out.println("</font>");%><%} %>
		
		
		<% out.println("</p><input type='hidden' name='page' value='formulaireInscription'><p><input type='submit' value='Ajouter'></p></form>"); %>
<%} %>
</body>
</html>