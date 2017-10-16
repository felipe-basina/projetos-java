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

<script>

	function iniciarElementos() {
		
		limparConteudoMensagem();
	
		limparListaUsuarios();

	}

	function limparConteudoMensagem() {
		
		if ($(".msg-alerta").css('display') === 'block') {
			$(".msg-alerta")
				.css('display', 'none')
				.html('');
		}
		
	}

	function limparListaUsuarios() {
		
		$("#div-tbl").css("display", "none");

		// Identifica o total de linhas na tabela
		var totalLinhas = $('#tbl tr').length;

		// Não remove a linha que possui o cabeçalho da tabela
		if (totalLinhas !== 1) {
			for (var i = 0; i < totalLinhas - 1; i++) {
				$('#tbl tr:last').remove();
			}
		}
		
	}

	$(function() {
		
		$('#recuperar, #limpar').on( "click", function() {
			
			if ($(this).val() === 'Recuperar usuários') {
			  console.log('---> Preparando para recuperar usuários...');
			  
			  $.ajax({
					type: 'get',
					url: 'employees',
					dataType: 'json', // Define o tipo de retorno
					success: function(data) {
						console.log('---> Retorno: ' + JSON.stringify(data));
						
						if (data !== null
								&& data.employees !== null) {
						
							iniciarElementos();
							
							var lista = data.employees;
							
							$("#div-tbl").css("display", "block");
							
							$.each(lista, function(index, usuario) {
								console.log('# Usuario [' + JSON.stringify(usuario) + ']');
	
								$("#tbl tr:last").after('<tr><td>' + usuario.id + '</td>'
										+ '<td>' + usuario.firstName + '</td>'
										+ '<td>' + usuario.lastName + '</td>'
										+ '<td>' + usuario.email + '</td>'
										+ '</tr>');
							});
						} else {
							var msg = "Não foi possível recuperar usuários!";
							$(".msg-alerta")
								.css('display', 'block')
								.append(msg);
						}
						
					},
					error: function(data) {
						console.log('---> Error: ' + JSON.stringify(data));		
					}
			  });
		  
			} else if ($(this).val() === 'Limpar conteúdo') {
			
				iniciarElementos();
				
			} else {
				console.log(' # Operacao nao identificada!');
			}

		});
	});

</script>


<style type="text/css">
.borda {
	border: 3px solid green;
	height: 20%;
	width: 700px;
	overflow-y: auto;
	background-color: green;
}

td {
	border: 2px solid red;
	padding: 6px;
	text-align: center;
	color: white;
	width: 300px;
}
</style>

</head>
<body>
	<h2>REST - API</h2>
</body>

<p>Recuperar lista de usuários:</p>
<input type="button" value="Recuperar usuários" id="recuperar" /> |
<input type="button" value="Limpar conteúdo" id="limpar" />
<br>
<br>
<div class="usuarios">

	<div class="msg-alerta" style="display: none;"></div>

	<div id="div-tbl" style="display: none;" class="borda">
		<table id="tbl">
			<tr>
				<td>#</td>
				<td>NOME</td>
				<td>SOBRENOME</td>
				<td>E-MAIL</td>
			</tr>
		</table>
	</div>

</div>

</html>
