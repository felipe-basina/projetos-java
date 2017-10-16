<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>autenticando...</title>
</head>
<body>

	<p>Conectar utilizando o Facebook</p>
	<div class="fb-login-button" data-max-rows="1" data-size="medium"
		data-show-faces="false" data-auto-logout-link="true"
		onlogin="checkLoginState();"></div>
	
	<p></p>
	<div id="status"></div>
</body>
<div id="fb-root"></div>
<script>
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
		FB.getLoginStatus(function(response) {
			console.log('response-> ' + JSON.stringify(response));
			statusChangeCallback(response);
		});
	}

	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(JSON.stringify(response));
		// The response object is returned with a status field that lets the
		// app know the current login status of the person.
		// Full docs on the response object can be found in the documentation
		// for FB.getLoginStatus().
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			testAPI();
		} else if (response.status === 'not_authorized') {
			// The person is logged into Facebook, but not your app.
			document.getElementById('status').innerHTML = 'Por favor '
					+ 'se autentique na aplicação.';
		} else {
			// The person is not logged into Facebook, so we're not sure if
			// they are logged into this app or not.
			document.getElementById('status').innerHTML = 'Por favor '
					+ 'se autentique pelo Facebook.';
		}
	}

	function testAPI() {
		console.log('Bem-vindo! Recuperando informacoes.... ');
		FB
				.api(
						'/me', {fields: ["id", "birthday", "first_name", "gender", "last_name", "name", "email"]},
						function(response) {
							console.log('---' + JSON.stringify(response));
							console.log('Autenticacao realizada com sucesso: '
									+ response.name);
							document.getElementById('status').innerHTML = 'Obrigado por vir, '
									+ response.name + '!';
						});
	}
</script>
</html>