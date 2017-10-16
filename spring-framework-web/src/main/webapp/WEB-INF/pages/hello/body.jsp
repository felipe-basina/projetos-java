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

	<form:form method="post" action="helloworld.do">

		<table style="margin: 0 auto;">
			<tr>
				<td class="label"><spring:message code="prompt.name" /></td>
				<td><form:input path="name" name="name" type="text" maxlength="30" class="textbox"/></td>
			</tr>
			<tr>
				<td colspan="2" height="5" />
			</tr>
			<tr>
				<td colspan="2" class="label"><c:out value="${command.message}" /></td>
			</tr>
		</table>
		<br>
		<input type="submit" class="button" value="<spring:message code="label.send" />" />
		<input type="reset" class="button" value="<spring:message code="label.reset" />" />
	</form:form>

</div>
