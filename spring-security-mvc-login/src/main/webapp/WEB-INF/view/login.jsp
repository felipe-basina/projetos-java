<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Spring Security: Autenticação</title>

<style type="text/css">
.align_right {
	text-align: right;
}

.align_center {
	text-align: center;
}

.borda {
	border: 1px solid;
	width: 400px;
}

.align_div_center {
	margin-left: auto;
	margin-right: auto;
}
</style>

</head>

<body>
	<h1>Login</h1>

	<c:if test="${not empty message}">
		<div style="color: red" class="align_div_center borda">
			<p class="align_center">${message}</p>
		</div>
		<br>
	</c:if>
	<c:remove var="message" scope="session" />

	<form name='f' action="perform_login" method='POST'>
		<div class="align_div_center borda">
			<table>
				<tr>
					<td class="align_right">Login:</td>
					<td><input type='text' name='j_username' value=''></td>
				</tr>
				<tr>
					<td class="align_right">Senha:</td>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td colspan="2" class="align_center">
						<img id="jcaptchaId" src='<c:url value="/jcaptcha.jpg" />' />
					</td>
				</tr>
				<tr>
					<td class="align_right">Digite o valor da imagem:</td>
					<td>
						<input id="valorCaptcha" name="valorCaptcha" type="text">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="align_center"><input name="submit" type="submit" value="submit" /></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>