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
			<font face="Arial, Tahoma, serif" size="6">HTTP 401 - Falha na autenticação do usuário</font>
			<br>
			<font face="Arial, Tahoma, serif" size="4">Segurança da Informação</font>
		</td>
	</tr>
	<tr>
		<td height="5" />
	</tr>
	<tr>
		<td>
			<font face="Arial, Tahoma, serif" size="2">
			Não foi possível autenticar o usuário, provavelmente devido a usuário ou senha incorreta.
			</font>
		</td>
	</tr>
	<tr>
		<td height="5" />
	</tr>
	<tr>
		<td>
			<font face="Arial, Tahoma, serif" size="2">
				Em caso de dúvida entre em contato com a área de Segurança da Informação da Claro.
			</font>
		</td>
	</tr>
</table>
</body>
</html>
