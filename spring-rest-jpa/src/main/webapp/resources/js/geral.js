var listaSessao;

function listar(lista) {

    iniciarElementos();

    listaSessao = lista;
    
    $("#div-tbl").css("display", "block");

    $.each(lista, function(index, usuario) {
        console.log('# Usuario [' + JSON.stringify(usuario) + ']');

        $("#tbl tr:last").after(
            '<tr><td>' 
        		+ '<span class=\'label\' style=\'margin:4px;padding:4px\'><input type=\'radio\' name=\'usrid\' value=\'' + usuario.id + '\' /></span>' + '</td>' 
        		+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario.firstName + '</span></td>' 
        		+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario.lastName + '</span></td>' 
        		+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario.email + '</span></td>' 
        		+ '</tr>');
    });

}

function iniciarElementos() {

    limparConteudoMensagem();

    limparListaUsuarios();

    $("#div-form, .criar").css("display", "none");
    
    // Para cada campo de texto, remove o conteúdo
    $("input[type=text], input[type=hidden]").each(function() {
        $(this).val('');
        $(this).removeClass('error');
    });

    // Desmarca botão radio
    $("input[name=usrid]").each(function() {
        $(this).prop('checked', false);
    });
    
    // Remove classe específica
    $("label[class=error]").each(function() {
        $(this).remove();
    });
}

function limparConteudoMensagem() {

    if ($(".msg-alerta").css('display') === 'block') {
        $(".msg-alerta").css('display', 'none').html('');
    }

}

function removerUsuario(id) {
	
	console.log('---> Removendo usuario id... ' + id);
	
	var param = {'id': id};
	
    $.ajax({
        type: 'post',
        url: 'employee/remove',
        dataType: 'json', // Define o tipo de retorno
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function(lista) {
        	        	
            listar(lista);

            $("#div-form").css("display", "none");
        	
        },
        error: function(data) {
            console.log('---> Error: ' + JSON.stringify(data));
        }
    });
}

function atualizarUsuario(id) {
		
	console.log('---> Atualizando usuario id... ' + id);
	
	var param = {'id': id};
	
    $.ajax({
        type: 'post',
        url: 'employee/get',
        dataType: 'json', // Define o tipo de retorno
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function(usuario) {

            if (usuario !== undefined
            		&& usuario !== null) {

            	$("#div-form, .criar").css("display", "block");
            	
            	// Define as informações
            	$('#idval').val(usuario.id);
                $('#nome').val(usuario.firstName);
                $('#sobrenome').val(usuario.lastName);
                $('#email').val(usuario.email);
            	
            } else {
            	
                var msg = "Não foi possível recuperar usuário [" + id + "]";
                $(".msg-alerta").css('display', 'block')
                    	.append(decodeString(msg));

            }

        },
        error: function(data) {
            console.log('---> Error: ' + JSON.stringify(data));
        }
    });
	
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

function decodeString(string) {
	try {
		return decodeURIComponent(escape(string));	
	} catch (e) {
		console.log(' ---> Erro ao decodificar string [' + string + ']\n....: ' + e);
	}
	
	return string;
}

$(function() {
	
    $("#div-form").validate({
        rules: {
            nome: {
                required: true,
                minlength: 3
            },
            sobrenome: {
                required: true,
                minlength: 4
            },
            email: {
            	required: true,
            	email: true
            }            
        },
        messages: {
            nome: {
                required: decodeString(' *Nome é um campo obrigatório'),
                minlength: jQuery.validator.format(decodeString(" Por favor, entre com pelo menos {0} caracteres"))
            },
            sobrenome: {
                required: decodeString(' *Sobrenome é um campo obrigatório'),
                minlength: jQuery.validator.format(decodeString(" Por favor, entre com pelo menos {0} caracteres"))
            },
            email: {
            	required: decodeString(' *E-mail inválido'),
            	email: decodeString(" Por favor, entre com um e-mail válido. Ex: apelido@dominio.com")
            }
        },        
        submitHandler: function(form) {
            var data;
            
            if ($('#idval').val() !== null
            		|| $('#idval').val() !== '') {

    			data = {
    					'id': $('#idval').val(),
    					'firstName': $('#nome').val(),
                        'lastName': $('#sobrenome').val(),
                        'email': $('#email').val()
    			};
            	
            } else {
            
    			data = {
    					'firstName': $('#nome').val(),
                        'lastName': $('#sobrenome').val(),
                        'email': $('#email').val()
    			};
            	
            }
        	
            $.ajax({
                type: 'post',
                url: 'employee/save',
                dataType: 'json', // Define o tipo de retorno
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(lista) {
                	
                	listar(lista);
                	
                    $("#div-form").css("display", "none");

                },
                error: function(data) {
                    console.log('---> Error: ' + JSON.stringify(data));
                }
            });
        }
    });

    $('#recuperar, #limpar, #criar, #atualizar, #remover')
        .on(
            "click",
            function() {
					
                if ($(this).val() === decodeString('Recuperar usuários')) {
                    console.log('---> Preparando para recuperar usuários...');

                    $.ajax({
                            type: 'get',
                            url: 'employee/all',
                            dataType: 'json', // Define o tipo de retorno
                            success: function(lista) {
                                console
                                    .log('---> Retorno: ' + JSON
                                        .stringify(lista));

                                if (lista !== null) {
                                	
                                    listar(lista);

                                } else {
                                    var msg = "Não foi possível recuperar usuários!";
                                    $(".msg-alerta").css(
                                            'display', 'block')
                                        .append(decodeString(msg));
                                }

                            },
                            error: function(data) {
                                console.log('---> Error: ' + JSON.stringify(data));
                            }
                        });

                } else if ($(this).val() === decodeString('Limpar conteúdo')) {

                    iniciarElementos();

                } else if ($(this).val() === decodeString('Criar usuário')) {

                	iniciarElementos();
                	
                    $("#div-form, .criar").css("display", "block");

                } else if ($(this).val() === decodeString('Atualizar usuário')) {

                	var id = $('input[name=usrid]:checked').val();

                	iniciarElementos();
                	
					if (id === undefined
							|| id === null
							|| id < 0) {
						
						var msg = "Nenhum usuário selecionado para atualização!";
                        $(".msg-alerta").css(
                                'display', 'block')
                            .append(decodeString(msg));
						
					} else {
						atualizarUsuario(id);
					}

                } else if ($(this).val() === decodeString('Remover usuário')) {

                	var id = $('input[name=usrid]:checked').val();
                	
                	iniciarElementos();
                	
					if (id === undefined
							|| id === null
							|| id < 0) {
						
						var msg = "Nenhum usuário selecionado para remoção!";
                        $(".msg-alerta").css(
                                'display', 'block')
                            .append(decodeString(msg));
						
					} else {

						
						$('<div></div>')
								.appendTo('body')
								.html(
										'<div><h6>Deseja remover o registro?</h6></div>')
								.dialog({
									modal : true,
									title : decodeString('Confirmação da operação'),
									zIndex : 10000,
									autoOpen : true,
									width : 'auto',
									resizable : false,
									buttons : {
										sim : function() {
											
											removerUsuario(id);
											
											$(this).dialog("close");
											
										},
										'nao' : function() {
																						
											$(this).dialog("close");
																	                	
						                	listar(listaSessao);

										}
									}
							});
					}             	
                    
                } else {
                    console.log(' # Operacao nao identificada!');
                }

            });
});