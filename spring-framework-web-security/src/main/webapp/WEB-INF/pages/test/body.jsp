<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="form">
	<div id="formTitle">
		<spring:message code="label.example" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.example" />
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

	<p>Página de teste de acesso</p>
	<security:isInRole roles="appGrpUser">Usuário [<%= request.getRemoteUser() %>] pertence ao grupo [appGrpUser]<br /></security:isInRole>
	<security:isNotInRole roles="appGrpUser">Usuário [<%= request.getRemoteUser() %>] NÃO pertence ao grupo [appGrpUser]<br /></security:isNotInRole>
	<security:isInRole roles="appGrpOper">Usuário [<%= request.getRemoteUser() %>] pertence ao grupo [appGrpOper]<br /></security:isInRole>
	<security:isNotInRole roles="appGrpOper">Usuário [<%= request.getRemoteUser() %>] NÃO pertence ao grupo [appGrpOper]<br /></security:isNotInRole>
	<security:isInRole roles="appGrpAdmin">Usuário [<%= request.getRemoteUser() %>] pertence ao grupo [appGrpAdmin]<br /></security:isInRole>
	<security:isNotInRole roles="appGrpAdmin">Usuário [<%= request.getRemoteUser() %>] NÃO pertence ao grupo [appGrpAdmin]<br /></security:isNotInRole>

</div>
