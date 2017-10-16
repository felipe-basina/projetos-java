/**
 * 
 */

var CONTEXT = "/rest/todo/";
var todoJS = {};	
todoJS = {
		
	listar: function(table) {
		
		$.ajax({
			url: CONTEXT,
			method: "GET",
			dataType: "json",
			success: function(data) {
				console.log("data: " + JSON.stringify(data));
				
				var todos = data.todos;
				
				var tabela = table.dataTable(); 
				tabela.fnClearTable(); // Reinicia a datatable
							
				$.each(todos, function(index, item) {
					var estilo = 'background-color: #E6E6FA'; // lavander 
					if (index % 2 == 0) {
						estilo = 'background-color: #ADD8E6'; // lightblue 
					}
					
					var tr = $('<tr style=\'' + estilo + '\' class=\'alinhamento-texto\'></tr>');
					
					var td = $('<td>' + item.id + '</td>');
					tr.append(td); 
					
					td = $('<td>' + item.title + '</td>');
					tr.append(td);
					
					td = $('<td>' + item.description + '</td>');
					tr.append(td);
					
					td = $('<td>' + item.fmtCreationDate + '</td>');
					tr.append(td);
					
					td = $('<td><a style=\'cursor:pointer;\' onclick=\'javascript:todoJS.remover(' + item.id + ', $("#tabela"));\'><i class=\'icon-trash\'></i></a></td>');
					tr.append(td);
					
					tabela.fnAddData(tr); // Adiciona os elementos na datatable
				});
				
				tabela.fnDraw(); // Atualiza a datatable
			}
		});
		
		this.limparFormulario();
	},
	
	remover: function(id, modal) {
		console.log(" ---- id: " + id);
		
		if (confirm("Deseja remover o item selecionado?")) {
			$.ajax({
				url: CONTEXT + id,
				method: 'DELETE',
				success: function(data) {
					console.log("data: " + JSON.stringify(data));
					todoJS.listar(modal);
				},
				error: function(errdata) {
					console.log("errdata: " + JSON.stringify(errdata.responseJSON));
					todoJS.showErro(errdata.responseJSON.msgFormValidation);
				}
			});
		}
	},
	
	adicionar: function(modal) {
		
		var metodo = 'POST';
		var contexto_ = CONTEXT;
		var param = {"title": $("input[name=titulo]").val(), "description": $("input[name=descricao]").val()};
	
		var id_ = $("input[name=id]").val();
		if (id_ !== null && id_ !== '') {
			metodo = 'PUT';
			contexto_ = contexto_ + id_;
		}
		
		$.ajax({
			url: contexto_,
			data: JSON.stringify(param),
			method: metodo,
			contentType: "application/json",
			success: function(data) {
				console.log("data: " + JSON.stringify(data));
				todoJS.limparFormulario();
				todoJS.listar(modal);
			},
			error: function(errdata) {
				console.log("errdata: " + JSON.stringify(errdata.responseJSON));
				todoJS.showErro(errdata.responseJSON.msgFormValidation);
			}
		});
	
	},
	
	showErro: function(errdata) {
		var erroMsg = '';
		
		$.each(errdata, function(index, erro) {
			erroMsg = erroMsg
				+ ' - ' + erro.id + '   ' + erro.message + '</br>';
		});
		
		this.showMessage(erroMsg, "erro");
	},
	
	hideMessage: function() {
		$("#status").html('');
		$("#status").removeClass("erro, sucesso");
		$("#status").hide();
	},
	
	showMessage: function(msg, estilo) {
		$("#status").html(msg);
		$("#status").addClass(estilo);
		$("#status").show();
	},
	
	limparFormulario: function() {
		this.hideMessage();
		$.each($(".clean"), function(index, item) {
			$(this).val("");
		});
	}
};