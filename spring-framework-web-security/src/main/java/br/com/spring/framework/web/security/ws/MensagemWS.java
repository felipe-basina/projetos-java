package br.com.spring.framework.web.security.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService
@Component
public class MensagemWS extends SpringBeanAutowiringSupport {

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

		Date dtNascimento = null;
		try {
			dtNascimento = sdf.parse(request.getDtNascimento());
		} catch (ParseException e) {
			throw new ParametroInvalidoBusinessException(
					"O campo [data-nascimento] precisa ser um valor de data valido no formato dd/MM/yyyy!",
					e);
		}

		long idade = this.calcularIdade(dtNascimento);

		String mensagemFinal = "Ola [" + request.getNome().toUpperCase()
				+ "], voce esta com [" + idade + "] anos de idade!";

		MensagemWSResponse response = new MensagemWSResponse();
		response.setMensagem(mensagemFinal);

		return response;
	}

	private long calcularIdade(Date dtNascimento) {
		Calendar dtAtual = Calendar.getInstance();
		long anoAtual = dtAtual.get(Calendar.YEAR);
		long mesAtual = dtAtual.get(Calendar.MONTH);

		Calendar dtNascimentoCompleta = Calendar.getInstance();
		dtNascimentoCompleta.setTime(dtNascimento);

		long anoNascimento = dtNascimentoCompleta.get(Calendar.YEAR);
		long mesNascimento = dtNascimentoCompleta.get(Calendar.MONTH);

		long idade = -1;
		if (anoAtual > anoNascimento) {
			idade = anoAtual - anoNascimento;

			if (mesAtual < mesNascimento) {
				idade--;
			}
		}

		return idade;
	}
}
