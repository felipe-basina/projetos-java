package br.com.spring.framework.web.security.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import br.com.spring.framework.web.security.ws.MensagemWSRequest;
import br.com.spring.framework.web.security.ws.MensagemWSResponse;

public class MensagemWSCliente {

	public MensagemWSResponse invokeWS(MensagemWSRequest request, String domain)
			throws ParametroInvalidoBusinessException, RemoteException,
			MalformedURLException, ServiceException {

		MensagemWSServiceLocator serviceLocator = new MensagemWSServiceLocator();

		String endereco = serviceLocator.getMensagemWSPortAddress(domain);
		URL url = new URL(endereco);

		MensagemWSPortBindingStub port = (MensagemWSPortBindingStub) serviceLocator
				.getMensagemWSPort(url);

		return (port.showMessage(request));
	}
}