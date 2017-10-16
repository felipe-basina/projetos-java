package spring.jpa.query.methods.service;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.query.methods.repository.UsuarioRepository;

@Service
public class BaseService implements IBaseService {

	@Autowired
	private EntityManager em;

	@Autowired
	private UsuarioRepository repository;

	@Override
	public long getIdFromEntityManager() {
		Query query = em.createNamedQuery("Usuario.getLastId");

		long id = 0l;

		try {
			id = ((BigInteger) query.getSingleResult()).longValue();
		} catch (Exception ex) {
			System.err.println(" #### ".concat(ex.getMessage()));
		}

		return id;
	}

	@Override
	public void deleteById(long id) {
		repository.delete(id);
	}

	@Override
	public long getLastId() {
		long id = -1l;

		try {
			id = repository.getLastId();
		} catch (Exception ex) {
			System.err.println(" ### ".concat(ex.getMessage()));
		}

		return id;
	}
}
