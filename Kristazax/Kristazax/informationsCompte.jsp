<!-- Auteur : Flavien Coçu -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <s:include value="include/header.jsp" />
    <s:include value="include/scripts.jsp" />
	<script type="text/javascript" src="pageInfoCompteUser.js"></script>
</head>

<body>
    <s:include value="include/navbar.jsp" />

	<s:if test="#session.utilisateur == null">

		<jsp:forward page="pageConnexion.jsp" />
		
	</s:if>

	<script>
		var idCompte = <% out.println(request.getParameter("idVendeur")); %>
		var url3 = "<s:url action="ajax_obtenirInformation"/>";
		var url2 = "<s:url action="ajax_obtenirInformation"/>";
		var nomDeCompte = <% out.println("'"+session.getAttribute("utilisateur")+"'"); %>
    </script>

	<s:else>
		<div class="container bg-white p-5 rounded">
			<div class="row">
				<div id="information" class="col-lg-8 offset-lg-2"> </div>
				<div class="col-lg-8 offset-lg-2 pt-3">
					<a href='deconnexion' class="btn btn-primary">Déconnexion</a>
					<a href='index' class="btn btn-primary">Retour index</a>
				</div>
				<s:if test="#session.typeCompte == 'Administrateur' && #session.utilisateur != null">
					<div id="boutonAdmin" class="col-lg-8 offset-lg-2 pt-3">
					<a href='administration.jsp' class="btn btn-primary">Gestion admins</a>
					<a href='gestionAnnonces.jsp' class="btn btn-primary">Gestion annonces</a>
					</div>
    			</s:if>
			</div>
		</div>
	</s:else>

    <s:include value="include/footer.jsp" />
</body>

</html>
