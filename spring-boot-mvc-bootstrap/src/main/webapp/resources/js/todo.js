var todoObj = {};
var todoObj = {
		
 recuperarEmails: function() {
	 console.log('---> Recuperar emails...');
		
	$.ajax({
	    type: 'get',
	    url: 'user/all',
	    dataType: 'json', // Define o tipo de retorno
	    contentType: 'text/html',
	    success: function(lista) {
	    	console.log('---> Lista com usuários: ' + JSON.stringify(lista));
	    		    	
	        if (lista !== undefined
	        		&& lista !== null
	        		&& lista.length > 0) {
	
	        	jsObject.removeElementsReadOnly();
	        	
	        	todoObj.criarListaEmails(lista);

	        } else {
	        	
	        	console.log('---> Nenhum e-mail encontrado');
	 
	        	$('select[name=emailSelect]').html('');
	        	
	        	var msg = 'Não existem e-mails cadastrados. Por favor, crie um usuário para associação.';
	        	
	        	jsObject.showErrorMessage(msg, $('.msg-alerta-register'));
	        	jsObject.setElementsReadOnly();

	        }
	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});
	
 },
 
 criarListaEmails: function(lista) {
	 
	 // Limpa o conteudo existente
	 $('select[name=emailSelect]').html('');
	 
	 $('select[name=emailSelect]').append('</option>')
	 
	 /** Identifica os e-mails da listagem para definir o conteúdo da listagem html **/
 	$.each(lista, function(index, usuario) {
 		console.log('---> Usuário: ' + JSON.stringify(usuario));
 		
 		var optionContent = '<option id=\'' + usuario.id + '\'>' + usuario.email + '</option>'; 
 		
 		$('select[name=emailSelect]').append(optionContent);
 	});
	 
 },
 
 recuperarListaTodos: function() {
	 console.log('---> Recuperar todos...');
		
		$.ajax({
		    type: 'get',
		    url: 'todo/all',
		    dataType: 'json', // Define o tipo de retorno
		    contentType: 'text/html',
		    success: function(lista) {
		    	console.log('---> Lista com todos: ' + JSON.stringify(lista));
		    	
		        if (lista !== undefined
		        		&& lista !== null
		        		&& lista.length > 0) {
		
		        	todoObj.exibirListaTodos(lista);
		            
		        } else {
		        	
		        	$('#show-todos').css('display', 'none');
		        	
		        }
		
		    },
		    error: function(data) {
		        console.log('---> Error: ' + JSON.stringify(data));
		    }
		});
	 
 },
 
 exibirListaTodos: function(lista) {
		
	 jsObject.cleanTableContent();
	 
	 $("#show-todos").css("display", "block");
	 
    $.each(lista, function(index, todo) {
        console.log('# Todo [' + JSON.stringify(todo) + ']');

        var totalLinhas = $('#tabletodo tr').length;
        
	   	 var css = 'odd gradeX';
	     if (totalLinhas !== 1
	    		 && totalLinhas % 2 === 0) {
			css = 'even gradeC';
		 }
        
		// Define os valores na tabela
		$("#tabletodo tbody").append(
			'<tr class=\'' + css + '\'><td>' 
		    	+ '<span class=\'label\' style=\'margin:4px;padding:4px\'><input type=\'radio\' name=\'tdid\' value=\'' + todo['id'] + '\' /></span></td>' 
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + todo['title'] + '</span></td>'
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + todo['description'].substring(0, 14) + '...' + '</span></td>'		    	
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + todo['email'] + '</span></td>'
		    	//+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + jsObject.getDate(new Date(todo['creationDate'])) + '</span></td>'		    	
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + todo['creationDate'] + '</span></td>'
		    	+ '</tr>');
    });
	
    try {
	    // Atualiza o data-table
    	$('#tabletodo').dataTable();
    	
   	 	$('#tabletodo_filter input[type=search]').addClass('form-control');
   	 	$('#tabletodo_length select[name=tabletodo_length]').addClass('form-control');
    } catch (e) {
    	console.log(' ---> Erro ao atualizar datatable: ' + e);
    }
	 
 },
 
 recuperarTodoPorId: function(id) {
	 console.log('---> Recuperar todo por id = ' + id);
		
	 var _url = 'todo/get/' + id;
	 
	 jsObject.cleanForm();
	 
	$.ajax({
	    type: 'get',
	    url: _url,
	    dataType: 'json', // Define o tipo de retorno
	    contentType: 'text/html',
	    success: function(todo) {
	    	console.log('---> Todo ' + JSON.stringify(todo));
	    	
	    	$('select[name=emailSelect]').val(todo.email);
	    	$('#creationDate').val(todo.creationDate);
	    	$('#idvaltodo').val(todo.id);
	    	$('#title').val(todo.title);
	    	$('#description').val(todo.description);
	    	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});
	 
 },
 
 removerTodoPorId: function(id) {
	 console.log('---> Remover todo por id = ' + id);
		
	 var _url = 'todo/delete/' + id;
	 
	 jsObject.cleanForm();
	 
	$.ajax({
	    type: 'get',
	    url: _url,
	    //dataType: 'json', // Define o tipo de retorno
	    contentType: 'text/html',
	    success: function(todo) {
	    	console.log('---> Todo ' + JSON.stringify(todo));
	    	
	    	var msg = 'Registro removido com sucesso';
	    	jsObject.showNotificationMessage(msg, $('.msg-sucesso'));
	    	
	    	todoObj.recuperarListaTodos();
	    	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});
	
 }
		
};