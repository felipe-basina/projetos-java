package spring.ws.jms.sample;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import spring.ws.jms.sample.pojo.Pessoa;

@Service
public class TestJmsMdb implements MessageListener {

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	public void onMessage(final Message message) {

		try {

			Pessoa pessoa = (Pessoa) jmsTemplate.getMessageConverter()
					.fromMessage(message);
			System.out.println("\n\n\n >>>>>>>>>>>>>> Fila: " + this
					+ "\n >>>>>>>>>>>>>> Objeto recebido: " + pessoa);

			System.out.println("\n\n ##### Ola ".concat(pessoa.getNome())
					.concat(", sua data de nascimento e: ")
					.concat(pessoa.getDtNascimento()));

			Thread.sleep(5000l);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.getClass().getCanonicalName();
	}
}