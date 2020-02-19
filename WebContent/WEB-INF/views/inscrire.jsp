<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>S'inscrire</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-login.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.inscrire" var="inscrire_text"/>

    <section id="login">
        <div class="container">
            <div class="row">
                <div id="forme" class="col-md-6 p-5 my-3">
                    <form method="POST" action="Inscrire">
                        <div class="row">
                            <h2 class="col-md-4 pb-3">
                                <fmt:message key="inscrire" bundle="${inscrire_text }"/>
                            </h2>
                            <div class="col-md-8 form-groupe my-3">
                                <h5>
                                	<c:choose>
                                		<c:when test='${param.utilisateur == "f" }'>
											<c:set var="c_checked" scope="page" value=""/>
											<c:set var="f_checked" scope="page" value="checked"/>
                                		</c:when>
                                		<c:otherwise>
											<c:set var="c_checked" scope="page" value="checked"/>
											<c:set var="f_checked" scope="page" value=""/>
                                		</c:otherwise>
                                    </c:choose>
                                    <label id="lb_client" class="control-label radio-label" for="client"><fmt:message key="client" bundle="${inscrire_text }"/></label>
                                    <input id="client" name="utilisateur" type="radio" class="hiddedElement" value="c" onchange="utilisateurChanger(this.id)" ${c_checked }>
                                    <span class="lead">/</span>
                                    <label id="lb_fournisseur" class="control-label radio-label" for="fournisseur"><fmt:message key="fournisseur" bundle="${inscrire_text }"/></label>
                                    <input id="fournisseur" name="utilisateur" type="radio" class="hiddedElement" value="f" onchange="utilisateurChanger(this.id);" ${f_checked }>
                                </h5>
                            </div>
                        </div>
                        <div class="line"></div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="nom"><fmt:message key="nom" bundle="${inscrire_text }"/>:</label>
                            <input id="nom" name="nom" value="${param.nom }" type="text" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="nom" bundle="${inscrire_text }"/>' required>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="prenom"><fmt:message key="prenom" bundle="${inscrire_text }"/>:</label>
                            <input id="prenom" name="prenom" value="${param.prenom }" type="text" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="prenom" bundle="${inscrire_text }"/>' required>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="adresse"><fmt:message key="adresse" bundle="${inscrire_text }"/>:</label>
                            <input id="adresse" name="adresse" value="${param.adresse }" type="text" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="adresse" bundle="${inscrire_text }"/>'>
                        </div>
                        <div class="form-groupe my-3">
                            <div class="row">
                                <div class="col-4">
                                    <label class="control-label" for="code_postale"><fmt:message key="code_postale" bundle="${inscrire_text }"/>:</label>
                                    <input id="code_postale" name="code_postale" value="${param.code_postale }" type="number" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="code_postale" bundle="${inscrire_text }"/>'>
                                </div>
                                <div class="col-8">
                                    <label class="control-label" for="ville"><fmt:message key="ville" bundle="${inscrire_text }"/>:</label>
                                    <input id="ville" name="ville" type="text" value="${param.nom }" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="ville" bundle="${inscrire_text }"/>'>
                                </div>
                            </div>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="tel"><fmt:message key="telephone" bundle="${inscrire_text }"/>:</label>
                            <input id="tel" name="tel" type="tel" value="${param.tel }" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="telephone" bundle="${inscrire_text }"/>'>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="email"><fmt:message key="email" bundle="${inscrire_text }"/></label>
                            <div class="row">
                                <div class="col-8 mx-0">
                                    <input id="email" name="email" value="${param.email }" type="text" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="email" bundle="${inscrire_text }"/>' required>
                                </div>
                                <div class="col-4 mx-0">
                                    <input id="email_part" type="text" class="form-control" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="mot_passe"><fmt:message key="mot_passe" bundle="${inscrire_text }"/>:</label>
                            <input id="mot_passe" name="mot_passe" type="password" class="form-control" placeholder='<fmt:message key="enter" bundle="${inscrire_text }"/> <fmt:message key="mot_passe" bundle="${inscrire_text }"/>' required>
                        </div>
                        <c:if test='${requestScope.err != null}'>
                        	<span class="lead errorMessage"><fmt:message key="error" bundle="${inscrire_text }"/></span>
                        </c:if>
                        <div class="form-groupe mt-5">
                            <button type="submit" class="btn btn-primary btn-lg form-control"><fmt:message key="inscrire" bundle="${inscrire_text }"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <section class="py-5">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h5 class="text-secondary">
                        <fmt:message key="message" bundle="${inscrire_text }"/> <a href="Connecter" class="_link"><fmt:message key="connecter" bundle="${inscrire_text }"/></a>
                    </h5>
                </div>
            </div>
        </div>
    </section>
    
    <script type="text/javascript" src="js/signin.js"></script>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>