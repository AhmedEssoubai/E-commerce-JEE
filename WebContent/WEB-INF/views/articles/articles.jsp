<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Articles</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-articles-gestion.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.articles" var="articles_text"/>

    <section id="articles" class="py-5  bg-faded">
        <div class="container">
            <div class="row mb-3">
                <form name="frm_categories" class="form-inline mr-3" action="Articles" method="get">
                    <div class="form-group">
                        <label class="mr-sm-3" for="categories"><fmt:message key="genre" bundle="${articles_text }"/> </label>
                       	<select id="categories" name="categorie" class="form-control" onchange='submit("frm_categories")'>
                       		<option value="0"><fmt:message key="tout" bundle="${articles_text }"/></option>
                      		<c:forEach var="categorie" items="${requestScope.categories }">
                           		<option value="${categorie.id }" ${requestScope.categorie != null && requestScope.categorie == categorie.id ? "selected" : ""}>${categorie.label }</option>
                       		</c:forEach>
                       	</select>
                    </div>
                </form>
                
		        <%--<c:if test="${session.utilisateur.type != 'f' }">
	                <form name="frm_fournisseur" class="form-inline" action="Articles" method="get">
	                    <div class="form-group">
	                        <label class="mr-sm-3" for="fournisseur">Choisissez le fournisseur: </label>
	                       	<select id="categories" name="fournisseur" class="form-control" onchange='submit("frm_fournisseur")'>
	                       		<option value="0">Tout</option>
	                      		<c:forEach var="fournisseur" items="${requestScope.fournisseurs }">
	                           		<option value="${fournisseur.id }" ${requestScope.fournisseur != null && requestScope.fournisseur == fournisseur.id ? "selected" : ""}>${fournisseur.prenom } ${fournisseur.nom }</option>
	                       		</c:forEach>
	                       	</select>
	                    </div>
                	</form>
                </c:if> --%>
            </div>
            <div class="row mb-3">
                <div class="col-12 line">
                </div>
            </div>
            
            <div class="card-deck">
            	<c:if test="${fn:length(requestScope.articles) == 0 }">
            		<div class="row text-center">
	                    <p class="lead py-2">
	                            <fmt:message key="vide" bundle="${articles_text }"/>
	                    </p>
	                </div>
            	</c:if>
            	<c:forEach var="article" items="${requestScope.articles }">
            		<div class="row my-3">
	                    <div class="card flex-row flex-wrap">
	                        <div class="col-md-4">
	                            <a href="Articles?id=${article.id }"><img class="img-fluid" src="${article.photo }"></a>
	                        </div>
	                        <div class="col-md-8">
	                            <div class="card-body">
	                                <h4 class="pb-1 card-title">
                                		<a class="card-link" href="Articles?id=${article.id }">${article.titre }</a>
                                		<c:set var="type">${sessionScope.utilisateur.type }</c:set>
		                            	<c:if test="${type != 'c' }">
                               				<small><a class="card-link text-danger" href="SupprimerArticle?id=${article.id }"><fmt:message key="supprimer" bundle="${articles_text }"/></a></small>
                             			</c:if>
	                                </h4>
	                                <h6 class="card-subtitle pt-1 pb-2">
	                                	<a href="Articles?categorie=${article.categorie.id }" class="card-link">${article.categorie.label }</a>
	                                	<a href="Articles?fournisseur=${article.fournisseur.id }" class="card-link">${article.fournisseur.nom } ${article.fournisseur.prenom }</a>
	                                </h6>
	                                <h5 class="card-subtitle py-2 pt-1">${article.prix }$</h5>
	                                <h6 class="card-subtitle pb-2 pt-1">${article.stock } <fmt:message key="en_stock" bundle="${articles_text }"/></h6>
	                                <p class="card-text pt-1">
	                                        ${article.description }
	                                </p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
            	</c:forEach>
            </div>
        </div>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>