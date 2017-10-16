package br.com.integrador.parceiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

public class ClienteIntegrador {

	private final HttpPost request = new HttpPost(Parametros.URI_INTEGRADOR.getValor()); 
	
	private HttpResponse response;
	
	private HttpClient httpClient;
	
	private HttpContext httpContext;
	
	private static int contador = 0;
	
	private void fecharConexao() throws IOException {
		((CloseableHttpClient) this.httpClient).close();
	}
	
	private void imprimirValoresRetorno(String retorno) {
		System.out.println("\n# Retorno " + retorno + " ::::: Iteracao [ " + contador++ + " ]");

		final StringBuilder retornoFinal = new StringBuilder();
		
		final JSONObject jsonObjectResponse = new JSONObject(retorno);
		
		@SuppressWarnings("unchecked")
		final Iterator<String> chaves = jsonObjectResponse.keys();
		
		while (chaves.hasNext()) {
			String chave = chaves.next();
			retornoFinal.append("\n..... [ ")
				.append(chave)
				.append(" ] : ")
				.append(jsonObjectResponse.get(chave));
		}

		System.out.println(retornoFinal.toString());
	}
	
	private String recuperarRetorno() throws UnsupportedOperationException, IOException {
		final InputStream ins = this.response.getEntity().getContent();
		final InputStreamReader isr = new InputStreamReader(ins);
		final BufferedReader in = new BufferedReader(isr);

		final StringBuilder resposta = new StringBuilder();

		String inputLinha;
		
		while ((inputLinha = in.readLine()) != null) {
			resposta.append(inputLinha);
		}
		
		in.close();
		
		return resposta.toString();
	}
	
	private void enviarMensagem() throws ClientProtocolException, IOException {
		if (ObjectUtils.isEmpty(this.httpClient)) {

			final Header contentTypeHeader = new BasicHeader(Parametros.CONTENT_TYPE_HEADER_PARAM.getValor(), 
					Parametros.CONTENT_TYPE_HEADER_VALUE.getValor());
			
			final Header acceptHeader = new BasicHeader(Parametros.ACCEPT_HEADER_PARAM.getValor(), 
					Parametros.ACCEPT_HEADER_VALUE.getValor());
			
			final Header userNameHeader = new BasicHeader(Parametros.USERNAME_HEADER_PARAM.getValor(), 
					Parametros.USERNAME_HEADER_VALUE.getValor());
			
			final Header tokenHeader = new BasicHeader(Parametros.AUTHENTICATION_TOKEN_HEADER_PARAM.getValor(), 
					Parametros.AUTHENTICATION_TOKEN_HEADER_VALUE.getValor());			
			
			final List<Header> padraoHeaders = Lists.newArrayList(contentTypeHeader, 
					acceptHeader, userNameHeader, tokenHeader);
			
			this.httpClient = HttpClients.custom().setDefaultHeaders(padraoHeaders).build();
			
			final CookieStore cookieStore = new BasicCookieStore();
			
			this.httpContext = new BasicHttpContext();
			this.httpContext.setAttribute("http.cookie-store", cookieStore);

		}
		
		this.response = this.httpClient.execute(this.request, this.httpContext);
	}
	
	private String codificarUtf8(String valor) throws UnsupportedEncodingException {
		return URLEncoder.encode(valor, "UTF-8");
	}
	
	// {"destination": "11973008408", "messageText": "Uma mensagem para teste", "correlationId": "1234567890000"}
	private void definirCorpoMensagem() throws UnsupportedEncodingException {
		JSONObject jsonObjectRequest = new JSONObject();
		jsonObjectRequest.put("destination", this.codificarUtf8("11973008408"));
		jsonObjectRequest.put("messageText", this.codificarUtf8("Uma mensagem para teste para avião"));
		jsonObjectRequest.put("correlationId", this.codificarUtf8(String.valueOf(System.currentTimeMillis())));
		
		this.request.setEntity(new StringEntity(jsonObjectRequest.toString()));		
	}
	
	private void processar() {
		try {
			
			for (int indice = 0; indice < 5; indice++) {
			
				this.definirCorpoMensagem();
	
				this.enviarMensagem();

				String retorno = this.recuperarRetorno();
				
				this.imprimirValoresRetorno(retorno);
				
				Thread.sleep(3000l); // Aguarda 3 segundos

			}

			this.fecharConexao();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		new ClienteIntegrador().processar();
	}
	
}
