<!-- Auteurs : -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!doctype html>
<html lang="fr">

<head>
      <s:include value="include/header.jsp" />
</head>


<body>

      <s:include value="include/navbar.jsp" />

      <s:if test="#session.utilisateur != null">

		<jsp:forward page="index.jsp" />

	</s:if>

	<s:else>

            <div class="container bg-white p-5 rounded">
                  <div class="row">
                        <div class="col-lg-7 offset-lg-2">
                              <s:form cssClass="bg-white rounded" id="creationcompte" action="creationcompte">
                                    <div class="form-group row">
                                          <label for="nom" class="col-sm-3 col-form-label">Nom</label>
                                          <div class="col-sm-9">
                                                <s:iterator value="fieldErrors">
                                                      <s:if test="key == 'compteBean.identifiant'">
                                                            <s:iterator value="value">
                                                            <div class="alert alert-danger">
                                                                  <s:property />
                                                            </div>
                                                            </s:iterator>
                                                      </s:if>
                                                </s:iterator>
                                                <s:textfield name="compteBean.identifiant" cssClass="form-control" id="nom" placeholder="Paul Kristazax"/>
                                          </div>
                                    </div>
                                    <div class="form-group row">
                                          <label for="email" class="col-sm-3 col-form-label">Email</label>
                                          <div class="col-sm-9">
                                                <s:iterator value="fieldErrors">
                                                      <s:if test="key == 'compteBean.email'">
                                                            <s:iterator value="value">
                                                            <div class="alert alert-danger">
                                                                  <s:property />
                                                            </div>
                                                            </s:iterator>
                                                      </s:if>
                                                </s:iterator>
                                                <s:textfield name="compteBean.email" cssClass="form-control" id="email" placeholder="bernard@exemple.fr"/>
                                          </div>
                                    </div>
                                    <div class="form-group row">
                                          <label for="motdepasse" class="col-sm-3 col-form-label">Mot de passe</label>
                                          <div class="col-sm-9">
                                                <s:iterator value="fieldErrors">
                                                      <s:if test="key == 'compteBean.motDePasse'">
                                                            <s:iterator value="value">
                                                            <div class="alert alert-danger">
                                                                  <s:property />
                                                            </div>
                                                            </s:iterator>
                                                      </s:if>
                                                </s:iterator>
                                                <s:password name="compteBean.motDePasse" cssClass="form-control" id="motdepasse" placeholder="Mot de passe"/>
                                          </div>
                                    </div>

                                    <s:submit cssClass="btn btn-primary btn-block mt-4" value="Valider"/>
                              </s:form>
                        </div>
                  </div>
            </div>
	
	</s:else>

      <s:include value="include/footer.jsp" />
      <s:include value="include/scripts.jsp" />

</body>

</html>