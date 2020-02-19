<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-admin.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    
    <sql:setDataSource dataSource="jdbc/e_commerce"/>

    <c:if test="${param.id != null && param.id.matches('[0-9]+')}">
    	<sql:update>DELETE FROM categories WHERE id = ?
    		<sql:param>${param.id }</sql:param>
    	</sql:update>
    </c:if>
    
    <c:if test='${pageContext.request.method == "POST" && param.label != null}'>
    	<sql:update>INSERT INTO categories(label) VALUES(?)
    		<sql:param>${param.label }</sql:param>
    	</sql:update>
    </c:if>

    <section id="articles" class="py-5  bg-faded">
	   <div class="container table-container">
	       <div class="row">
	       		<h4 class="display-4 mb-4">List des categories</h4>
	       		<div class="col-12 table-responsive-lg">
		           <table class="table table-striped">
		               <thead>
		               <tr>
		                   <th scope="col">#</th>
		                   <th scope="col">Label</th>
		                   <th scope="col">Articles</th>
		                   <th scope="col"></th>
		               </tr>
		               </thead>
		               <tbody>
		               <sql:query var="categories">SELECT c.id, label, COUNT(a.id) articles FROM categories c LEFT JOIN articles a ON c.id = a.categorie GROUP BY c.id, label</sql:query>
		               <c:forEach var="categorie" items="${categories.rows }">
		               	<tr>
		               		<th scope="row">${categorie.id }</th>
		               		<td>${categorie.label }</td>
		               		<td>${categorie.articles }</td>
		               		<td><a class="card-link" href="Categories?id=${categorie.id }">supprimer</a></td>
	               		</tr>
		               </c:forEach>
		               </tbody>
		           </table>
	       		</div>
	       </div>
	        <div class="row">
	            <form class="form-inline col-12 mt-2 mb-5" action="Categories" method="POST">
	                <label class="control-label col-md-3 mx-3" for="label">Nouvelle catégorie</label>
	                <input id="label" name="label" type="text" maxlength="50" class="form-control col-md-6 mx-3 mr-auto" placeholder="Enter une label">
	                <button type="submit" class="btn btn-light form-control col-md-2 mx-3">Ajouter</button>
	            </form>
	        </div>
	   </div>
    </section>

	<c:set var="url">Categories</c:set>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>