<%@ page language="java" import="javax.servlet.http.HttpSession"
	import="org.apache.commons.logging.Log"
	import="org.apache.commons.logging.LogFactory"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet"
	href="css/claro.css" type="text/css">
</head>

<body>
	<!-- In�cio da P�gina -->
	<center>
		<!-- In�cio do T�tulo da P�gina -->
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="350">
			<tr>
				<td class="page-title"><spring:message code="label.information" /></td>
			</tr>
			<tr>
				<td class="page-subtitle"><spring:message code="label.information.message" /></td>
			</tr>
		</table>
		<br>
		<!-- T�rmino do T�tulo da P�gina -->
		<!-- In�cio do Corpo da P�gina -->
		<table border="0" cellpadding="0" cellspacing="0" width="350">
			<tr>
				<td class="label" align="center"><spring:message code="msg.session.invalidate" /></td>
			</tr>
			<tr>
				<td height="5" />
			</tr>
		</table>
		<!-- T�rmino do Corpo da P�gina -->
	</center>
	<!-- T�rmino da P�gina -->
</body>
</html>

<script type="text/javascript">
<!-- start hiding
	setTimeout("self.close();", 3000);
// end hiding -->
</script>

<%
  // Gets the session
			String sessionId = request.getSession(true).getId();
			String userId = request.getRemoteUser();
			String remoteAddr = request.getRemoteAddr();

			Log logger = LogFactory
					.getLog("com.claro.security.jsp.invalidate_jsp");

			logger.info("The user [" + userId
					+ "] has automatic logged off of session [" + sessionId
					+ "] from  [" + remoteAddr + "].");

			request.getSession(true).invalidate();
%>

