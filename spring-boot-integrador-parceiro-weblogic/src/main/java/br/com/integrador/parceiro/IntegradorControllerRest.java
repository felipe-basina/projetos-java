package br.com.integrador.parceiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegradorControllerRest {

	@RequestMapping(value = { "/", "/ping" }, method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody Retorno ping() {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		
		final Date agora = new Date();
		
		final Retorno retorno = new Retorno(System.currentTimeMillis(), 
				"Requisicao recebida em [ " + sdf.format(agora) + " ]", agora);
		
		return retorno;
	}

	@RequestMapping(value = "/sms/enviar", 
			method = RequestMethod.POST, consumes = { "application/json" },
			produces = { "application/json" })
	public @ResponseBody SmsResponse enviar(@RequestBody SmsRequest smsRequest,
			HttpServletRequest request) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		
		System.out.println("\n\nRequisicao recebida em [ " 
				+ sdf.format(new Date()) + " ] ::\n" 
				+ smsRequest);
		
		this.imprimirValoresHeader(request);
		
		SmsResponse smsResponse = null;
		
		if (!ObjectUtils.isEmpty(smsRequest)) {
			final String correlationId = smsRequest.getCorrelationId();
			
			smsResponse = new SmsResponse(correlationId);
		}
		
		return smsResponse;
	}
	
	private static final String DIRETORIO = "c:/deposito";

	@RequestMapping(value = "/recuperar/planilha", 
			method = { RequestMethod.GET, RequestMethod.POST },
			produces = { "application/zip" })
	public byte[] recuperarArquivo(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		
		System.out.println("\n #### /recuperar/planilha ");
		
		try {
			
			String url = "http://".concat("localhost")
					.concat(":").concat("7004")
					.concat("/integrador")
					.concat("/recuperar/planilha/final");
			
			final HttpPost requestPost = new HttpPost(url);
			
			final ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
			parametros.add(new BasicNameValuePair("TIPO_SIMULACAO", 
					request.getParameter("TIPO_SIMULACAO")));

			requestPost.setEntity(new UrlEncodedFormEntity(parametros));
		    
			HttpClient httpClient = HttpClients.custom().build();
			
			final CookieStore cookieStore = new BasicCookieStore();
			final HttpContext httpContext = new BasicHttpContext();
			
			httpContext.setAttribute("http.cookie-store", cookieStore);
			
			HttpResponse responseHttp = httpClient.execute(requestPost, httpContext);
			
			final Header[] header = responseHttp.getHeaders("Content-Disposition");
			String valor = header[0].getValue();
			String nomeArquivo = valor.substring(valor.indexOf("=") + 1);
			System.out.println("Arquivo recuperado do servidor remoto [ " +
					nomeArquivo + " ]");
			
			response.addHeader("Content-Disposition", "attachment; filename=" 
	        		+ nomeArquivo);
			
			final InputStream ins = responseHttp.getEntity().getContent();
			
			final byte[] bytes = IOUtils.toByteArray(ins);
		    
		    ((CloseableHttpClient) httpClient).close();			
			
		    return bytes;
		    
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return new byte[0];
	}
	
	@RequestMapping(value = "/rest/planilha/download/old", 
			method = { RequestMethod.GET, RequestMethod.POST },
			produces = { "application/zip" })
	public byte[] recuperarArquivoFinal(final HttpServletRequest request,
			final HttpServletResponse response) {
		
		try {
			
			final String id = request.getParameter("CD_CONTROLE_VERSAO_PRECO");
			
			final String tipoSimulacao = request.getParameter("TIPO_SIMULACAO");
			
			System.out.println("Content type: " + request.getContentType());
			
			System.out.println("... ID [ " + id + " ]"
					+ "\n... TIPO SIMULACAO [ " + tipoSimulacao + " ]");
			
			File planilha = null;
			
			for (File arquivo : new File(DIRETORIO).listFiles()) {
				
				if (arquivo.getName().toLowerCase().contains(tipoSimulacao.toLowerCase())) {
					planilha = arquivo;
				}
				
			}
			
			if (ObjectUtils.isEmpty(planilha)) {
				throw new Exception("Arquivo nao encontrado!");
			}
			
			System.out.println("Arquivo identificado [ " + planilha.getAbsolutePath() + " ] ");
			
			response.addHeader("Content-Disposition", "attachment; filename=" 
	        		+ planilha.getName());
			
			final InputStream is = new FileInputStream(planilha);
			System.out.println("Tamanho = " + planilha.length());
			
			final byte[] bytes = IOUtils.toByteArray(is);
			System.out.println("Total bytes recuperado [ " + bytes.length + " ]");
			
			return bytes;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return new byte[0];
	}
	
	@RequestMapping(value = "/rest/planilha/download", 
			method = { RequestMethod.GET, RequestMethod.POST },
			produces = { "application/zip" })
	public byte[] recuperarArquivoFinalAmbienteDev(final HttpServletRequest request,
			final HttpServletResponse response) {
		
		try {
			
			final String filtro = request.getParameter("FILTRO_ARQUIVO");
			
			FilenameFilter filtroArquivo = new FilenameFilter() {
				
				public boolean accept(File dir, String nomeArquivo) {
					
					if (nomeArquivo.equalsIgnoreCase(filtro)) {
						
						return true;

					} else {
						
						return false;
						
					}
					
				}

			};
			
			String diretorio = "/tmp/planilhas/";
			
			File diretorioHistorico = new File(diretorio);
			System.out.println("Diretorio historico [ " + diretorioHistorico + " ] ");
			
			File planilha = diretorioHistorico.listFiles(filtroArquivo)[0];
			
			if (ObjectUtils.isEmpty(planilha)) {
				throw new Exception("Arquivo nao encontrado!");
			}
			
			response.addHeader("Content-Disposition", "attachment; filename=" 
	        		+ planilha.getName());
			
			final byte[] bytes = IOUtils.toByteArray(new FileInputStream(planilha));
			System.out.println("# Total de bytes do arquivo para download (servlet) [ " 
					+ bytes.length + " ]");
			
			return bytes;			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
		
	}
	
	private void imprimirValoresHeader(final HttpServletRequest request) {
		if (!ObjectUtils.isEmpty(request)) {
			
			String contentType = request.getHeader(Parametros
					.CONTENT_TYPE_HEADER_PARAM.getValor());
			System.out.println("# Content-type [ " + contentType + " ]");
			
			String usuario = request.getHeader(Parametros
					.USERNAME_HEADER_PARAM.getValor());
			System.out.println("# Usuario [ " + usuario + " ]");
			
			String authenticationToken = request.getHeader(Parametros
					.AUTHENTICATION_TOKEN_HEADER_PARAM.getValor());
			System.out.println("# AuthenticationToken [ " + authenticationToken + " ]");
			
		}
	}
	
}
