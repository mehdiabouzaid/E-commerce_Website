<!-- Auteur : Thomas Di Gregorio, Flavien Coçu -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String texte = request.getParameter("texte") == null ? "" : request.getParameter("texte");
    String categorie = request.getParameter("categorie") == null ? "" : request.getParameter("categorie");
    String lieu = request.getParameter("lieu") == null ? "" : request.getParameter("lieu");
    String prix = request.getParameter("prix") == null ? "" : request.getParameter("prix");
%>

<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <s:include value="include/scripts.jsp" />
    <s:include value="include/header.jsp" />
</head>

<body>

    <s:include value="include/navbar.jsp" />

    <div class="container bg-white p-5 rounded">
        <h5 class="mb-5">
            Annonces
            <%
                if (!categorie.equals("")) {
                    out.print(categorie);

                    if (!texte.equals("")) out.print(" - ");
                }

                if (!texte.equals("")) out.print(texte);
                if (!prix.equals("")) out.print(" < " + prix + "€");
                if (!lieu.equals("")) out.print(" (" + lieu + ")");
            %>
        </h5>
        <div class="row" id="resultat">
            <script type="text/javascript">
                $(function() {
                    $.post(
                        "<s:url action='ajax_rechercheArticleAvancee' />",
                        data = 'texte=<%= texte %>&categorie=<%= categorie %>&lieu=<%= lieu %>&prix=<%= prix %>',
                        function (donnees) {
                            $.each(
                                donnees,
                                function (cle, val) {
                                    var contenu  = "<a class='card col-sm-6 col-md-4 col-lg-3 col-xl-2 border-0 text-dark' href='page-produit.jsp?idArticle=" + val.identifiant + "'>";
                                        contenu += "<img class='card-img-top rounded-circle' src='" + val.img + "' alt='Image de " + val.nom + "'>";
                                        contenu += "<div class='card-body border-top mt-3 text-center'>";
                                        contenu += "<h5 class='card-title'>" + val.nom + "</h5>";
                                        contenu += "<p class='prix'>" + val.prix + "€</p>";
                                        contenu += "<p class='card-text elipse' title='" + val.courteDescription + "'>" + val.courteDescription + "</p></div></a>";

                                    $("#resultat").append(contenu);
                                });
                        })
                        .fail(function () {
                            alert("Une erreur s'est produite");
                        });
                })
            </script>
        </div>
    </div>

    <s:include value="include/footer.jsp" />

</body>

</html>
