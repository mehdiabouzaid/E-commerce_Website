<!-- Auteur : Flavien Coçu -->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <s:include value="include/scripts.jsp" />
    <s:include value="include/header.jsp" />
    <script type="text/javascript" src="pageAfficherArticle.js"></script>
</head>

<body>
    <script>
        var idArticle = <% out.println(request.getParameter("idArticle")); %>
        var url = "<s:url action="ajax_obtenirArticle"/>";
        var url2 = "<s:url action="ajax_ajoutArticlePanier"/>";
    </script>
    <s:include value="include/navbar.jsp" />

    <div class="container bg-white p-5 rounded">
        <div class="row">
            <div class="col-md-4" id="imageID"></div>
            <div class="col-md-8">
                <div class="row mt-5 bt-3 border-bottom">
                    <div class="col-md-8" id="descriptionDebut"></div>
                    <div class="col-md-4 text-center my-3 my-md-auto" id="ajoutPanier"></div>
                </div>
                <div class="row">
                    <div class="col-md-8" id="descriptionFin"></div>
                    <div class="col-md-4 text-center my-3 my-md-auto" id="afficherVendeur"></div>
                </div>
            </div>
        </div>
    </div>

    <s:include value="include/footer.jsp" />

</body>

</html>