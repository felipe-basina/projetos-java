<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isErrorPage = "true"%>
<html>
<head>
<title>HTTP 401</title>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" summary="">
	<col align="center" width="80">
	<col align="left" width="520">
	<tr>
		<td rowspan="5" valign="top">
			<img src="<%= request.getContextPath() %>/images/logo70x70.png" alt="Logo Claro">
		</td>
		<td align="center">
			<font face="Arial, Tahoma, serif" size="6">HTTP 401 - Falha na autentica��o do usu�rio</font>
			<br>
			<font face="Arial, Tahoma, serif" size="4">Seguran�a da Informa��o</font>
		</td>
	</tr>
	<tr>
		<td height="5" />
	</tr>
	<tr>
		<td>
			<font face="Arial, Tahoma, serif" size="2">
			N�o foi poss�vel autenticar o usu�rio, provavelmente devido a usu�rio ou senha incorreta.
			</font>
		</td>
	</tr>
	<tr>
		<td height="5" />
	</tr>
	<tr>
		<td>
			<font face="Arial, Tahoma, serif" size="2">
				Em caso de d�vida entre em contato com a �rea de Seguran�a da Informa��o da Claro.
			</font>
		</td>
	</tr>
</table>
</body>
</html>
