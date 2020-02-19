<%@ page language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Commandes</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style-admin.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    
    <sql:setDataSource dataSource="jdbc/e_commerce"/>

    <c:if test="${param.id != null && param.id.matches('[0-9]+')}">
    	<sql:update>DELETE FROM commandes WHERE id = ?
    		<sql:param>${param.id }</sql:param>
    	</sql:update>
    </c:if>

    <section id="articles" class="py-5  bg-faded">
	   <div class="container table-container">
	       <div class="row">
	       		<h4 class="display-4 mb-4">List des commandes</h4>
	       		<div class="col-12 table-responsive-lg">
		           <table class="table table-striped">
		               <thead>
		               <tr>
		                   <th scope="col">#</th>
		                   <th scope="col">Client</th>
		                   <th scope="col">Date</th>
		                   <th scope="col">Quantite</th>
		                   <th scope="col">Prix total</th>
		                   <th scope="col"></th>
		               </tr>
		               </thead>
		               <tbody>
		               <sql:query var="commandes">
		               		SELECT cm.id, CONCAT(nom, ' ', prenom) client, dateCommande, SUM(quantite) quantite, SUM(quantite * prix) prixTotal
							FROM commandes cm, articles a, clients cl, lignesCommande lc 
							WHERE cm.client = cl.id AND cm.id = lc.commande AND lc.article = a.id
							GROUP BY cm.id, dateCommande, nom, prenom
						 	ORDER BY id DESC
						</sql:query>
		               <c:forEach var="commande" items="${commandes.rows }">
		               	<tr>
		               		<th scope="row">${commande.id }</th>
		               		<td>${commande.client }</td>
		               		<td><fmt:formatDate value="${commande.dateCommande }" type="both" dateStyle="short" timeStyle="short"/></td>
		               		<td>${commande.quantite }</td>
		               		<td>${commande.prixTotal }</td>
		               		<td><a class="card-link" href="Commandes?id=${commande.id }">supprimer</a></td>
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