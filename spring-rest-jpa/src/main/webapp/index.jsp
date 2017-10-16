<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">

<spring:url value="/resources/js/jquery-min.js" var="jqueryjs" />
<script src="${jqueryjs}"></script>

<spring:url value="/resources/js/jquery.validate.min.js" var="jqueryvalidatejs" />
<script src="${jqueryvalidatejs}"></script>

<spring:url value="/resources/js/jquery-ui.js" var="jqueryuijs" />
<script src="${jqueryuijs}"></script>

<spring:url value="/resources/js/geral.js" var="geraljs" />
<script src="${geraljs}" type="text/javascript"></script>

<spring:url value="/bootstrap/css/bootstrap.css" var="bootstrapcss" />
<link href="${bootstrapcss}" rel="stylesheet" type="text/css"></link>

<spring:url value="/resources/css/geral.css" var="geralcss" />
<link href="${geralcss}" rel="stylesheet" type="text/css"></link>

<spring:url value="/resources/css/jquery-ui.css" var="jqueryuicss" />
<link href="${jqueryuicss}" rel="stylesheet" type="text/css"></link>

</head>
<body>

	<div class="acoes" style="margin: 14px;">
		<h3>rest</h3>
	
		<p style="color: #2282E4">Opções</p>
		<input type="button" value="Recuperar usuários" id="recuperar" class="btn btn-primary" /> |
		<input type="button" value="Limpar conteúdo" id="limpar" class="btn btn-primary" /> |
		<input type="button" value="Criar usuário" id="criar" class="btn btn-primary" /> |
		<input type="button" value="Atualizar usuário" id="atualizar" class="btn btn-primary" /> |
		<input type="button" value="Remover usuário" id="remover" class="btn btn-primary" />
	</div>
	
	<br>
	<div class="usuarios" style="margin: 16px;">
	
		<div class="msg-alerta" style="display: none;"></div>
	
		<form id="div-form" style="display: none;" class="article">
			<input id="idval" type="hidden" value="" />
			<div class="input-prepend">
				<span class="add-on">Nome </span>
				<input type="text" id="nome" name="nome" value="nomeee" class="span4" />
			</div><br/>
			<div class="input-prepend">
				<span class="add-on">Sobrenome </span>
				<input type="text" id="sobrenome" name="sobrenome" value="sobrenomeeee" class="span4" />
			</div><br/>
			<div class="input-prepend">
				<span class="add-on">E-mail </span>
				<input type="text" id="email" name="email" value="email@dominio.com" class="span4" />
			</div><br/><br/>
			
			<input type="submit" class="criar btn btn-primary" value="Salvar" style="display: none;" />
		</form>

		<div id="div-tbl" style="display: none;width: 700px; padding: 20px;" class="borda">
			<p style="color: #ccc;text-align: center">Listagem</p>
			<table id="tbl" class="table">
				<tr>
					<th>#</th>
					<th>NOME</th>
					<th>SOBRENOME</th>
					<th>E-MAIL</th>
				</tr>
			</table>
		</div>
	
	</div>	
</body>

</html>
