/**
	http://getbootstrap.com/css/?#forms-horizontal
**/

/**---------------------------------------- Inicializacao **/
$(function() {
	
	// Inicia
	jsObject.init();
	
	// Data-table
	$('#example').dataTable({
		"bPaginate": true,
		"bJQueryUI": false,
		"bDestroy": true,
		"sPaginationType": "full_numbers"
	});
	
	$('#tabletodo').dataTable({
		"bPaginate": true,
		"bJQueryUI": false,
		"bDestroy": true,
		"sPaginationType": "full_numbers"
	});
	
	// Menu lateral
	$("#menu-toggle, #menu-toggle2, #menu-toggle3, #menu-toggle4").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

	// Campos de data
	 $('#creationDate').datepicker({
		dateFormat: 'dd/mm/yy',
		altField: '#creationDate',
		beforeShow: function(input, inst) { // Exibe o calendario ao lado do campo
			inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'});
		}
	 });
});
/**---------------------------------------- Inicializacao **/

function decodeString(string) {
	try {
		return decodeURIComponent(escape(string));	
	} catch (e) {
		console.log(' ---> Erro ao decodificar string [' + string + ']\n....: ' + e);
	}
	
	return string;
}

var jsObject = {};
var jsObject = {

	getElementId: function(element) {
		var id = element.attr('id');
		id = id.substring(0, id.indexOf('-'));
		var element = $('#' + id);
		return element;
	},
	
	hideAllDivContent: function() {
		$('.sidebar-nav').each(function() {
			$(this).find('li a').each(function() {
				var id = $(this).attr('id');
				id = id.substring(0, id.indexOf('-'));
				var element = $('#' + id);
				element.hide();
			});
		});
	},
	
	showDivContent: function(element) {
		this.hideAllDivContent();
		this.cleanAll();

		// Recupera informações adicionais caso necessário
		jsObject.getAdditionalData(element);
		
		var _url = element.attr('id');
		console.log('---> Preparando para invocar: ' + _url);
		
		// Executa a requisição ajax no servidor
		$.ajax({
            type: 'get',
            url: _url,
            success: function(data) {
                console.log('---> Retorno: ' + JSON.stringify(data));

                element.show();

            },
            error: function(data) {
                console.log('---> Error: ' + JSON.stringify(data));
            }
        });
	},
	
	getAdditionalData: function(element) {
		console.log('---> Recuperando informações adicionais para item: ' + element.attr('id'));

		if (element.attr('id') === 'usersave') {
			
			userObj.recuperarUsuarios();
		
		} else if (element.attr('id') === 'todosave') {
			
			todoObj.recuperarEmails();
			todoObj.recuperarListaTodos();

		} 
		
	},
	
	init: function() {
		var element = jsObject.getElementId($('#main-menu-item'));
		jsObject.showDivContent(element);	
	},
	
	cleanInputFields: function() {
		$('input[type=text]').each(function() {
			var input = $(this).attr('id');
			$('#' + input).val('');
		});
		
		$('textarea').each(function() {
			var input = $(this).attr('id');
			$('#' + input).val('');
		});
		
		this.cleanIdField();
	},
	
	cleanRadioButtons: function() {
	    // Desmarca botão radio
	    $("input[name=usrid], input[name=tdid]").each(function() {
	        $(this).prop('checked', false);
	    });
	},
	
	cleanErrorMessages: function() {
		$('label[class=error]').each(function() {
			var label = $(this).attr('id');
			$('#' + label).css('display', 'none');
		});
		
		$('.msg-alerta-register, .msg-alerta').css('display', 'none').html('');
	},
	
	cleanNotificationMessages: function() {	
		$('.msg-sucesso').css('display', 'none').html('');
	},
	
	cleanTableContent: function() {
		$('#example').dataTable().fnDestroy();
		$('#tabletodo').dataTable().fnDestroy();
		$("#example tbody, #tabletodo tbody").html('');
		$("#show-users, #show-todos").css('display', 'none');
	},
	
	cleanForm: function() {
		this.cleanInputFields();
		this.cleanRadioButtons();
		this.cleanErrorMessages();	
		this.cleanIdField();
		this.cleanNotificationMessages();
		this.removeElementsReadOnly();
	},
	
	cleanIdField: function() {
		$('input[name=id]').val('');
		this.removeElementsReadOnly();
	},
	
	cleanAll: function() {
		this.cleanInputFields();
		this.cleanRadioButtons();
		this.cleanErrorMessages();
		this.cleanNotificationMessages();
		this.cleanTableContent();
		this.cleanIdField();
		this.removeElementsReadOnly();
	},

	getDate: function(dateTime) {
		var day = dateTime.getDate();
		var month = (dateTime.getMonth() + 1);
		var year = dateTime.getFullYear();
		
		return (day + '/' + month + '/' + year);
	},
	
	showErrorMessage: function(msg, element) {
		this.cleanErrorMessages();
		
		element.css('display', 'block')
			.append('</br>')
            .append(decodeString(msg))
            .append('</br></br>');
	},
	
	showNotificationMessage: function(msg, element) {
		this.cleanNotificationMessages();
		
		element.css('display', 'block')
			.append('</br>')
			.append(decodeString(msg))
			.append('</br></br>');
	},
	
	setInputFieldsReadOnly: function() {
		
		$('input[type=text], textarea').each(function() {
			$(this).prop('readonly', true);
		});

	},
	
	removeInputFieldsReadOnly: function() {
		
		$('input[type=text], textarea').each(function() {
			$(this).prop('readonly', false);
		});

	},
	
	setButtonDisabled: function() {
		
		$('.btn').each(function() {
			$(this).prop('disabled', true);
		});

	},
	
	removeButtonDisabled: function() {
		
		$('.btn').each(function() {
			$(this).prop('disabled', false);
		});

	},
	
	setElementsReadOnly: function() {
		this.setInputFieldsReadOnly();
		this.setButtonDisabled();
	},
	
	removeElementsReadOnly: function() {
		this.removeInputFieldsReadOnly();
		this.removeButtonDisabled();
	}
	
};

