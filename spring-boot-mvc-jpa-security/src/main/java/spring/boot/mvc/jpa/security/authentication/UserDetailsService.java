package spring.boot.mvc.jpa.security.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Service(value = "userDetailsService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	private static final String ADMIN = "adm";

	private static final String SENHA = "claro123";

	private static final List<String> USUARIOS_VALIDOS = new ArrayList<String>();

	@Autowired
	private MessageSource messageSource;

	@Override
	public final UserDetails loadUserByUsername(final String login) {
		System.out.println("\n ######### Entrou em loadUserByUsername: "
				+ login);

		System.out.println("\n ######## Validando login...");
		this.verifyUser(login);

		System.out.println("\n ######## Adicionando usuario na sessao...");
		RequestContextHolder.currentRequestAttributes().setAttribute(
				"KEY_USER_SESSION", login, RequestAttributes.SCOPE_SESSION);

		System.out.println("\n ######### Retornando usuario [" + login + "]");

		return new org.springframework.security.core.userdetails.User(login,
				SENHA, true, true, true, true, this.getAuthorities(this
						.getRole(login)));
	}

	private String getRole(final String login) {
		if (ADMIN.equalsIgnoreCase(login)) {
			return "ROLE_ADMIN";
		}
		return "ROLE_USER";
	}

	private void verifyUser(final String user) {
		USUARIOS_VALIDOS.add("adm");
		USUARIOS_VALIDOS.add("usr");

		if (user == null || !USUARIOS_VALIDOS.contains(user)) {
			throw new UserAuthenticationException(messageSource.getMessage(
					"error.security.invalid.credentials", null,
					LocaleContextHolder.getLocale()));
		}
	}

	/**
	 * Gets the authorities.
	 *
	 * @param user
	 *            the user
	 * @return the authorities
	 */
	public final Collection<? extends GrantedAuthority> getAuthorities(
			final String role) {
		final HashSet<String> roles = new HashSet<String>();
		roles.add(role);

		final List<GrantedAuthority> authList = this
				.getGrantedAuthorities(roles);
		return authList;
	}

	/**
	 * Gets the granted authorities.
	 *
	 * @param roles
	 *            the roles
	 * @return the granted authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(final Set<String> roles) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	/**
	 * Sets the message source.
	 *
	 * @param pMessageSource
	 *            the new message source
	 */
	public final void setMessageSource(final MessageSource pMessageSource) {
		messageSource = pMessageSource;
	}

}