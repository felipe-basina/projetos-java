<%@ include file="/WEB-INF/pages/commons/tags.jsp" %>

<div id="form">

	<div id="formTitle">
		<h1>
			<spring:message code="spring.boot.form.title" />
		</h1>
	</div>
	<div id="formSubtitle">
		<h2>
			<spring:message code="spring.boot.form.subtitle" />
		</h2>
	</div>

	<c:if test="${mensagem != null}">
		<div id="msg-box" class="alert-error borda">${mensagem}</div>
		<br />
	</c:if>

	<form:form method="post" action="${pageContext.request.contextPath}/save.do" commandName="command">
		<table style="margin: 0;" border="0">
			<tr>
				<td class=""><form:label path="nome">
						<spring:message code="spring.boot.form.campo.nome" />
					</form:label></td>
				<td><form:input class="textbox" path="nome" id="nome" /></td>
				<td><span class="help-inline"><form:errors path="nome"
							cssClass="alert-error" /></span></td>
			</tr>
			<tr>
				<td class=""><form:label path="ultimoNome">
						<spring:message code="spring.boot.form.campo.ultimonome" />
					</form:label></td>
				<td><form:input class="textbox" path="ultimoNome"
						id="ultimoNome" /></td>
				<td><span class="help-inline"><form:errors
							path="ultimoNome" cssClass="alert-error" /></span></td>
			</tr>
			<tr>
				<td class=""><form:label path="telefone">
						<spring:message code="spring.boot.form.campo.telefone" />
					</form:label></td>
				<td><form:input class="textbox campoTelefone" path="telefone" id="telefone" /></td>
				<td><span class="help-inline"><form:errors
							path="telefone" cssClass="alert-error" /></span></td>
			</tr>
		</table>
		<br>
		<input type="submit" class="button"
			value="<spring:message code='spring.boot.form.botao.salvar' />" />
		<input type="reset"
			value="<spring:message code='spring.boot.form.botao.limpar' />" />
	</form:form>

	<div class="container" style="padding-top: 25px;">
		<table class="display" id="example" border="0">
			<thead>
				<tr align="center">
					<th><spring:message code="spring.boot.form.tabela.coluna.nome" /></th>
					<th><spring:message
							code="spring.boot.form.tabela.coluna.ultimonome" /></th>
					<th><spring:message
							code="spring.boot.form.tabela.coluna.telefone" /></th>
					<th><spring:message
							code="spring.boot.form.tabela.coluna.acao.editar" /></th>
					<th><spring:message
							code="spring.boot.form.tabela.coluna.acao.excluir" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="usuario" items="${usuarios}" varStatus="idStatus">
					<tr align="center"
						class="${idStatus.index % 2 == 0 ? 'gradeC' : 'gradeX'}">
						<td><c:out value="${usuario.nome}" /></td>
						<td><c:out value="${usuario.ultimoNome}" /></td>
						<td><c:out value="${usuario.telefone}" /></td>
						<td nowrap="nowrap"><a
							href="<c:url value="/getUser.do?nome=${usuario.nome}"/>"
							onclick="javascript:return confirm('<spring:message code='spring.boot.form.msg.editar' />');">
								<spring:message
									code="spring.boot.form.tabela.coluna.acao.editar" />
						</a></td>
						<td nowrap="nowrap"><a
							href="<c:url value="/remove.do?nome=${usuario.nome}"/>"
							onclick="javascript:return confirm('<spring:message code='spring.boot.form.msg.excluir' />');">
								<spring:message
									code="spring.boot.form.tabela.coluna.acao.excluir" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>