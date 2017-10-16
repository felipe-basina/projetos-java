<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="form">

	<div id="formTitle">
		<spring:message code="label.tabs" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.tabs.navegation" />
	</div>

	<br/>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1"><spring:message code="label.tabs.n01" /></a></li>
			<c:if test="${ response != null }">
				<li><a href="#tabs-2"><spring:message code="label.tabs.n02" /></a></li>
			</c:if>
		</ul>
		
		<!-- Tab 01 -->
		<div id="tabs-1">
			<c:if test="${mensagem != null}">
				<div id="msg-box" class="alert-error">${mensagem}</div><br/>
			</c:if>

			<p><spring:message code="label.tabs.info.requisicao" />:</p>
			
			<form:form method="post" action="clientews.do" commandName="command">
				<table style="margin: 0 auto;">
					<tr>
						<td>
							<form:label path="nome">
								<spring:message code="label.tabs.nome" />
							</form:label>
						</td>
						<td><form:input path="nome" name="nome" type="text" maxlength="20" class="textbox"/></td>
						<td>
							<span class="help-inline"><form:errors path="nome" cssClass="alert-error" /></span>
						</td>
					</tr>
					<tr>
						<td>
							<form:label path="dtNascimento">
								<spring:message code="label.tabs.dtnascimento" />
							</form:label>
						</td>
						<td><form:input path="dtNascimento" name="dtNascimento" type="text" maxlength="10" class="textbox"/></td>
						<td>
							<span class="help-inline"><form:errors path="dtNascimento" cssClass="alert-error" /></span>
						</td>
					</tr>
				</table>
				
				<input type="submit" class="button" value="<spring:message code="label.send" />" />
				<input type="button" class="button" value="<spring:message code="label.tabs.restart" />" onclick="javascript:confirmMessage('<spring:message code="label.tabs.restart.confirm" />');" />
			</form:form>
		</div>
		<!-- Tab 01 -->
		
		<!-- Tab 02 -->
		<c:if test="${ response != null }">
			<div id="tabs-2">
				<p><spring:message code="label.tabs.info.retorno" /></p>
				<p><c:out value="${response.mensagem}" /></p>
			</div>
		</c:if>
		<!-- Tab 02 -->
		
	</div>

</div>

<script>

	function confirmMessage(msg) {
		if (confirm(msg)) {
			submitForm();
		}
	}

	function submitForm() {
		var objForm = document.forms[0];
		objForm.innerHTML = '';
		objForm.method = 'GET';
		objForm.action = 'tabs.do';
		objForm.submit();
	}
	
</script>