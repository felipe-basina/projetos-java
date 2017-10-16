package br.com.spring.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageListener {

	@JmsListener(destination = "SendToRecv")
	@SendTo("RecvToSend")
	public String processMessage(String text) {
		System.out.println("Received: " + text);
		return "ACK from handleMessage";
	}
}