// Botao para teste
$(".action").on("click", function() {
	alert("Clicou no botao!");
});

// Controle para exibir o item do menu selecionado
$('.menu-item').on('click', function() {
	var element = jsObject.getElementId($(this));
	jsObject.showDivContent(element);
});

$('#select-todo-delete, #select-todo-update').on('click', function() {
	var id = $('input[name=tdid]:checked').val();
	console.log('---> id: ' + id + ' --- operação: ' + $(this).attr('id'));
		
	if (id === undefined
			|| id === null
			|| id < 0) {

		jsObject.cleanErrorMessages();
		
		var msg = "Nenhuma tarefa selecionada!";
        jsObject.showErrorMessage(msg, $('.msg-alerta'));
		
	} else {

		jsObject.cleanErrorMessages();
		
		var valId = $(this).attr('id');
		
		if (valId === 'select-todo-update') {
			
			todoObj.recuperarTodoPorId(id);
			
		} else if (valId === 'select-todo-delete') {
			
			$('<div></div>')
				.appendTo('body')
				.html('<div><h6>Deseja remover o registro?</h6></div>')
				.dialog({
					modal : true,
					title : decodeString('Confirma?'),
					zIndex : 10000,
					autoOpen : true,
					width : 'auto',
					resizable : false,
					buttons : {
						sim : function() {
							
							todoObj.removerTodoPorId(id);
							
							$(this).dialog("close");
							
						},
						'nao' : function() {
																		
							$(this).dialog("close");
													                	
		                	todoObj.recuperarListaTodos();
	
						}
					}
			});
			
			// Adiciona o estilo para botões do dialog
			$('.ui-dialog-buttonset button').addClass('btn btn-primary');
		}

	}
	
});

$('#select-user-delete, #select-user-update').on('click', function() {
	var id = $('input[name=usrid]:checked').val();
	console.log('---> id: ' + id + ' --- operação: ' + $(this).attr('id'));
		
	if (id === undefined
			|| id === null
			|| id < 0) {

		jsObject.cleanErrorMessages();
		
		var msg = "Nenhum usuário selecionado!";
        jsObject.showErrorMessage(msg, $('.msg-alerta'));
		
	} else {

		jsObject.cleanErrorMessages();
		
		var valId = $(this).attr('id');
		
		if (valId === 'select-user-update') {
			
			userObj.recuperarUsuario(id);
			
		} else if (valId === 'select-user-delete') {
			
			$('<div></div>')
				.appendTo('body')
				.html('<div><h6>Deseja remover o registro?</h6></div>')
				.dialog({
					modal : true,
					title : decodeString('Confirma?'),
					zIndex : 10000,
					autoOpen : true,
					width : 'auto',
					resizable : false,
					buttons : {
						sim : function() {
							
							userObj.removerUsuario(id);
							
							$(this).dialog("close");
							
						},
						'nao' : function() {
																		
							$(this).dialog("close");
													                	
		                	userObj.recuperarUsuarios();
	
						}
					}
			});
			
			// Adiciona o estilo para botões do dialog
			$('.ui-dialog-buttonset button').addClass('btn btn-primary');
		}

	}
	
});

