/**
 * MensagemWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spring.framework.web.ws.client;

public class MensagemWSServiceLocator extends org.apache.axis.client.Service
		implements MensagemWSService {

	public MensagemWSServiceLocator() {
	}

	public MensagemWSServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public MensagemWSServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for MensagemWSPort
	// private java.lang.String MensagemWSPort_address =
	// "http://localhost:7001/ClaroFrameworkWeb2/MensagemWSService";
	private java.lang.String MensagemWSPort_address = "/MensagemWSService";

	public java.lang.String getMensagemWSPortAddress() {
		return MensagemWSPort_address;
	}

	public java.lang.String getMensagemWSPortAddress(String domain) {
		return domain.concat(MensagemWSPort_address);
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String MensagemWSPortWSDDServiceName = "MensagemWSPort";

	public java.lang.String getMensagemWSPortWSDDServiceName() {
		return MensagemWSPortWSDDServiceName;
	}

	public void setMensagemWSPortWSDDServiceName(java.lang.String name) {
		MensagemWSPortWSDDServiceName = name;
	}

	public br.com.spring.framework.web.ws.client.MensagemWS getMensagemWSPort()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(MensagemWSPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getMensagemWSPort(endpoint);
	}

	public br.com.spring.framework.web.ws.client.MensagemWS getMensagemWSPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			MensagemWSPortBindingStub _stub = new MensagemWSPortBindingStub(
					portAddress, this);
			_stub.setPortName(getMensagemWSPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setMensagemWSPortEndpointAddress(java.lang.String address) {
		MensagemWSPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (br.com.spring.framework.web.ws.MensagemWS.class
					.isAssignableFrom(serviceEndpointInterface)) {
				MensagemWSPortBindingStub _stub = new MensagemWSPortBindingStub(
						new java.net.URL(MensagemWSPort_address), this);
				_stub.setPortName(getMensagemWSPortWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("MensagemWSPort".equals(inputPortName)) {
			return getMensagemWSPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName(
				"http://ws.web.framework.spring.com.br/", "MensagemWSService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName(
					"http://ws.web.framework.spring.com.br/", "MensagemWSPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("MensagemWSPort".equals(portName)) {
			setMensagemWSPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
