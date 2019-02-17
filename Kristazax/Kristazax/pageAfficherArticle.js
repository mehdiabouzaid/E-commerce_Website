$(document).ready(() => {
    afficherArticles();
});
function afficherArticles() {
    console.log(idArticle);
    if (idArticle != null) {
        idArticle = parseInt(idArticle);

        // URL de l'action AJAX

        // Action AJAX en POST
        $.ajax({
            type: "POST",
            url,
            data: "idArticle=" + idArticle,
            success: function (data) {
                $('#imageID').empty();
                $('#imageID').append("<img class=\"rounded-circle card-img-top\" src=\"" + data.img + "\" alt=\"Image du produit\">");
                $('#descriptionDebut').empty();
                var contenu = "<p>" + data.nom + " -<span class=\"prix\">" + data.prix.toFixed(2) + "</span></p>";
                contenu += "<p class=\"text-muted elipse mb-0\">" + data.courteDescription + "</p>";
                $('#descriptionDebut').append(contenu);
                $('#ajoutPanier').empty();
                $('#ajoutPanier').append("<button id=\"ajout" + data.identifiant + "\" class=\"btn btn-primary ajouter\" type=\"button\">Ajouter au panier</a>");
                $('#descriptionFin').empty();
                $('#descriptionFin').append("<p class=\"text-secondary mt-3\">" + data.longueDescription + "</p>");
                $('#afficherVendeur').empty();
                contenu = "<a class=\"col-12\" href=\"informationsCompte.jsp?idVendeur="+ data.idVendeur +"\">";
                contenu += "<p class=\"text-center\"> Accéder au profil du vendeur</p> </a>";
                $('#afficherVendeur').append(contenu);
                $('.ajouter').click(function(){
                    var temp = this.id.split("ajout")[1];
                    url = url2;
                    data = "idArticle=" + temp;
                    $.ajax({
                        type : "POST",
                        url,
                        data,
                        success : function(data){
                            if(data== "Article ajouté"){
                                toastr.success(data, 'Info Panier');
                            }else{
                                toastr.error(data, 'Info Panier');
                            }
                        }
                    });
                });
            }
        });
    }
}

