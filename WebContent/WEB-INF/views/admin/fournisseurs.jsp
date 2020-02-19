<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fournisseurs</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-admin.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    
    <sql:setDataSource dataSource="jdbc/e_commerce"/>

    <c:if test="${param.id != null && param.id.matches('[0-9]+')}">
    	<sql:update>DELETE FROM fournisseurs WHERE id = ?
    		<sql:param>${param.id }</sql:param>
    	</sql:update>
    </c:if>

    <section id="articles" class="py-5  bg-faded">
	   <div class="container table-container">
	       <div class="row">
	       		<h4 class="display-4 mb-4">List des fournisseurs</h4>
	       		<div class="col-12 table-responsive-lg">
		           <table class="table table-striped">
		               <thead>
		               <tr>
		                   <th scope="col">#</th>
		                   <th scope="col">Nom</th>
		                   <th scope="col">Prenom</th>
		                   <th scope="col">Email</th>
		                   <th scope="col">Mot de passe</th>
		                   <th scope="col">Addresse</th>
		                   <th scope="col">Code postal</th>
		                   <th scope="col">Ville</th>
		                   <th scope="col"></th>
		               </tr>
		               </thead>
		               <tbody>
		               <sql:query var="fournisseurs">SELECT * from fournisseurs ORDER BY id DESC</sql:query>
		               <c:forEach var="fournisseur" items="${fournisseurs.rows }">
		               	<tr>
		               		<th scope="row">${fournisseur.id }</th>
		               		<td>${fournisseur.nom }</td>
		               		<td>${fournisseur.prenom }</td>
		               		<td>${fournisseur.email }</td>
		               		<td>${fournisseur.motPasse }</td>
		               		<td>${fournisseur.addresse }</td>
		               		<td>${fournisseur.codePostal }</td>
		               		<td>${fournisseur.ville }</td>
		               		<td><a class="card-link" href="Fournisseurs?id=${fournisseur.id }">supprimer</a></td>
	               		</tr>
		               </c:forEach>
		               </tbody>
		           </table>
	       		</div>
	       </div>
	   </div>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>