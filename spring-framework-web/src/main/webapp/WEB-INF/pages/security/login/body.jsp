<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	function hideMessage() {
		var element = document.getElementById("msg-box");
		element.innerHTML = "";
		element.className = "none";
		return;
	}

	function showMessage(level, msg) {

		if (level == "validation" || level == "info" || level == "success" || level == "warning" || level == "error") {
			var element = document.getElementById("msg-box");
			element.innerHTML = msg;
			element.className = level;
			return;
		}
	}

	function validateLogonForm(form) {

		if (form.j_username.value == '') {
			showMessage('validation', '<spring:message code="error.security.userid.required" />');
			form.j_username.focus();
			return false;
		}
		if (form.j_password.value == '') {
			showMessage('validation', '<spring:message code="error.security.password.required" />');
			form.j_password.focus();
			return false;
		}

		return true;
	}
</script>

<div id="form">
	<div id="formTitle">
		<spring:message	code="label.authentication" />
	</div>
	<div id="formSubtitle">
		<spring:message code="label.logon" />
	</div>

	<br>
	<div id="msg-box" class="none">&nbsp;</div>
	<br>
	
	<form name="logonForm" method="post" action="j_security_check" onsubmit="javascript:return validateLogonForm(this);">

	<table style="margin: 0 auto;">
		<tr>
			<td class="">
				<spring:message code="prompt.userId" />
			</td>
			<td>
				<input name="j_username" type="text" class="textbox" maxlength="30" />
			</td>
		</tr>
		<tr>
			<td colspan="2" height="5" />
		</tr>
		<tr>
			<td class=""><spring:message code="prompt.password" /></td>
			<td>
				<input name="j_password" type="password" class="textbox" maxlength="30" />
			</td>
		</tr>
	</table>
	<br>
	<input name="button" type="submit" class="button" value="<spring:message code="label.logon"/>" />
	<input name="button" type="button" class="button" value="<spring:message code="label.exit"/>" onclick="window.close();" />
</form>

</div>

<script type="text/javascript">
<!--
	document.forms["logonForm"].elements["j_username"].focus();
<% if ("true".equals(request.getParameter("error"))) {%>
	showMessage('validation', '<spring:message code="error.security.invalid.login" />');
<%}%>
// -->
</script>