// Botao para limpeza de campos de formularios
$('.clean').on('click', function() {
	jsObject.cleanForm();
});

// Funcao utilizada para permitir a mascara de entrada no formato dd/mm/yy
$.validator.addMethod('date',
    function (value, element) {
        if (this.optional(element)) {
            return true;
        }
        var ok = true;
        try {
            $.datepicker.parseDate('dd/mm/yy', value);
        }
        catch (err) {
            ok = false;
        }
        return ok;
});

// Regras de validacao do formulario
$("#dataTodoform").validate({
	rules: {
		title: {
			required: true,
			minlength: 3,       
		},
		creationDate: {   
			required: true,
			date: true,
			dpDate: false
		},
		description: {   
			required: true
		},
		emailSelect: {   
			required: true
		}
	},
	messages: {
		title: {
			  required: "Please put a title",
			  minlength: jQuery.validator.format("At least {0} characters.")
		},
		creationDate: {
			  required:"Please set a date"
		},
		description: {
			  required:"Please put a description"
		},
		emailSelect: {
			  required:"Choose an e-mail address from list"
		}
	},
	submitHandler: function(form) {
        var data;
                
        var idValTodo = $('#idvaltodo').val();
        
        if (idValTodo !== undefined
        		&& idValTodo !== null) {

    		data = {
    				'id': idValTodo,
    				'creationDate': $('#creationDate').val(),
                    'title': $('#title').val(),
                    'description': $('#description').val(),
                    'emailId': $('#emailSelect').children(':selected').attr('id')
    			};
        	
        } else {
 
    		data = {
    				'creationDate': $('#creationDate').val(),
                    'title': $('#title').val(),
                    'description': $('#description').val(),
                    'emailId': $('#emailSelect').children(':selected').attr('id')
    			};
        	
        }
     	
		console.log('---> data stringify: ' + JSON.stringify(data));		
		
        $.ajax({
            type: 'post',
            url: 'todo/save',
            dataType: 'json', // Define o tipo de retorno
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(lista) {
            	console.log(' ---> Retorno [submit]: ' + JSON.stringify(lista));
            	
            	jsObject.cleanInputFields();
            	
            	todoObj.exibirListaTodos(lista);
            },
            error: function(data) {
                console.log('---> Error: ' + JSON.stringify(data));
            }
        });
	}
});

/*-------------------------- Validação formulário usuário */
//Regras para validacao do formulario
$("#dataUserform").validate({
	rules: {
		name: {
			required: true,
			minlength: 3,       
		},
		lastName: {   
			required: true,
			minlength: 4,
		},
		email: {   
			required: true,
			email: true
		}
	},
	messages: {
		name: {
			  required: "Please enter with a name",
			  minlength: jQuery.validator.format("At least {0} characters.")
		},
		lastName: {
			  required:"Please enter with a surname",
			  minlength: jQuery.validator.format("At least {0} characters.")
		},
		email: {
			  required:"Please enter with a valid e-mail",
			  
		}
	},
	submitHandler: function(form) {
		var data;
     
     	console.log(' ---> Cadastro de usuário...');
     
        if ($('#idval').val() !== null
        		&& $('#idval').val() !== '') {

			data = {
					'id': $('#idval').val(),
					'name': $('#name').val(),
					'lastName': $('#lastName').val(),
					'email': $('#email').val()
				};
        	
        } else {
     	
			data = {
					'name': $('#name').val(),
					'lastName': $('#lastName').val(),
					'email': $('#email').val()
				};
			
        }
 	
     $.ajax({
         type: 'post',
         url: 'user/save',
         dataType: 'json', // Define o tipo de retorno
         contentType: 'application/json',
         data: JSON.stringify(data),
         success: function(lista) {
        	 console.log(' ---> Retorno [submit]: ' + JSON.stringify(lista));

        	 jsObject.cleanErrorMessages();
        	 
             userObj.criarListaUsuarios(lista);
             
         	jsObject.cleanInputFields();
         	jsObject.cleanRadioButtons();
         	jsObject.cleanNotificationMessages();
         },
         error: function(data) {
             console.log('---> Error: ' + JSON.stringify(data));
             
             jsObject.cleanErrorMessages();
             jsObject.cleanNotificationMessages();
             
             var msg = data['responseJSON'].message;
             jsObject.showErrorMessage(msg, $('.msg-alerta-register'));
         }
     });
	}
});
/*-------------------------- Validação formulário usuário */