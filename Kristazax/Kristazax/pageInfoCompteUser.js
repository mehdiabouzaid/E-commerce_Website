$(document).ready(() => {
    if(idCompte == null){
        afficherCompte();
    }else{
        afficherCompteVendeur();
    }
});
function afficherCompte() {
    // URL de l'action AJAX
    var prix = 0;
    url = url3;

    // Action AJAX en POST
    $.post(
        url,
        function (data) {
            var $listInformation = $('#information');
            $listInformation.empty();
            var contenu = "<form>";
            contenu += "<div class=\"form-group row\">";
            contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Identifiant</label>";
            contenu += "<div class=\"col-sm-9\">";
            contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.identifiant + "\" readonly>";
            contenu += "</div></div>";

            contenu += "<div class=\"form-group row\">";
            contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Email</label>";
            contenu += "<div class=\"col-sm-9\">";
            contenu += "<input type=\"email\" class=\"form-control-plaintext\" placeholder=\"" + data.email + "\" readonly>";
            contenu += "</div></div>";

            contenu += "<div class=\"form-group row\">";
            contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Type de compte</label>";
            contenu += "<div class=\"col-sm-9\">";
            contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.role + "\" readonly>";
            contenu += "</div></div>";

            contenu += "<div class=\"form-group row\">";
            contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Nombre de note</label>";
            contenu += "<div class=\"col-sm-9\">";
            contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.nbNotes + "\" readonly>";
            contenu += "</div></div>";

            contenu += "<div class=\"form-group row\">";
            contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Moyenne des notes</label>";
            contenu += "<div class=\"col-sm-9\">";
            if (data.nbNotes == 0){
                contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"Aucune note enregistrée\" readonly>";
            }else{
                contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.noteMoyenne + "\" readonly>";
            }
            contenu += "</div></div>";
            contenu += "<a href='pageChangerMdp.jsp' class=\"btn btn-primary\">"+ "Changer de mot de passe</a>";
            contenu += "</form>"
            $listInformation.append(contenu);
        })
        .fail(function () {
            alert("Une erreur s'est produite.");
        }
    );
}

function afficherCompteVendeur(){
    // URL de l'action AJAX
    url = url2;
    var prix = 0;
    // Action AJAX en POST
    $.ajax({
        type : "POST",
        url,
        data : "idCompte=" + idCompte,
        success : function(data){
            if(nomDeCompte == data.identifiant){
                afficherCompte(); 
            }else{
                var $listInformation = $('#information');
                $listInformation.empty();
                var contenu = "<form>";
                contenu += "<div class=\"form-group row\">";
                contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Identifiant</label>";
                contenu += "<div class=\"col-sm-9\">";
                contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.identifiant + "\" readonly>";
                contenu += "</div></div>";
    
                contenu += "<div class=\"form-group row\">";
                contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Type de compte</label>";
                contenu += "<div class=\"col-sm-9\">";
                contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.role + "\" readonly>";
                contenu += "</div></div>";
    
                contenu += "<div class=\"form-group row\">";
                contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Nombre de note</label>";
                contenu += "<div class=\"col-sm-9\">";
                contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.nbNotes + "\" readonly>";
                contenu += "</div></div>";
    
                contenu += "<div class=\"form-group row\">";
                contenu += "<label for=\"nom\" class=\"col-sm-3 col-form-label\">Moyenne des notes</label>";
                contenu += "<div class=\"col-sm-9\">";
                if (data.nbNotes == 0){
                    contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"Aucune note enregistrée\" readonly>";
                }else{
                    contenu += "<input type=\"text\" class=\"form-control-plaintext\" placeholder=\"" + data.noteMoyenne + "\" readonly>";
                }
                contenu += "</div></div>";
                contenu += "</form>"
                $listInformation.append(contenu);
                $('#boutonAdmin').remove();
            }
        }
    });
}