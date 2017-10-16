package br.com.spring.boot.web.security.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.boot.web.security.jpa.entity.User;

@Service
public class UserService {

	@Autowired
	private EntityManager em;
	
	public User getUser(String userName) {
		Query query = em.createNamedQuery("getUserInnerJoinUserRole");
		query.setParameter("username", userName);

		User user = null;
		
		try {
			
			user = (User) query.getResultList().get(0);
			
		} catch (Exception ex) {
			System.out.println("--> ERRO:" + ex.getMessage());
		}
		
		return user;
	}
	
}
