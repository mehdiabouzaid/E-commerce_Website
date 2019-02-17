<!-- Auteurs : Pierre Bernard & Quentin Enjalbert-->

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
      <s:include value="include/header.jsp" />

  </head>
  <body>
    <s:include value="include/navbar.jsp" />
    <s:if test="#session.utilisateur == null">

      <jsp:forward page="pageConnexion.jsp" />

    </s:if>

    
    <div class="row">
        <div class="col-lg-8 offset-lg-2">
            <div class="container bg-white p-5 rounded">

                <div class="form-group row">
                    <label for="selectionUtilisateurs" class="col-sm-3 col-form-label">Communiquer avec : </label>
                    <div class="col-sm-9">
                        <input id="selectionUtilisateurs" class="form-control" type="text" onkeyup="editerSelect()">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="ListeComptes" class="col-sm-3 col-form-label">Utilisateurs :</label>
                    <div class="col-sm-9">
                        <select  id="ListeComptes" class="form-control" onchange="afficherMessages()"></select>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-8 offset-lg-2">
            <div class="container bg-white p-5 rounded" style="overflow:auto;overflow-x: hidden; width:600px; height:400px;" id="printMessages">

            </div>
        </div>

        <div class="col-lg-8 offset-lg-2">
            <div class="container bg-white p-5 rounded">
                <div class="input-group mb-3">
                <input type="text" id="messageAEnvoyer" class="form-control" placeholder="Message à envoyer" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-primary" onclick="envoyerMessage()" type="button" id="envoyerMessage">Envoyer</button>
                </div>
            </div>
        </div>
    </div>
    
    <s:include value="include/scripts.jsp" />
    <script type="text/javascript">
        // URL de l'action AJAX
        function editerSelect(){
          var url = "<s:url action="ajax_obtenirUtilisateurs"/>";
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
            setTimeout(afficherMessages, 200);
        }

        function afficherMessages(){
            var utilisateurSession = <% out.println("'"+session.getAttribute("utilisateur")+"'"); %>;
            
            var url = "<s:url action="ajax_obtenirMessages"/>";
            $.post(
           url,
           data = "utilisateur1=" + $("#ListeComptes").val(),
           function (data) {
                $("#printMessages").empty();
                jQuery.each(data, function (key, val) {
                    var contenu = "";
                    var dt = new Date(val.dateEnvoi);
                    if(utilisateurSession == val.expediteur) {
                        contenu += '<div class="container2 darker">'
                        contenu += '<img src="image/user1.jpg" alt="Avatar" class="right" style="width:100%;">'
                        contenu += '<p>' + val.contenu +'</p>'
                        contenu += '<span class="time-left">'+dt.getHours()+':'+dt.getMinutes()+':'+dt.getSeconds()+'</span>'
                        contenu += '</div>'
                    }
                    else{
                        contenu += '<div class="container2">'
                        contenu += '<img src="image/user2.jpg" alt="Avatar" style="width:100%;">'
                        contenu += '<p>' + val.contenu +'</p>'
                        contenu += '<span class="time-right">'+dt.getHours()+':'+dt.getMinutes()+':'+dt.getSeconds()+'</span>'
                        contenu += '</div>'
                    }
                    $("#printMessages").append(contenu);
                });
                $("#printMessages")[0].scrollTop = ($("#printMessages")[0].scrollHeight - $("#printMessages")[0].clientHeight);
            })
            .fail(function () {
              alert("Une erreur s'est produite.");
            });
        }

        function envoyerMessage() {
            var utilisateurAEnvoyer = $("#ListeComptes").val();
            var messageAEnvoyer = $("#messageAEnvoyer").val()
             var url = "<s:url action="ajax_envoyerMessage"/>";
             $.post(
           url,
           data = {
               utilisateurAEnvoyer,
               messageAEnvoyer,
           },
           function (data) {
               $("#messageAEnvoyer").empty();
            })
            .fail(function () {
              alert("Une erreur s'est produite.");
            });
            setTimeout(afficherMessages, 200);
            $("#messageAEnvoyer").val("");
            
        } 
        
    </script>

      <s:include value="include/footer.jsp" />
      
  </body>
</html>

