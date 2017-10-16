<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Spring Security: Autenticação</title>
</head>

<body>
	<h1>This is the body of the sample view</h1>

	<security:authorize access="hasRole('ROLE_USER')">
		<div style="color: green">
			This text is only visible to an user
		</div>
		<br/>
	</security:authorize>
	
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<div style="color: blue">
			This text is only visible to an admin
		</div>
		<br/>
	</security:authorize>

	<a href="<c:url value="/perform_logout" />">Logout</a>
	
</body>
</html>