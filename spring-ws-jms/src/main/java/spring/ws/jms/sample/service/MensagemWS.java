package spring.ws.jms.sample.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import spring.ws.jms.sample.QueueUtil;
import spring.ws.jms.sample.pojo.Pessoa;

@WebService
@Component
public class MensagemWS extends SpringBeanAutowiringSupport {

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	/** The queueUtil. */
	@Autowired
	private QueueUtil queueUtil;

	private static final String QUEUE_NAME = "jms/TestJmsQueue";

	@WebMethod(operationName = "showMessage")
	@WebResult(name = "MensagemWSResponse")
	public MensagemWSResponse showMessage(
			@WebParam(name = "MensagemWSRequest") MensagemWSRequest request)
			throws ParametroInvalidoBusinessException {

		if (request == null || request.getNome() == null
				|| "".equals(request.getNome())
				|| request.getDtNascimento() == null
				|| "".equals(request.getDtNascimento())) {

			String camposObrigatorios = "nome e data-nascimento";
			throw new ParametroInvalidoBusinessException("Os campos ["
					+ camposObrigatorios + "] sao obrigatorios!");
		}

		String pattern = "^[A-Za-z]+$";
		boolean nomeValido = request.getNome().matches(pattern);
		if (!nomeValido) {
			throw new ParametroInvalidoBusinessException(
					"O campo [nome] deve conter somente caracteres de A-Z");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {
			sdf.parse(request.getDtNascimento());
		} catch (ParseException e) {
			throw new ParametroInvalidoBusinessException(
					"O campo [data-nascimento] precisa ser um valor de data valido no formato dd/MM/yyyy!",
					e);
		}

		MensagemWSResponse response = new MensagemWSResponse();
		response.setMensagem(this.enviarObjetoProcessamento(request));

		return response;
	}

	private String enviarObjetoProcessamento(MensagemWSRequest request) {

		Pessoa pessoa = new Pessoa(request.getNome(), request.getDtNascimento());
		System.out.println("\n\n\n >>>>>>>>>>>>>> Enviado objeto pessoa: "
				+ pessoa + " para fila!");

		queueUtil.verificarSituacaoFila(QUEUE_NAME);

		jmsTemplate.convertAndSend(QUEUE_NAME, pessoa);

		String mensagemFinal = "Objeto enviado para fila com sucesso: "
				.concat(pessoa.toString());

		return mensagemFinal;

	}
}
