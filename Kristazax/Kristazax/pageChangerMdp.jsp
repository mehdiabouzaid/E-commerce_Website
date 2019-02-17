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

        <s:form cssClass="bg-white rounded" action="changerMdp">
            <h3 class="mb-4">Changement du mot de passe</h3>
            
            <div class="form-group">
                <s:iterator value="fieldErrors">
                    <s:if test="key == 'motDePasseOld'">
                        <s:iterator value="value">
                            <div class="alert alert-danger">
                                <s:property />
                            </div>
                        </s:iterator>
                    </s:if>
                </s:iterator>
                <s:password name="motDePasseOld" cssClass="form-control" placeholder="Mot de passe actuel"/>
            </div>
    
            <div class="form-group mb-0">
                <s:iterator value="fieldErrors">
                    <s:if test="key == 'motDePasseNew'">
                        <s:iterator value="value">
                            <div class="alert alert-danger">
                                <s:property />
                            </div>
                        </s:iterator>
                    </s:if>
                </s:iterator>
                <s:password name="motDePasseNew" cssClass="form-control" placeholder="Nouveau mot de passe"/>
            </div>
            
            <s:submit cssClass="btn btn-primary btn-block mt-4" value="Changer mot de passe"/>
        </s:form>
        
    </s:if>

    <s:else>
        <jsp:forward page="pageConnexion.jsp" />
    </s:else>

    <s:include value="include/footer.jsp" />
    <s:include value="include/scripts.jsp" />

</body>

</html>