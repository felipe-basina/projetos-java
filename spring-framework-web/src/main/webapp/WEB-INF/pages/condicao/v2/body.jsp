<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="form">
	<div id="formTitle">
		Verdadeiro | Falso
	</div>
	<div id="formSubtitle">
		Teste Verdadeiro | Falso
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>

	<form:form method="post" action="trueorfalse.do" commandName="command">

		<table style="margin: 0 auto;">
			<tr>
				<td><spring:message code="prompt.name" /></td>
				<td><form:input path="nome" name="nome" type="text" maxlength="30" class="textbox"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td>
								<c:choose>
									<c:when test="${command.exibirMensagem eq true}">
										<h1><font color="cyan"><c:out value="${command.mensagem}" /></font></h1>
									</c:when>
									<c:otherwise>
										<h1><font color="red"><c:out value="Nenhuma mensagem a ser exibida" /></font></h1>
									</c:otherwise>	
								</c:choose>							
							</td>
						</tr>						
					</table>			
				</td>
			</tr>
		</table>
		<br>
		
		<input type="submit" class="button" value="<spring:message code="label.send" />" />
		
	</form:form>

</div>
