<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-accueil.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.accueil" var="accueil_text"/>
	
    <section id="welcome" class="py-5">
        <div class="primary-overlay text-white pt-md-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h1 class="display-2 mt-5 pt-5">
                            <fmt:message key="title" bundle="${accueil_text }"/>
                        </h1>
                        <p class="lead">
                            <fmt:message key="subtitle" bundle="${accueil_text }"/>
                        </p>
                        <div class="mt-5">
                            <a href="Inscrire" class="btn btn-primary btn-lg text-white">
                            	<fmt:message key="signup" bundle="${accueil_text }"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="py-5 text-center bg-faded">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="mb-5">
                        <h1 class="text-primary pb-3">
                            <fmt:message key="project" bundle="${accueil_text }"/>
                        </h1>
                        <p class="lead pb-3">
                            <fmt:message key="desc" bundle="${accueil_text }"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<c:set var="url">Panier</c:set>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>