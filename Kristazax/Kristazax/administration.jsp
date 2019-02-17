<!-- Auteurs : Pierre Bernard -->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
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
            <label for="selectionUtilisateurs" class="col-sm-3 col-form-label">Utilisateur a promouvoir</label>
            <div class="col-sm-9">
                <input id="selectionUtilisateurs" class="form-control" type="text" onkeyup="editerSelect()">
            </div>
          </div>

          <div class="form-group row">
            <label for="ListeComptes" class="col-sm-3 col-form-label">Utilisateurs :</label>
            <div class="col-sm-9">
                <select  id="ListeComptes" class="form-control"></select>
            </div>

            <input type="button" id ="validerAdmin" onclick="validationAdmin()" class="btn btn-primary" value="Passer admin">

        </div>
      </div>
    </div>


      <%-- <br/> Utilisateurs :<br/>
      <select  id="ListeComptes"></select>
      <input id ="validerAdmin" onclick="validationAdmin()" class="btn btn-primary" value="Passer admin"> --%>
    </div>
    
    
    <script type="text/javascript">
        // URL de l'action AJAX
        function editerSelect(){
          var url = "<s:url action="ajax_obtenirListeComptes"/>";
         // Action AJAX en POST
        $.post(
           url,
           data = "debutUtilisateur=" + $("#selectionUtilisateurs").val(),
           function (data) {
              var $listeComptes = jQuery("#ListeComptes");
    
              $listeComptes.empty();
              jQuery.each(data, function (key, val) {
                $listeComptes.append('<option value="'+val.identifiant+'">'+val.identifiant+'</option>');
                });
            })
            .fail(function () {
              alert("Une erreur s'est produite.");
            });
        }

        function validationAdmin(){
          var url = "<s:url action="ajax_passerAdmin"/>";
           $.post(
           url,
           data = "utilisateurPromotion=" + $("#ListeComptes").val(),
            function(data){
              $("#ListeComptes").empty();
              $("#selectionUtilisateurs").value = "";
            })
            .fail(function () {
              alert("Une erreur s'est produite.");
            });
        }
        
    </script>

      <s:include value="include/footer.jsp" />
      <s:include value="include/scripts.jsp" />
  </body>
</html>

