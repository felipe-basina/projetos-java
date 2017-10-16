<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="spring.boot.500.head.title" /></title>
</head>
<body>
	<h1>
		<spring:message code="spring.boot.500.head.title" />
	</h1>
	<p>
		<spring:message code="spring.boot.500.msg" />
	</p>
	<br>
	<a href='${pageContext.request.contextPath}'><spring:message
			code="spring.boot.404.link.voltar" /></a>
</body>
</html>