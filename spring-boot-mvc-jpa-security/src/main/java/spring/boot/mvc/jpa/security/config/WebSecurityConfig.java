package spring.boot.mvc.jpa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	private LogoutHandler logoutSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .httpBasic().disable()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/js/*", "/css/*", "/")
			.permitAll()
		.anyRequest()
			.authenticated()
			.and()
        .formLogin()
        	.loginPage("/login")
        	.loginProcessingUrl("/login")
        	.defaultSuccessUrl("/init")
        	.failureUrl("/login?error")
        	.permitAll()
        	.failureHandler(authFailureHandler)
        	.and()
        .logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/")
        	.addLogoutHandler(logoutSuccessHandler)
        	.permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}*/
}