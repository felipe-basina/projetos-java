package com.spring.boot.ws.sample.endpoint;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.spring.boot.ws.sample.service.HumanResourceService;

@Endpoint
public class HolidayEndpoint {

    private final Logger logger = LoggerFactory
	    .getLogger(HolidayEndpoint.class);
    
    private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

    private XPathExpression<Element> startDateExpression;
    private XPathExpression<Element> endDateExpression;
    private XPathExpression<String> nameExpression;

    private HumanResourceService humanResourceService;

    @Autowired
    public HolidayEndpoint(HumanResourceService humanResourceService)
	    throws JDOMException, XPathFactoryConfigurationException,
	    XPathExpressionException {
	this.humanResourceService = humanResourceService;

	Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);

	XPathFactory xPathFactory = XPathFactory.instance();

	this.startDateExpression = xPathFactory.compile("//hr:StartDate",
		Filters.element(), null, namespace);
	this.endDateExpression = xPathFactory.compile("//hr:EndDate",
		Filters.element(), null, namespace);
	this.nameExpression = xPathFactory.compile(
		"concat(//hr:FirstName,' ',//hr:LastName)", Filters.fstring(),
		null, namespace);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
    @ResponsePayload // Necessário para enviar retorno da operação
    public Element handleHolidayRequest(@RequestPayload Element holidayRequest)
	    throws Exception {
	
	logger.info("\n\n ### Request payload : " + holidayRequest + "\n\n");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date startDate = dateFormat.parse(this.startDateExpression
		.evaluateFirst(holidayRequest).getText());
	Date endDate = dateFormat.parse(this.endDateExpression.evaluateFirst(
		holidayRequest).getText());
	String name = this.nameExpression.evaluateFirst(holidayRequest);

	String retorno = this.humanResourceService.bookHoliday(startDate, endDate, name);
	
	// Monta o retorno
	Element childElement1 = new Element("Message");
	childElement1.setText(retorno);
	
	Element responseElement = new Element("HolidayResponse");
	responseElement.addContent(childElement1);
	
	return responseElement;
    }

}