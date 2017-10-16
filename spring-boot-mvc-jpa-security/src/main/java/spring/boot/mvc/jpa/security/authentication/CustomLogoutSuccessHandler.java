package spring.boot.mvc.jpa.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
		implements LogoutHandler {

	private Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
	
	public CustomLogoutSuccessHandler() {
		super();
	}

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		final String refererUrl = request.getHeader("Referer");
		logger.debug("\n ### Finalizando sessao... ".concat(refererUrl)
				.concat("\n"));

		setDefaultTargetUrl("/");
		try {
			this.onLogoutSuccess(request, response, authentication);
		} catch (IOException e) {
			logger.error("---> Erro: "
					.concat(e.getMessage()), e);
		} catch (ServletException e) {
			logger.error("---> Erro: "
					.concat(e.getMessage()), e);
		}
	}

}
