<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>HTTP 404</title>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" summary="">
		<col align="center" width="80">
		<col align="left" width="520">
		<tr>
			<td rowspan="5" valign="top"><img
				src="<%=request.getContextPath()%>/images/logo70x70.png"
				alt="Logo Claro"></td>
			<td align="center"><font face="Arial, Tahoma, serif" size="6">HTTP
					404 - Recurso n�o encontrado</font> <br> <font
				face="Arial, Tahoma, serif" size="4">Informa��o n�o dispon�vel</font>
			</td>
		</tr>
		<tr>
			<td height="5" />
		</tr>
		<tr>
			<td><font face="Arial, Tahoma, serif" size="2"> O recurso procurado n�o existe ou n�o
			est� dispon�vel </font></td>
		</tr>
		<tr>
			<td height="5" />
		</tr>
		<tr>
			<td><font face="Arial, Tahoma, serif" size="2"> Em caso
					de d�vida entre em contato com a �rea de Suporte de Aplica��es Corporativas da
					Claro. </font></td>
		</tr>
		<tr>
			<td>
				<a href='<c:url value="/" />'>Voltar</a>
			</td>
		</tr>
	</table>
</body>
</html>
