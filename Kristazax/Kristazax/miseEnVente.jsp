<!-- Auteur :  -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <s:form action="miseenvente" class="bg-white rounded" enctype="multipart/form-data">
                        <div class="form-group row">
                            <label for="nom" class="col-sm-3 col-form-label">Produit</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.nom'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:textfield cssClass="form-control" id="nom" name="articleBean.nom" placeholder="iPhone 36" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="titre" class="col-sm-3 col-form-label">Titre</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.courteDescription'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:textfield cssClass="form-control" id="titre" name="articleBean.courteDescription"
                                    placeholder="Noir 64 Go" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="description" class="col-sm-3 col-form-label">Description</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.longueDescription'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:textfield cssClass="form-control" id="description" name="articleBean.longueDescription"
                                    placeholder="Vendu avec tous les accessoires d'origine" rows="3" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="categorie" class="col-sm-3 col-form-label">Catégorie</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.categorie'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:select id="categorie" cssClass="custom-select" headerKey="-1" headerValue="Selectionner la catégorie"
                                    list="#{'VEHICULES':'Vehicules', 'IMMOBILIER':'Immobilier', 'MULTIMEDIA':'Multimédia', 'MODE':'Mode', 'MAISON':'Maison', 'LOISIRS':'Loisirs', 'MATERIELPROFESSIONNEL':'Materiel Pro', 'SERVICES':'Services'}"
                                    name="articleBean.categorie" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="prix" class="col-sm-3 col-form-label">Prix (€)</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.prix'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:textfield type="number" cssClass="form-control" id="prix" name="articleBean.prix"
                                    placeholder="10000" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="img" class="col-sm-3 col-form-label">Photo</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.img'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:file cssClass="form-control" id="img" name="upload" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="lieu" class="col-sm-3 col-form-label">Lieu</label>
                            <div class="col-sm-9">
                                <s:iterator value="fieldErrors">
                                    <s:if test="key == 'articleBean.lieu'">
                                        <s:iterator value="value">
                                            <div class="alert alert-danger">
                                                <s:property />
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </s:iterator>
                                <s:select id="lieu" cssClass="custom-select" headerKey="-1" headerValue="Selectionner le lieu"
                                    list="#{'CENTRE':'Centre', 'NORD':'Nord', 'SUD':'Sud', 'EST':'Est', 'OUEST':'Ouest'}"
                                    name="articleBean.lieu" />
                            </div>
                        </div>

                        <s:submit cssClass="btn btn-primary" value="Mettre en ligne" />
                    </s:form>
                </div>
            </div>
        </div>

    </s:else>

    <s:include value="include/footer.jsp" />
    <s:include value="include/scripts.jsp" />

</body>

</html>