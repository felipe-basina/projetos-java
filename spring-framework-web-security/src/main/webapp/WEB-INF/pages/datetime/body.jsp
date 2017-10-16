<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="form">
	<div id="formTitle">
		<spring:message code="label.datetime" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.datetime" />
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

	<table style="margin: 0 auto;">
		<tr>
			<td colspan="2" class="label"><c:out value="${datetime}" /></td>
		</tr>
	</table>

</div>
