<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${message != null && not empty message}">
	<div class="validation" style="display: block;">
		<strong><spring:message code="form.alert.${message.status}" /></strong>${message.message}
	</div>
</c:if>
<c:remove var="message" scope="session" />
