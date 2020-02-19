<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mes commandes</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-articles-gestion.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    
    <section id="articles" class="py-5">
        <div class="container">
            <div class="row mb-3">
                <h5 class="lead mr-auto">Le prix total pour tous vos achats est : ${requestScope.prixTotal }</h5>
            </div>
            <div class="row mb-3">
                <div class="col-12 line">
                </div>
            </div>
           	<c:if test="${fn:length(requestScope.commandes) == 0 }">
           		<div class="row">
                   	<div class="col-12 py-2 text-center">
	                    <p class="lead">
	                           Vous n'avez aucun achat
	                    </p>
                    </div>
                </div>
           	</c:if>
        	<c:forEach var="commande" items="${requestScope.commandes }">
	            <div class="row my-3">
	                <div class="card">
	                    <div class="card-header">
	                        <fmt:formatDate value="${commande.date }"/>   ,   
	                        Le prix total : ${commande.prixTotal }
	                    </div>
	                    <div class="card-body">
	                        <div class="card-deck">
		                        <c:forEach var="ligneCommande" items="${commande.lignesCommande }">
					           		<div class="row my-3">
					                    <div class="card flex-row flex-wrap">
					                        <div class="col-md-4">
					                            <a href="Articles?id=${ligneCommande.article.id }"><img class="img-fluid" src="${ligneCommande.article.photo }"></a>
					                        </div>
					                        <div class="col-md-8">
					                            <div class="card-body">
					                                <h4 class="card-title pb-1"><a class="card-link" href="Articles?id=${ligneCommande.article.id }">${ligneCommande.article.titre }</a></h4>
					                                <h6 class="card-subtitle pt-1 pb-2">
					                                	<a href="Articles?categorie=${ligneCommande.article.categorie.id }" class="card-link">${ligneCommande.article.categorie.label }</a>
					                                	<a href="Articles?fournisseur=${ligneCommande.article.fournisseur.id }" class="card-link">${ligneCommande.article.fournisseur.nom } 
					                                									${ligneCommande.article.fournisseur.prenom }</a>
					                                </h6>
					                                <h5 class="card-subtitle py-2 pt-1">${ligneCommande.article.prix } $</h5>
					                                <h6 class="card-subtitle pb-2 pt-1">${ligneCommande.quantite } les pièces par ${ligneCommande.prix } $</h6>
					                                <p class="card-text pt-1">
					                                        ${ligneCommande.article.description }
					                                </p>
					                            </div>
					                        </div>
					                    </div>
					                </div>
					           	</c:forEach>
	                        </div>
	                    </div>
	                </div>
	            </div>
        	</c:forEach>
        </div>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>