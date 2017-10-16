<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/todo.js"></script>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/estilos.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css" />


<title>...</title>

<script>

	$(function() {
		console.log("ok");
		
		$("#listar").on("click", function() {
			todoJS.listar($("#tabela"));
		});
		
		$("#adicionar").on("click", function() {
			todoJS.adicionar($("#tabela"));
		});
		
		$("#limpar").on("click", function() {
			todoJS.limparFormulario();
		});
		
		var _table = $('#tabela').DataTable({
			pagingType: 'simple' // Remove a paginação por números das páginas
		});
		
		// Carrega a lista
		todoJS.listar($("#tabela"));
			     
		// Captura o evento de selecionar uma linha
		$("#tabela tbody").on('click', 'tr', function () {
			try {
		        var data = _table.row(this).data(); // Linha selecionada
		        $("input[name=id]").val(data[0]);
		        $("input[name=titulo]").val(data[1]);
		        $("input[name=descricao]").val(data[2]);
			} catch(e) {
				console.log(e);
			}
	    });
		
	});
	
</script>


</head>
<body>

	<div id="div_principal" class="commons">

		<div id="div_esquerda" class="commons">
			<input type="hidden" name="id" class="clean" value="" />
			<label class="x">Título: </label><input type="text" name="titulo" class="clean" />
			<label class="x">Descrição: </label><input type="text" name="descricao" class="clean" />
			
			<div id="status" style="display:none"></div>
		</div>
		
		<div id="div_direita" class="commons">
			<input type="button" value="SALVAR" id="adicionar" class="btn btn-primary" /></BR></BR>
			<input type="button" value="LISTAR" id="listar" class="btn btn-info" /></BR></BR>
			<input type="button" value="LIMPAR" id="limpar" class="btn btn-danger" />
		</div>

		<div class="clearfix"></div>

		<div id="div_central" class="commons">
			<div id="lista">
				<table id="tabela" border="0">
					<thead>
						<tr>
							<th>#</th>
							<th>TÍTULO</th>
							<th>DESCRIÇÃO</th>
							<th>DT. CRIAÇÃO</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>

	</div><!-- Fim da DIV div_principal -->

</body>
</html>