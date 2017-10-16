<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>autenticando...</title>
</head>
<body>

	<!-- 
		Definir o atributo 'data-scope' para que
		seja apresentada a confirmação de acesso às
		informações armazenadas no Facebook
	 -->
	<p>Conectar utilizando o Facebook</p>
	
	<div id="entrarfb" style="display: block;">
		<div class="fb-login-button" data-max-rows="1" data-size="medium"
			data-show-faces="false" data-auto-logout-link="true"
			onlogin="checkLoginState();" data-scope="email,basic_info"></div>
		<!-- public_profile -->
	</div>

	<div id="sairfb" style="display: none;">
		<span id="fbLogout" onclick="fbLogout();"><a
			class="fb_button fb_button_medium" style="cursor: pointer;"><span class="fb_button_text">Logout</span></a></span>
	</div>

	<p></p>
	<div id="status"></div>
	
</body>
<div id="fb-root"></div>
<script>

	// Carrega a API do Facebook
	// Necessário o código da aplicação registrada no Facebook, no caso de testes
	// appId = 191901167897917
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.7&appId=191901167897917";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	function checkLoginState() {
		// Ver documentação para FB.getLoginStatus() 
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}

	function fbLogout() {
		console.log('--- Logout');
        FB.logout(function (response) {
        	window.location.reload();
        });
    }
	
	function statusChangeCallback(response) {
		console.log('--- statusChangeCallback: ' + JSON.stringify(response));
		
		// Objeto resposta retorna o status da operação de autenticação
		if (response.status === 'connected') { // Autenticação realizada com sucesso
			recuperarInfomacoes(); 
		} else if (response.status === 'not_authorized') {
			// O usuário se autenticou pelo Facebook
			// mas a aplicação não tem permissão para acessá-lo
			document.getElementById('status').innerHTML = 'A aplicação '
					+ 'não está autorizada para acessar as informações do usuário!';
		} else {
			// O usuário não se autenticou pelo Facebook
			document.getElementById('status').innerHTML = 'Por favor '
					+ 'se autentique pelo Facebook.';
		}
	}

	function recuperarInfomacoes() {
		console.log('--- Recuperando informacoes.... ');
		try {
			// Campos a serem recuperados no Facebook
			FB.api('/me',
					{ fields : [ "id", "birthday", "first_name",
										"gender", "last_name", "name", "email" ] },
					function(response) {
						console.log('--- Autenticação: ' + JSON.stringify(response));
						
						var mensagem = 'Obrigado por vir, ' + response.name;
						
						if (response.email === undefined
								|| response.email === '') {
							mensagem += ', porém, seu email não foi encontrado';
							document.getElementById('sairfb').style.display = 'block';
							document.getElementById('entrarfb').style.display = 'none';
						} else {
							mensagem += ', seu email cadastrado é o ' + response.email;
							document.getElementById('sairfb').style.display = 'none';
							document.getElementById('entrarfb').style.display = 'block';
						}
						
						document.getElementById('status').innerHTML = mensagem;
					});
			
		} catch (e) {
			console.log('exception: ' + e);
		}
	}

</script>
</html>