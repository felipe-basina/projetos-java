<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="form">
	<div id="formTitle">
		<spring:message code="label.se.senao" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.se.senao" />
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

	<form:form method="post" action="exibesenao.do">

		<table style="margin: 0 auto;">
			<tr>
				<td class="label"><spring:message code="digite.numero" /></td>
				<td><form:input path="numero" name="numero" type="text" maxlength="2" class="textbox"/></td>
			</tr>
		</table>

		<c:choose>
			<c:when test="${command.name == 'EXIBE_IF'}">
				<h2>Entrou no EXIBE_IF</h2>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${command.name == 'EXIBE_ELSE_01'}">
						<h2>Entrou no EXIBE_IF_01</h2>					
					</c:when>
					<c:when test="${command.name == 'EXIBE_ELSE_02'}">
						<h2>Entrou no EXIBE_IF_02</h2>					
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
		
		<input type="submit" class="button" value="<spring:message code="label.send" />" />

	</form:form>

</div>