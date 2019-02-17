<!-- Auteur : Enjalbert Quentin -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<footer class="footer p-3 text-white bg-dark">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 border-left">
                <h4 class="mb-4 font-weight-bold">Plan du site</h4>
                <p><a href="index.jsp" class="text-info">Accueil</a></p>
                <p><a href="miseEnVente.jsp" class="text-info">Vendre</a></p>
                
                <s:if test="#session.utilisateur == null">

                    <p><a href="pageConnexion.jsp" class="text-info">Se Connecter</a></p>
                    <p><a href="creationCompte.jsp" class="text-info">S'inscrire</a></p>
                
                </s:if>

                <s:else>

                    <p><a href="informationsCompte.jsp" class="text-info">Votre Compte</a></p>
                    <p><a href="panier.jsp" class="text-info">Panier</a></p>
                    <p><a href="deconnexion" class="text-danger">Déconnexion</a></p>
                    
                </s:else>
            </div>
            <div class="col-lg-3 border-left">
                <h4 class="mb-4 font-weight-bold">Auteurs</h4>
                <p><a href="mailto:mehdi.abouzaid@insa-rouen.fr" class="text-info">Mehdi Abouzaid</a></p>
                <p><a href="mailto:pierre.bernard@insa-rouen.fr" class="text-info">Pierre Bernard</a></p>
                <p><a href="mailto:flavien.cocu@insa-rouen.fr" class="text-info">Flavien Coçu</a></p>
                <p><a href="mailto:thomas.di_gregorio@insa-rouen.fr" class="text-info">Thomas Di Gregorio</a></p>
                <p><a href="mailto:quentin.enjalbert@insa-rouen.fr" class="text-info">Quentin Enjalbert</a></p>
            </div>
            <div class="col-lg-6 border-left">
                <h4 class="mb-4 font-weight-bold">Kristazax</h4>
                <p class="text-info">
                    Bonjour, bienvenue à toi minot. Nous sommes le groupe VENTE4. Ici tu pourras faire tous tes
                    achats de Noël, et plus encore ;)
                </p>
            </div>
        </div>
    </div>
</footer>