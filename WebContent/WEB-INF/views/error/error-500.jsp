<%@ page isErrorPage="true" language="java" contentType="text/html;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Articles</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
</head>
<body>
    <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<fmt:setBundle basename="lang.error_500" var="error_text"/>
	
	<div class="container">
	    <div class="row my-5">
	        <div class="col-md-12 my-5">
	            <div class="error-template text-center px-3 py-2">
	                <h1>
	                    Oops!
	                </h1>
	                <h2 class="my-2">
	                    <fmt:message key="title" bundle="${error_text }"/>
	                 </h2>
	                <div class="my-2">
	                    <fmt:message key="desc" bundle="${error_text }"/>
	                </div>
	                <div class="my-2 py-2">
	                    <a href="Accueil" class="btn btn-primary btn-lg">
	                        <fmt:message key="accueil" bundle="${error_text }"/>
	                    </a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>