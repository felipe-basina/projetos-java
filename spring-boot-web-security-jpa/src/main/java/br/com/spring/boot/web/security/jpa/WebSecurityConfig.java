package br.com.spring.boot.web.security.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static PasswordEncoder encoder;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/home").permitAll();

		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/auth").permitAll()
				.and().logout().permitAll();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(
				passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (encoder == null) {
			encoder = new BCryptPasswordEncoder();
		}

		return encoder;
	}
}