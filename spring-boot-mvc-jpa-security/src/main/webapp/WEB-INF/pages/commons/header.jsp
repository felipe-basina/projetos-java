<%@ include file="/WEB-INF/pages/commons/tags.jsp" %>

<div id="headerSection">
	<div id="headerTitle">
		<spring:message code="spring.boot.form.head.title" />
		&nbsp;&nbsp;<spring:message code="spring.boot.greeting" /> <b><security:authentication property="principal.username" /></b> 
		&nbsp;&nbsp;||
		&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/logout">Log out</a>
	</div>
	
</div>

