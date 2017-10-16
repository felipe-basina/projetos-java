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
		seja apresentada a confirma��o de acesso �s
		informa��es armazenadas no Facebook
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
	// Necess�rio o c�digo da aplica��o registrada no Facebook, no caso de testes
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
		// Ver documenta��o para FB.getLoginStatus() 
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
		
		// Objeto resposta retorna o status da opera��o de autentica��o
		if (response.status === 'connected') { // Autentica��o realizada com sucesso
			recuperarInfomacoes(); 
		} else if (response.status === 'not_authorized') {
			// O usu�rio se autenticou pelo Facebook
			// mas a aplica��o n�o tem permiss�o para acess�-lo
			document.getElementById('status').innerHTML = 'A aplica��o '
					+ 'n�o est� autorizada para acessar as informa��es do usu�rio!';
		} else {
			// O usu�rio n�o se autenticou pelo Facebook
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
						console.log('--- Autentica��o: ' + JSON.stringify(response));
						
						var mensagem = 'Obrigado por vir, ' + response.name;
						
						if (response.email === undefined
								|| response.email === '') {
							mensagem += ', por�m, seu email n�o foi encontrado';
							document.getElementById('sairfb').style.display = 'block';
							document.getElementById('entrarfb').style.display = 'none';
						} else {
							mensagem += ', seu email cadastrado � o ' + response.email;
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