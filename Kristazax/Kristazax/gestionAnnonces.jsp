<!-- Auteurs : Pierre Bernard -->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
      <s:include value="include/scripts.jsp" />
      <s:include value="include/header.jsp" />
  </head>
  <body>
    <s:include value="include/navbar.jsp" />
    <s:if test="#session.typeCompte == 'Normal' || #session.utilisateur == null">

      <jsp:forward page="index.jsp" />

    </s:if>
    
    <div class="row">
      <div class="col-lg-8 offset-lg-2">
        <div class="container bg-white p-5 rounded">

          <div class="form-group row">
            <label for="ListeAnnonces" class="col-sm-2 col-form-label">Annonces a valider :</label>
            <div class="col-sm-9">
                <select  id="ListeAnnonces" class="form-control"></select>
          </div>

          <div class='mt-2'>
            <input type="button" id ="validerAnnonce" onclick="validationAnnonce()" class="btn btn-primary" value="Valider l'annonce">
            <input type="button" id ="supprimerAnnonce" onclick="supprimerAnnonce()" class="btn btn-danger pl-2" value="Supprimer l'annonce">
          </div>

        </div>
      </div>
    </div>
    <script type="text/javascript">
    $(function() {
        miseAJour();
    });

    function miseAJour() {
        var url = "<s:url action="ajax_obtenirAnnonces"/>";
         $.post(
           url,
           function (data) {
              var $listeAnnonces = jQuery("#ListeAnnonces");
    
              $listeAnnonces.empty();
              jQuery.each(data, function (key, val) {
                $listeAnnonces.append('<option value="'+val.identifiant+'">'+val.nom+' - '+ val.courteDescription + ' - ' + val.longueDescription + '</option>');
                });
            })
            .fail(function () {
              alert("Une erreur s'est produite.");
            });
    }
    function validationAnnonce() {
        var url = "<s:url action="ajax_validerAnnonce"/>";
        $.post(
        url,
        data = "annonceValidee=" + $("#ListeAnnonces").val(),
        function(data){
          toastr.success('Annonce validé', 'Gestion Annonce');
          miseAJour();
        })
        .fail(function () {
          alert("Une erreur s'est produite.");
        });
    }
    function supprimerAnnonce() {
        var url = "<s:url action="ajax_supprimerArticle"/>";
        $.post(
        url,
        data = "annonceValidee=" + $("#ListeAnnonces").val(),
        function(data){
          toastr.error('Annonce supprimé', 'Gestion Annonce');
          miseAJour();
        })
        .fail(function () {
          alert("Une erreur s'est produite.");
        });
    }
     
    </script>

      <s:include value="include/footer.jsp" />
      
  </body>
</html>

