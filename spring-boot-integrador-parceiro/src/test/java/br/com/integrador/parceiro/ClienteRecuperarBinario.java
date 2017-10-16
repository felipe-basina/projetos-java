package br.com.integrador.parceiro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

// http://localhost:7004/integrador/recuperar/planilha?CD_CONTROLE_VERSAO_PRECO=11&TIPO_SIMULACAO=NET_CLARO

public class ClienteRecuperarBinario {

	private static final String DIRETORIO_DESTINO = "c:/deposito/download/";
	
	private static final String URL = "http://localhost:7004/integrador/recuperar/planilha";
	
	public static void main(String[] args) {

		try {
			
			final String[] simulacaoTipos = new String[] { "NET_CLARO", "CLAROTV" };
			
			for (String tipoSimulacao : simulacaoTipos) {

				final HttpPost request = new HttpPost(URL);
				
				final ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
				parametros.add(new BasicNameValuePair("CD_CONTROLE_VERSAO_PRECO", "11l"));
			    parametros.add(new BasicNameValuePair("TIPO_SIMULACAO", tipoSimulacao));

			    request.setEntity(new UrlEncodedFormEntity(parametros));
			    
				HttpClient httpClient = HttpClients.custom().build();
				
				final CookieStore cookieStore = new BasicCookieStore();
				final HttpContext httpContext = new BasicHttpContext();
				
				httpContext.setAttribute("http.cookie-store", cookieStore);
				
				HttpResponse response = httpClient.execute(request, httpContext);
				Header[] header = response.getHeaders("Content-Disposition");
				String valor = header[0].getValue();
				String nomeArquivo = valor.substring(valor.indexOf("=") + 1);
				
				final InputStream ins = response.getEntity().getContent();
				System.out.println("Content length [ " + response.getEntity().getContentLength() + " ]");
				
				final byte[] bytes = IOUtils.toByteArray(ins);
				System.out.println("Total de bytes recebidos [ " + bytes.length + " ]");
				
				File targetFile = new File(DIRETORIO_DESTINO + nomeArquivo);
			    OutputStream outStream = new FileOutputStream(targetFile);
			    outStream.write(bytes);
			    
			    outStream.close();

			    System.out.println("Arquivo transferido com sucesso!");
			    
			    ((CloseableHttpClient) httpClient).close();
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.exit(1);
		}

	}

}
