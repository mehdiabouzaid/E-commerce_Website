<!-- Auteur : Thomas Di Gregorio, Flavien Coçu -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <s:include value="include/scripts.jsp" />
    <s:include value="include/header.jsp" />
</head>

<body>

    <s:include value="include/navbar.jsp" />

    <%
        if (request.getParameter("mdpchanger") != null ){
    %>
        <script>
            toastr.success('<% out.print(request.getParameter("mdpchanger")); %>', 'Mot de passe');
        </script>
    <%
        }
    %>

    <%
        if (request.getParameter("connexionReussie") != null ){
    %>
        <script>
            toastr.success('<% out.print(request.getParameter("connexionReussie")); %>', 'Connecté');
        </script>
    <%
        }
    %>

    <%
        if (request.getParameter("deconnexionReussie") != null ){
    %>
        <script>
            toastr.success('<% out.print(request.getParameter("deconnexionReussie")); %>', 'Déconnecté');
        </script>
    <%
        }
    %>

    <%
        if (request.getParameter("achat") != null ){
    %>
        <script>
            toastr.success('<% out.print(request.getParameter("achat")); %>', 'Commande validée');
        </script>
    <%
        }
    %>

	<div class="container bg-white p-5 rounded">
        <div class="row">
            <a href="recherche.jsp?categorie=vehicules" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Vehicules.png" alt="Image de véhicules" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Véhicules</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=immobilier" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Immobilier.png" alt="Image d'immobilier" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Immobilier</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=multimedia" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Multimedia.png" alt="Image de multimédia" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Multimédia</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=materielprofessionnel" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/MaterielPro.png" alt="Image de matériel professionnel" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Matériel professionnel</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=loisirs" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Loisirs.png" alt="Image de loisirs" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Loisirs</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=services" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Services.png" alt="Image de services" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Services</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=mode" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Mode.png" alt="Image de mode" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Mode</h5>
                </div>
            </a>

            <a href="recherche.jsp?categorie=maison" class="card col-lg-2 border-0">
                <img class="card-img-top rounded-circle" src="image/Maison.png" alt="Image de maison" style="height:100px">
                <div class="card-body border-top mt-3 text-center">
                    <h5 class="card-title text-dark">Maison</h5>
                </div>
            </a>
        </div>
    </div>

    <div class="container bg-white p-5 rounded">
        <h5 class="mb-5">Recommandés pour vous</h5>
        <div class="row" id="recommandes">
            <script type="text/javascript">
                $(function() {
                    $.post(
                        "<s:url action='ajax_articlesRecommandes' />",
                        function (donnees) {
                            $.each(
                                donnees,
                                function (cle, val) {
                                    var contenu  = "<a class='card col-sm-6 col-md-4 col-lg-3 col-xl-2 border-0 text-dark' href='page-produit.jsp?idArticle=" + val.identifiant + "'>";
                                        contenu += "<img class='card-img-top rounded-circle' src='" + val.img + "' alt='Image de " + val.nom + "' style='height:100px'>";
                                        contenu += "<div class='card-body border-top mt-3 text-center'>";
                                        contenu += "<h5 class='card-title'>" + val.nom + "</h5>";
                                        contenu += "<p class='prix'>" + val.prix + "€</p>";
                                        contenu += "<p class='card-text elipse' title='" + val.courteDescription + "'>" + val.courteDescription + "</p></div></a>";

                                    $("#recommandes").append(contenu);
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
