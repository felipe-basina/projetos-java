package br.com.spring.framework.web.security.controller;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.security.ws.MensagemWSRequest;
import br.com.spring.framework.web.security.ws.MensagemWSResponse;
import br.com.spring.framework.web.security.ws.client.MensagemWSCliente;
import br.com.spring.framework.web.security.ws.client.ParametroInvalidoBusinessException;

@Controller
public class TabsController {

	@RequestMapping("/tabs")
	public ModelAndView getTabs() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("command", new MensagemWSRequest());

		return new ModelAndView("tabs", map);
	}

	@RequestMapping(value = "/clientews")
	public ModelAndView getMessage(
			@Valid @ModelAttribute("command") MensagemWSRequest request,
			BindingResult result, HttpServletRequest servletRequest) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("command", request);

		if (result.hasErrors()) {
			map.put("mensagem", "Preencha todos os campos obrigat�rios!");
			return new ModelAndView("tabs", map);
		}

		MensagemWSResponse response = null;

		try {

			System.out.println("\n ##### REQUEST: " + request);

			String protocol = servletRequest.getProtocol();
			String server = servletRequest.getServerName();
			int port = servletRequest.getServerPort();
			String contextRoot = servletRequest.getContextPath();

			String domain = protocol.substring(0, protocol.indexOf("/"))
					.concat("://").concat(server).concat(":")
					.concat(String.valueOf(port)).concat(contextRoot);
			System.out.println("\n\n domain: " + domain + "\n\n");

			MensagemWSCliente ws = new MensagemWSCliente();
			response = ws.invokeWS(request, domain);

			System.out.println("\n ##### RESPONSE: " + response);

			map.put("command", new MensagemWSRequest());
			map.put("response", response);

		} catch (ParametroInvalidoBusinessException e) {
			System.out.println(this.logException(e));
			map.put("mensagem", e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println(this.logException(e));
			map.put("mensagem", e.getMessage());
		} catch (ServiceException e) {
			System.out.println(this.logException(e));
			map.put("mensagem", e.getMessage());
		} catch (RemoteException e) {
			System.out.println(this.logException(e));
			map.put("mensagem", e.getMessage());
		}

		return new ModelAndView("tabs", map);
	}
	
	private String logException(Throwable throwable) {

		String newLine = System.getProperty("line.separator");

		StringBuilder logMessage = new StringBuilder();
		logMessage.append('\t');
		logMessage.append(throwable.getMessage());
		logMessage.append(newLine);
		logMessage.append('\t');
		logMessage.append("Exception:         ");
		logMessage.append(throwable.getClass().getCanonicalName());
		logMessage.append(newLine);
		logMessage.append('\t');
		logMessage.append("Exception Message: ");
		logMessage.append(throwable.getMessage());

		return logMessage.toString();
	}

	public static void main(String[] args) {
		String protocolo = "HTTP/1.1";
		System.out.println(protocolo.substring(0, protocolo.indexOf("/")));
	}
}
