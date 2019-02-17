<!-- Auteur : Enjalbert Quentin -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<nav class="navbar navbar-expand-xl navbar-light bg-white flex-xl-column align-items-start">
    <a class="navbar-brand" href="index.jsp">Kristazax</a>


    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-expanded="false">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse w-100 border-top pt-4 pb-3" id="navigation">
        <div class="col-xl-6 pl-xl-0">
            <form class="input-group mb-xl-0" action="recherche.jsp" method="GET">
                <div class="input-group-prepend keep-open">
                <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown"></button>
                <div class="dropdown-menu">
                    <div class="px-4 py-3">
                        <div class="form-group">
                            <label for="recherche-prix">Prix maximal</label>
                            <input type="text" class="form-control" id="recherche-prix" name="prix">
                        </div>
                        <div class="form-group">
                            <label for="recherche-categorie">Catégorie</label>
                            <select id="recherche-categorie" class="custom-select" name="categorie">
                                <option value="" selected>Veuillez choisir une catégorie...</option>
                                <option value="vehicules">Véhicules</option>
                                <option value="immobilier">Immobilier</option>
                                <option value="multimedia">Multimédia</option>
                                <option value="materielprofessionnel">Matériel professionnel</option>
                                <option value="loisirs">Loisirs</option>
                                <option value="services">Services</option>
                                <option value="mode">Mode</option>
                                <option value="maison">Maison</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="recherche-lieu">Lieu</label>
                            <select id="recherche-lieu" class="custom-select" name="lieu">
                                <option value="" selected>Veuillez choisir un Lieu...</option>
                                <option value="nord">Nord</option>
                                <option value="sud">Sud</option>
                                <option value="est">Est</option>
                                <option value="ouest">Ouest</option>
                                <option value="centre">Centre</option>
                            </select>
                        </div>
                    </div>
                </div>
                </div>
                <input type="text" name="texte" placeholder="Que cherchez-vous ?">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-outline-success">Rechercher</button>
                </div>
            </form>
        </div>

        <div class="col-xl-6 d-flex justify-content-xl-end">        
            <ul class="navbar-nav">

                <a class="nav-item nav-link mr-5" href="miseEnVente.jsp">Vendre</a>

                <s:if test="#session.utilisateur == null">

                    <a class="nav-item nav-link mr-5" href="pageConnexion.jsp">Se Connecter</a>
                    <a class="nav-item nav-link mr-5" href="creationCompte.jsp">S'inscrire</a>
                
                </s:if>

                <s:else>

                    <a class="nav-item nav-link mr-5" href="informationsCompte.jsp">Votre Compte</a>
                    <a class="nav-item nav-link mr-5" href="messagerie.jsp">Messagerie</a>
                    <a class="nav-item nav-link mr-5" href="panier.jsp">Panier</a>
                    
                </s:else>

            </ul>
        </div>
    </div>
</nav>