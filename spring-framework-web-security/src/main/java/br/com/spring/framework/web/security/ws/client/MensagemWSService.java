/**
 * MensagemWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spring.framework.web.security.ws.client;

public interface MensagemWSService extends javax.xml.rpc.Service {
	public java.lang.String getMensagemWSPortAddress();

	public br.com.spring.framework.web.security.ws.client.MensagemWS getMensagemWSPort()
			throws javax.xml.rpc.ServiceException;

	public br.com.spring.framework.web.security.ws.client.MensagemWS getMensagemWSPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
