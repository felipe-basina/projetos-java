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
					404 - Recurso não encontrado</font> <br> <font
				face="Arial, Tahoma, serif" size="4">Informação não disponível</font>
			</td>
		</tr>
		<tr>
			<td height="5" />
		</tr>
		<tr>
			<td><font face="Arial, Tahoma, serif" size="2"> O recurso procurado não existe ou não
			está disponível </font></td>
		</tr>
		<tr>
			<td height="5" />
		</tr>
		<tr>
			<td><font face="Arial, Tahoma, serif" size="2"> Em caso
					de dúvida entre em contato com a área de Suporte de Aplicações Corporativas da
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
