var userObj = {};
var userObj = {
		
 recuperarUsuarios: function() {
	 console.log('---> Recuperar usuários...');
		
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
	
	        	userObj.criarListaUsuarios(lista);
	            
	        } else {
	        	
	        	$('#show-users').css('display', 'none');
	        	
	        }
	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});
	
 },
 
 criarListaUsuarios: function(lista) {
		
	 jsObject.cleanTableContent();
	 
	 $("#show-users").css("display", "block");
	 
    $.each(lista, function(index, usuario) {
        console.log('# Usuario [' + JSON.stringify(usuario) + ']');

        var totalLinhas = $('#example tr').length;
        
	   	 var css = 'odd gradeX';
	     if (totalLinhas !== 1
	    		 && totalLinhas % 2 === 0) {
			css = 'even gradeC';
		 }
        
		// Define os valores na tabela
		$("#example tbody").append(
			'<tr class=\'' + css + '\'><td>' 
		    	+ '<span class=\'label\' style=\'margin:4px;padding:4px\'><input type=\'radio\' name=\'usrid\' value=\'' + usuario['id'] + '\' /></span></td>' 
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario['name'] + '</span></td>' 
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario['lastName'] + '</span></td>' 
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + usuario['email'] + '</span></td>'
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + jsObject.getDate(new Date(usuario['creationDate'])) + '</span></td>'
		    	+ '<td><span class=\'label label-info\' style=\'margin:4px;padding:4px\'>' + jsObject.getDate(new Date(usuario['updateDate'])) + '</span></td>'
		    	+ '</tr>');
    });
	
    try {
	    // Atualiza o data-table
    	$('#example').dataTable();
    	
   	 	$('#example_filter input[type=search]').addClass('form-control');
   	 	$('#example_length select[name=example_length]').addClass('form-control');
    } catch (e) {
    	console.log(' ---> Erro ao atualizar datatable: ' + e);
    }
    
 },
 
 recuperarUsuario: function(id) {
		
	console.log('---> Recuperando usuario id... ' + id);
	
	var _url = 'user/get/' + id;
	
	$.ajax({
	    type: 'get',
	    url: _url,
	    dataType: 'json', // Define o tipo de retorno
	    contentType: 'text/html',
	    success: function(usuario) {
	    	console.log('---> usuario: ' + JSON.stringify(usuario));
	    	
	        if (usuario !== undefined
	        		&& usuario !== null) {
	        	            	
	        	// Define as informações
	        	$('#idval').val(usuario.id);
	            $('#name').val(usuario.name);
	            $('#lastName').val(usuario.lastName);
	            $('#email').val(usuario.email);
	        	
	            // Desmarca a opção radio selecionada 
	            jsObject.cleanRadioButtons();
	            
	        } else {
	        	
	            var msg = "Não foi possível recuperar usuário [" + id + "]";
	            jsObject.showErrorMessage(msg, $('.msg-alerta'));
	
	        }
	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});

 },

 removerUsuario: function(id) {
			
	console.log('---> Removendo usuario id... ' + id);
	
	jsObject.cleanAll();
	
	var _url = 'user/delete/' + id;
	
	$.ajax({
	    type: 'get',
	    url: _url,
	    dataType: 'json', // Define o tipo de retorno
	    contentType: 'text/html',
	    success: function(lista) {
	    	console.log('---> Lista com usuários: ' + JSON.stringify(lista));
	    	
	        var msg = 'Usuário removido com sucesso!';
	        jsObject.showNotificationMessage(msg, $('.msg-sucesso'));
	        
	        if (lista !== undefined
	        		&& lista !== null
	        		&& lista.length > 0) {    	

	        	userObj.criarListaUsuarios(lista);
	        	
	            // Desmarca a opção radio selecionada 
	            jsObject.cleanRadioButtons();
	            
	        } else {
	        	
	        	$('#show-users').css('display', 'none');
	        	
	        }
	
	    },
	    error: function(data) {
	        console.log('---> Error: ' + JSON.stringify(data));
	    }
	});

 }
		
};