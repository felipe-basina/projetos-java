/**
 * MensagemWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spring.framework.web.ws.client;

public interface MensagemWS extends java.rmi.Remote {
	public br.com.spring.framework.web.ws.MensagemWSResponse showMessage(
			br.com.spring.framework.web.ws.MensagemWSRequest mensagemWSRequest)
			throws java.rmi.RemoteException,
			br.com.spring.framework.web.ws.client.ParametroInvalidoBusinessException;
}
