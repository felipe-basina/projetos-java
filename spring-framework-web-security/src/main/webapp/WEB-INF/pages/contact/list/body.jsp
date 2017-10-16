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
	
	<c:if test="${mensagem != null}">
		<br/><div id="msg-box" class="alert-error">${mensagem}</div><br/>
	</c:if>
	
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
					<a href="<c:url value="/getContact.do?id=${contato.id}"/>">Editar</a>
				</td>
				<td nowrap="nowrap">
					<a href="<c:url value="/removeContact.do?id=${contato.id}"/>">Excluir</a>
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
	</div>
			
		<br>

</div>