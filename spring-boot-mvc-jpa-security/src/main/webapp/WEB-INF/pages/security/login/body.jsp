<%@ include file="/WEB-INF/pages/commons/tags.jsp"%>

<div id="form">

	<sc:messages />

	<div id="loginform" class="login-box">
		<form name="logonForm" method="post"
			action="${pageContext.request.contextPath}/login">
			<fieldset>
				<legend>
					<spring:message code="label.logon" />
				</legend>
				<table style="margin: 0 auto;">
					<tr>
						<td class=""><spring:message code="prompt.userId" /></td>
						<td><input name="username" type="text" class="textbox"
							maxlength="30" /></td>
					</tr>
					<tr>
						<td colspan="2" height="5" />
					</tr>
					<tr>
						<td class=""><spring:message code="prompt.password" /></td>
						<td><input name="password" type="password" class="textbox"
							maxlength="30" /></td>
					</tr>
					<tr>
						<td colspan="2" height="5" />
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table>
								<tr>
									<td><input name="button" type="submit" class="button"
										value="<spring:message code="label.logon"/>" /></td>
									<td><input type="reset"
										value="<spring:message code='label.clear' />" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

</div>