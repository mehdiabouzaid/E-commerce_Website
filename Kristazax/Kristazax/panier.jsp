<!-- Auteur : Flavien Coçu -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="fr">

<head>
    <s:include value="include/header.jsp" />
</head>

<body>

    <s:include value="include/navbar.jsp" />

    <s:if test="#session.utilisateur == null">

		<jsp:forward page="pageConnexion.jsp" />

	</s:if>

	<s:else>

		<div class="container bg-white p-5 rounded">
            <div class="card p-3 rounded col-xl-4 offset-xl-4 text-center">
                <div class="card-body">
                    <h5 class="card-title border-bottom">Total</h5>
                    <p id="prixTotal" class="card-text prix"></p>
                    <div id="paypal-button-container"></div>
                </div>
            </div>

            <!-- Article -->

            <div id="listeArticlesPanier" class="row"></div>
        </div>
	
	</s:else>

    <s:include value="include/footer.jsp" />
    <s:include value="include/scripts.jsp" />

    <script type="text/javascript">
        var prix = 0;
        
        $(document).ready(() => {
            afficherArticles();
        });
        function afficherArticles(){
            // URL de l'action AJAX
            var url = "<s:url action="ajax_obtenirListeArticlesPourPanier"/>";

            // Action AJAX en POST
            
            $.post(
                url,
                function (data) {
                    var $listeArticlesPanier = $('#listeArticlesPanier');
                    $listeArticlesPanier.empty();
                    jQuery.each(data, function (key, val) {
                        var contenu = "";
                        contenu += "<div class=\"card my-3 p-3 col-xl-8 offset-xl-2\">";
                        contenu += "<div class=\"row\">";
                        contenu += "<a class=\"col-4 my-auto\" href=\"page-produit.jsp?idArticle="+ val.identifiant +"\"><img class=\"card-img-top rounded-circle\" src=\"" + val.img + "\" alt=\"Image de Catégorie\">";
                        contenu += "<div class=\"col-8 p-3 my-3\">";
                        contenu += "<h5 class=\"card-title\">" + val.nom + " -<span class=\"prix ml-1\">" + val.prix.toFixed(2) + " € </span></h5>";
                        contenu += "<p class=\"card-text text-muted elipse\">" + val.courteDescription + "</p></a>";
                        contenu += "<button id=\"suppr" + val.identifiant + "\" class=\"btn btn-danger supprimer\" type=\"button\">Supprimer</a></div></div></div>";
                        $listeArticlesPanier.append(contenu);
                        prix += val.prix;
                    });
                    $('#prixTotal').text(prix.toFixed(2) + " €");
                    $(".supprimer").click(function(){
                        console.log("supprimer");
                        var temp = this.id.split("suppr")[1];
                        console.log(this.id);
                        console.log(temp);
                        $.ajax({
                            type : "POST",
                            url : "<s:url action="ajax_suppressionArticle"/>",
                            data : "idArticle=" + temp,
                            success : function(data){
                                $('#listeArticlesPanier').empty();
                                afficherArticles();
                            }
                        });
                    });
                })
                .fail(function () {
                    alert("Une erreur s'est produite.");
                }
            );
        }

        // https://developer.paypal.com/docs/checkout/how-to/upgrade-integration/#4-configure-payment-callback
        paypal.Button.render({
            env: 'sandbox',
        
            style: {
                layout: 'vertical',
                size:   'medium',
                shape:  'rect',
                color:  'black'
            },
        
            funding: {
                allowed: [
                    paypal.FUNDING.CARD,
                ],
                disallowed: [
                    paypal.FUNDING.CREDIT,
                    paypal.FUNDING.ELV
                ]
            },
        
            commit: true,
        
            client: {
                sandbox: 'ASlk91T5Csx9Xkye64R19KLgsqZoq3oGhOEFLqRJL3TB5VUBj21TwtCJPIRHXCiiXi2Rl_eKbCcGV5Bc',
                production: '<insert production client id>'
            },
        
            payment: function (data, actions) {
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: {
                                    total: prix.toFixed(2),
                                    currency: 'EUR'
                                }
                            }
                        ]
                    }
                });
            },
        
            onAuthorize: function (data, actions) {
                return actions.payment.execute().then(function () {
                        var url = "<s:url action="ajax_supprimerPanier"/>";

                        $.post(url, 
                            function (data) {
                                window.location.replace("index.jsp?achat=Merci+pour+votre+achat+!");
                            }
                        ).fail(function () {
                            alert("Une erreur s'est produite.");
                        });
                    });
            }
        }, '#paypal-button-container');
    </script>

</body>

</html>