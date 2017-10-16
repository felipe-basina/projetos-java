package br.com.spring.boot.web.security.jpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.boot.web.security.jpa.entity.UserRole;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	//private UserRepository userRepository;
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		System.out.println("--> Searching for user: " + username);
		
		br.com.spring.boot.web.security.jpa.entity.User user = userService.getUser(username);
		List<GrantedAuthority> authorities = this.buildUserAuthority(user.getRoles());

		System.out.println("--> User found: " + user);
		
		return this.buildUserForAuthentication(user, authorities);

	}

	private User buildUserForAuthentication(br.com.spring.boot.web.security.jpa.entity.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		return new ArrayList<GrantedAuthority>(setAuths);
	}
}
