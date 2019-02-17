<!-- Auteur : Flavien Coçu -->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <s:include value="include/header.jsp" />
    <link rel="stylesheet" href="signin.css">
</head>

<body>

    <s:if test="#session.utilisateur != null">
        <jsp:forward page="index.jsp" />
    </s:if>

    <s:else>
    
        <s:form cssClass="bg-white rounded" action="connexion">
            <a href="index.jsp" class="mb-4 h3">Kristazax</a>
            
            <div class="form-group">
                <s:iterator value="fieldErrors">
                    <s:if test="key == 'compte.identifiant'">
                        <s:iterator value="value">
                            <div class="alert alert-danger">
                                <s:property />
                            </div>
                        </s:iterator>
                    </s:if>
                </s:iterator>
                <s:textfield name="compte.identifiant" cssClass="form-control" id="connexion_compte_identifiant" placeholder="Identifiant"/>
            </div>
    
            <div class="form-group mb-0">
                <s:iterator value="fieldErrors">
                    <s:if test="key == 'compte.motDePasse'">
                        <s:iterator value="value">
                            <div class="alert alert-danger">
                                <s:property />
                            </div>
                        </s:iterator>
                    </s:if>
                </s:iterator>
                <s:password name="compte.motDePasse" cssClass="form-control" id="connexion_compte_motDePasse" placeholder="Mot de passe"/>
            </div>
    
            <small class="form-text text-muted text-right"><a href="creationCompte.jsp">Création compte</a></small>
            
            <s:submit cssClass="btn btn-primary btn-block mt-4" value="Connexion"/>
        </s:form>

    </s:else>

    <s:include value="include/scripts.jsp" />

</body>

</html>