<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvelle article</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-articles-gestion.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.ajouter_article" var="ajouter_article_text"/>

    <section id="ajouter_articles" class="py-5 bg-gray">
        <div class="container">
            <div class="row my-3">
                <div id="forme" class="col-md-8 col-lg-6  mx-auto p-5">
                    <form action="Ajouter-Article" method="POST" enctype="multipart/form-data">
                        <h2 class="pb-3">
                            <fmt:message key="nouvelle_article" bundle="${ajouter_article_text }"/>
                        </h2>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="titre"><fmt:message key="titre" bundle="${ajouter_article_text }"/></label>
                            <input id="titre" name="titre" type="text" class="form-control" placeholder='<fmt:message key="enter" bundle="${ajouter_article_text }"/> <fmt:message key="titre" bundle="${ajouter_article_text }"/>' required>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="prix"><fmt:message key="prix" bundle="${ajouter_article_text }"/></label>
                            <input id="prix" name="prix" type="number" class="form-control" placeholder='<fmt:message key="enter" bundle="${ajouter_article_text }"/> <fmt:message key="prix" bundle="${ajouter_article_text }"/>' required>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="stock"><fmt:message key="stock" bundle="${ajouter_article_text }"/></label>
                            <input id="stock" name="stock" type="number" class="form-control" placeholder='<fmt:message key="enter" bundle="${ajouter_article_text }"/> <fmt:message key="stock" bundle="${ajouter_article_text }"/>' required>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="categories"><fmt:message key="categorie" bundle="${ajouter_article_text }"/></label>
                            <select id="categories" name="categorie" class="form-control">
	                      		<c:forEach var="categorie" items="${requestScope.categories }">
	                           		<option value="${categorie.id }">${categorie.label }</option>
	                       		</c:forEach>
                            </select>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label" for="description"><fmt:message key="description" bundle="${ajouter_article_text }"/></label>
                            <textarea id="description" name="description" class="form-control" style="max-height: 300px; min-height: 100px;" placeholder='<fmt:message key="enter" bundle="${ajouter_article_text }"/> <fmt:message key="description" bundle="${ajouter_article_text }"/>' maxlength="150" required></textarea>
                            <small class="form-text text-muted"><fmt:message key="letters" bundle="${ajouter_article_text }"/></small>
                        </div>
                        <div class="form-groupe my-3">
                            <label class="control-label">Photo</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="upload">Upload</span>
                                </div>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="photo_ifile" name="photo" accept=".jpg" aria-describedby="upload">
                                    <label class="custom-file-label" for="photo_ifile"><fmt:message key="choisir_fichier" bundle="${ajouter_article_text }"/></label>
                                </div>
                            </div>
                            <div class="form-groupe mt-5">
                                <button type="submit" class="btn btn-primary btn-lg form-control"><fmt:message key="ajouter" bundle="${ajouter_article_text }"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

	<c:set var="url">Ajouter-Article</c:set>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>