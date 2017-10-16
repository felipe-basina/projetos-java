package br.com.spring.framework.web.security.autentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class LoginFailureListener implements AuthenticationFailureHandler {

	@Autowired
	private MessageSource messageSource;

	private static final String MESSAGE = "message";

	private static final String TOTAL_RETENTATIVAS = "3";

	private static final String TOTAL_RETENTATIVAS_CONFIG = "TOTAL_RETENTATIVAS_CONFIG";

	@Override
	public final void onAuthenticationFailure(final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException exception) throws IOException,
			ServletException {

		System.out
				.println("\n ############ onAuthenticationFailure ############ ");

		final Object loginAttempts = RequestContextHolder
				.currentRequestAttributes().getAttribute(
						TOTAL_RETENTATIVAS_CONFIG,
						RequestAttributes.SCOPE_SESSION);
		System.out
				.println("\n ########### Total de retentativas configuracao ["
						+ loginAttempts + "]");

		if (loginAttempts == null) {
			RequestContextHolder.currentRequestAttributes().setAttribute(
					TOTAL_RETENTATIVAS_CONFIG, 1,
					RequestAttributes.SCOPE_SESSION);
		} else {
			RequestContextHolder.currentRequestAttributes().setAttribute(
					TOTAL_RETENTATIVAS_CONFIG,
					Integer.parseInt(loginAttempts.toString()) + 1,
					RequestAttributes.SCOPE_SESSION);

			if (TOTAL_RETENTATIVAS.equals(String.valueOf(loginAttempts))) {

				RequestContextHolder.currentRequestAttributes().setAttribute(
						TOTAL_RETENTATIVAS_CONFIG, 0,
						RequestAttributes.SCOPE_SESSION);

				request.getSession()
						.setAttribute(
								MESSAGE,
								new StatusMessage(
										StatusMessage.ERROR,
										messageSource
												.getMessage(
														"error.security.login.attempt.exceed",
														null,
														LocaleContextHolder
																.getLocale())));

				response.sendRedirect("login.do");
				return;
			}
		}

		if (exception != null) {
			System.out.println("\n ######## Exception message: "
					+ exception.getMessage());
			request.getSession().setAttribute(
					MESSAGE,
					new StatusMessage(StatusMessage.ERROR, exception
							.getMessage()));
		}

		response.sendRedirect("login.do");
	}

}