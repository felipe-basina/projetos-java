<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="form">

	<div id="formTitle">
		<spring:message code="label.crud" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.crud.operation" />
	</div>

	<br/>
	<c:if test="${mensagem != null}">
		<div id="msg-box" class="alert-error">${mensagem}</div><br/>
	</c:if>
	
	<form:form method="post" action="updateOne.do" commandName="command">
		<form:hidden path="id" />
		
		<!-- Cadastro de usuário -->
		
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
		
		<!-- Cadastro de usuário -->
				
		<!-- Botões -->

		<input type="submit" class="button" name="salvar"
			value="<spring:message code="label.savecontact"/>" />
			
		<input type="button" class="button" onclick="javascript:submitForm();"
			value="<spring:message code="label.cancel"/>" />
		
		<br><br><br>
		
		<!-- Botões -->
		
		
		<!-- Exibição da listagem de usuários -->
		
		<div class="container" style="padding-top: 15px;">
			<table class="display" id="example" border="0">
				<thead>
					<tr align="center">
						<th>
							<spring:message code="label.firstname" />
						</th>
						<th>
							<spring:message code="label.lastname" />
						</th>
						<th>
							<spring:message code="label.email" />
						</th>
						<th>
							<spring:message code="label.telephone" />
						</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contato" items="${contatos}" varStatus="idStatus">
					<tr align="center" class="${idStatus.index % 2 == 0 ? 'even gradeC' : 'odd gradeX'}">
						<td><c:out value="${contato.nome}"/></td>
						<td><c:out value="${contato.sobreNome}"/></td>
						<td><c:out value="${contato.email}"/></td>
						<td><c:out value="${contato.telefone}"/></td>
						<td nowrap="nowrap">
							<a href="<c:url value="/getOne.do?id=${contato.id}"/>" onclick="return confirm('<spring:message code="label.crud.update.confirm" />')" >Editar</a>
						</td>
						<td nowrap="nowrap">
							<a href="<c:url value="/removeOne.do?id=${contato.id}"/>" onclick="return confirm('<spring:message code="label.crud.remove.confirm" />')" >Excluir</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</tfoot>				
			</table>
			</div><br>
			
		<!-- Exibição da listagem de usuários -->			
			
	</form:form>

</div>

<script>

	function submitForm() {
		var objForm = document.forms[0];
		objForm.innerHTML = '';
		objForm.method = 'GET';
		objForm.action = 'init.do';
		objForm.submit();
	}
	
</script>