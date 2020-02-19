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

    <section id="articles" class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 mx-auto">
                	<c:choose>
                		<c:when test="${requestScope.article == null }">
		                    <p class="lead py-2">
	                            Article not found
		                    </p>
                		</c:when>
                		<c:otherwise>
		                    <div class="card">
		                        <div class="card-header">
		                            <a href="Articles?categorie=${requestScope.article.categorie.id }" class="card-link">${requestScope.article.categorie.label }</a>
		                            <a href="Articles?fournisseur=${requestScope.article.fournisseur.id }" class="card-link">${requestScope.article.fournisseur.nom } ${article.fournisseur.prenom }</a>
		                        </div>
		                        <img class="img-fluid" src="${article.photo }">
		                        <div class="card-body">
		                            <h4 class="card-title py-2">${requestScope.article.titre }</h4>
		                            <h5 class="card-subtitle py-2">${requestScope.article.prix }$</h5>
		                            <h6 class="card-subtitle py-3">${requestScope.article.stock } en stock</h6>
		                            <p class="card-text pt-2">
		                                ${requestScope.article.description }
		                            </p>
                               		<c:set var="type">${sessionScope.utilisateur.type }</c:set>
		                            <c:choose>
		                            	<c:when test="${type == 'c' }">
		                            		<form class="form-inline" action="AjouterAPanier" method="GET">
				                               	<input type="number" name="id" value="${requestScope.article.id }" class="hiddedElement" readonly/>
				                               	<input type="number" name="quantite" value="1" style="max-width: 100px" class="form-control"/>
				                               	<button type="submit" class="btn btn-primary mx-2">Add to list</button>
				                           	</form>
		                            	</c:when>
		                            	<c:otherwise>
	                               			<a href="SupprimerArticle?id=${article.id }" href="#" class="btn btn-danger">Supprimer</a>
		                            	</c:otherwise>
		                            </c:choose>
		                           	<%--<form class="form-inline" action="AjouterAPanier" method="GET">
		                               	<input type="number" name="id" value="${requestScope.article.id }" class="hiddedElement" readonly/>
		                               	<input type="number" name="quantite" value="1" style="max-width: 100px" class="form-control"/>
		                               	<button type="submit" class="btn btn-primary mx-2">Add to list</button>
		                           	</form> --%>
		                        </div>
		                    </div>
                		</c:otherwise>
                	</c:choose>
                </div>
            </div>
        </div>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>