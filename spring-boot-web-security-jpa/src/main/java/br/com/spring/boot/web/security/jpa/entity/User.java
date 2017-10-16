package br.com.spring.boot.web.security.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spring.boot.web.security.jpa.PasswordCrypto;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "getUserInnerJoinUserRole", query = "SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "user")
	private Set<UserRole> roles;

	public User(Long id, String username, String password, String email,
			Set<UserRole> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public User() {
	}

	public static User createUser(String username, String email, String password) {
		User user = new User();

		user.username = username;
		user.email = email;
		user.password = PasswordCrypto.getInstance().encrypt(password);

		if (user.roles == null) {
			user.roles = new HashSet<UserRole>();
		}

		// create a new user with basic user privileges
		user.roles.add(new UserRole(RoleEnum.USER.toString(), user));

		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}
}