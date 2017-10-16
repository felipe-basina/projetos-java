<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>P�gina n�o encontrada</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<link rel="stylesheet" href="${coreCss}" />

</head>
<body>

	<div class="fonte-principal">P�gina n�o encontrada</div>

	<div class="fonte-secundaria">
		<p>
			A p�gina solicitada n�o existe ou n�o est� dispon�vel.<br> Caso
			o erro persista, por favor, entre em contato com o administrador.
		</p>
	</div>

	<br>

	<a href="/spring4" class="fonte-voltar">Voltar</a>

</body>
</html>