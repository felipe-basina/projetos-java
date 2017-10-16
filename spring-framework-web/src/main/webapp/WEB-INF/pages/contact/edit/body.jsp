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

	<br>
	<c:if test="${mensagem != null}">
		<div id="msg-box">${mensagem}${contato}</div>
	</c:if>
	<br>
	
	<form:form method="post" action="updateContact.do" commandName="command">
		<form:hidden path="id" />
		<table style="margin: 0;" border="0">
			<tr>
				<td class=""><form:label path="nome">
						<spring:message code="label.firstname" />
					</form:label></td>
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
			value="<spring:message code="label.editcontact"/>" />
			
		<input type="button" class="button" onclick="javascript:submitForm();"
			value="<spring:message code="label.cancel"/>" />

	</form:form>

</div>

<script>

	function submitForm() {
		var objForm = document.forms[0];
		objForm.innerHTML = '';
		objForm.method = 'GET';
		objForm.action = 'getContacts.do';
		objForm.submit();
	}
</script>
