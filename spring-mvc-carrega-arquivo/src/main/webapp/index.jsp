<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>spring mvc carregar arquivo...</h2>

	<form:form method="POST"
		commandName="carrega"
		action="carrega"
		enctype="multipart/form-data">

		<form:errors path="*" cssClass="errorblock" element="div" />

		<label>Selecione arquivo [1]: </label>
		<input type="file" name="files[0]" />
		<br>
		<label>Selecione arquivo [2]: </label>
		<input type="file" name="files[1]" />
		<br>
		<input type="submit" value="carregar" />
		<br>
		<span><form:errors path="files" cssClass="error" /></span>

	</form:form>

</body>
</html>