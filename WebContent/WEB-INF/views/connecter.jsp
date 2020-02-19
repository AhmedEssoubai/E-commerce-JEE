<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Se connecter</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-login.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.connecter" var="connecter_text"/>

    <section id="login">
        <div class="container">
            <div class="row">
                <div id="forme" class="col-sm-6 p-5 my-3">
                    <form method="POST" action="Connecter" class="pt-5">
                        <h2 class="mb-5 pt-5 pb-3">
                            <fmt:message key="connecter" bundle="${connecter_text }"/>
                        </h2>
                        <div class="form-groupe my-4">
                            <label class="control-label" for="email"><fmt:message key="email" bundle="${connecter_text }"/>:</label>
                            <input id="email" type="email" name="email" value="${param.email }" class="form-control" placeholder='<fmt:message key="enter" bundle="${connecter_text }"/> <fmt:message key="email" bundle="${connecter_text }"/>' required>
                        </div>
                        <div class="form-groupe my-4">
                            <label class="control-label" for="mot_passe"><fmt:message key="mot_passe" bundle="${connecter_text }"/>:</label>
                            <input id="mot_passe" name="mot_passe" type="password" class="form-control" placeholder='<fmt:message key="enter" bundle="${connecter_text }"/> <fmt:message key="mot_passe" bundle="${connecter_text }"/>' required>
                        </div>
                        <c:if test='${requestScope.err != null}'>
                        	<span class="lead errorMessage"><fmt:message key="error" bundle="${connecter_text }"/></span>
                        </c:if>
                        <div class="form-groupe mt-5">
                            <button type="submit" class="btn btn-primary btn-lg form-control"><fmt:message key="connecter" bundle="${connecter_text }"/></button>
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
                        <fmt:message key="message" bundle="${connecter_text }"/> <a href="Inscrire" class="_link"><fmt:message key="inscrire" bundle="${connecter_text }"/></a>
                    </h5>
                </div>
            </div>
        </div>
    </section>

    
	<c:set var="url">Connecter</c:set>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>