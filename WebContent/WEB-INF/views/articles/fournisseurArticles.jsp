<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon articles</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-nav.css"/>
    <link rel="stylesheet" href="css/style-articles-gestion.css"/>
    <link rel="stylesheet" href="css/style-footer.css"/>
</head>
<body>
    <nav class="navbar navbar-light navbar-toggleable-sm py-4">
        <div class="container">
            <a href="Accueil" class="navbar-brand mr-auto">
                <h3 class="d-inline align-middle">E-commerce</h3>
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="nav-link">Items</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">My list</a>
                </li>
            </ul>
        </div>
    </nav>

    <sql:setDataSource dataSource="jdbc/e_commerce"/>
    
    <c:set var="categorieParam">${param.categorie}</c:set>
    <section id="articles" class="py-5">
        <div class="container">
            <div class="">
            <div class="row mb-3">
                <form name="frm_categories" class="form-inline" action="Articles" method="get">
                    <div class="form-group">
                        <label class="mr-sm-3" for="categories">Choisissez le genre: </label>
                       	<select id="categories" name="categorie" class="form-control" onchange='submit("frm_categories")'>
                       		<option>Tout</option>
                       		<sql:query var="categories">SELECT * FROM categories</sql:query>
                      		<c:forEach var="categorie" items="${categories.rows }">
                           		<option value="${categorie.id }" ${categorieParam != null && categorieParam == categorie.id ? "selected" : ""}>${categorie.label }</option>
                       		</c:forEach>
                       	</select>
                    </div>
                </form>
            </div>
            <div class="row mb-3">
                <div class="col-12 line">
                </div>
            </div>
            
            <div class="card-deck">
            	<sql:query var="articles">SELECT a.id, titre, prix, stock, label, description FROM articles a, categories c WHERE categorie = c.id ${param.categorie != null && param.categorie != "" ? " AND categorie = " + categorieParam : "" }</sql:query>
            	<c:forEach var="article" items="${articles.rows }">
            		<div class="row">
	                    <div class="card flex-row flex-wrap">
	                        <div class="col-md-4">
	                            <img class="img-fluid" src="img/article.jpg">
	                        </div>
	                        <div class="col-md-8">
	                            <div class="card-body">
	                                <h4 class="card-title pb-1 pt-3">${article.titre }</h4>
	                                <h6 class="card-subtitle pb-2">${article.label }</h6>
	                                <h5 class="card-subtitle pb-2 pt-1">${article.prix }$</h5>
	                                <h6 class="card-subtitle pb-2 pt-1">${article.stock } en stock</h6>
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
        </div>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>