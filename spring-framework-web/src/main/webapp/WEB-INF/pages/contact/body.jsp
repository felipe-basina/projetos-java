<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="form">

	<div id="formTitle">
		<spring:message code="label.contact" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.contact" />
	</div>

	<br/>
	<c:if test="${mensagem != null}">
		<div id="msg-box" class="alert-error">${mensagem}</div><br/>
	</c:if>
	
	<form:form method="post" action="addContact.do" commandName="command">

		<table style="margin: 0;" border="0">
			<tr>
				<td class=""><form:label path="nome">
						<spring:message code="label.firstname" />
					</form:label></th>
				<td><form:input class="textbox" path="nome" /></td>
				<td>
					<span class="help-inline"><form:errors path="nome" cssClass="alert-error" /></span>
				</td>
			</tr>
			<tr>
				<td class=""><form:label path="sobreNome">
						<spring:message code="label.lastname" />
					</form:label></td>
				<td><form:input class="textbox" path="sobreNome" /></td>
				<td>
					<span class="help-inline"><form:errors path="sobreNome" cssClass="alert-error" /></span>
				</td>
			</tr>
			<tr>
				<td class=""><form:label path="email">
						<spring:message code="label.email" />
					</form:label></td>
				<td><form:input class="textbox" path="email" /></td>
				<td>
					<span class="help-inline"><form:errors path="email" cssClass="alert-error" /></span>
				</td>
			</tr>
			<tr>
				<td class=""><form:label path="telefone">
						<spring:message code="label.telephone" />
					</form:label></td>
				<td><form:input class="textbox" path="telefone" /></td>
				<td>
					<span class="help-inline"><form:errors path="telefone" cssClass="alert-error" /></span>
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" class="button"
			value="<spring:message code="label.addcontact"/>" />

	</form:form>

</div>