package br.com.spring.testes.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.testes.entidade.Entidade;

@Service
public class Services {

	@PersistenceContext(unitName = "PU_JPA")
	private EntityManager em;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Entidade persistirEntidade(Entidade entidade) {
		try {

			this.em.persist(entidade);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return entidade;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> recuperarTodasEntidades() {
		List<Entidade> entidadeLista = new ArrayList<Entidade>();
		
		try {
			
			Query consulta = this.em.createNamedQuery("Entidade.findAll");
			
			entidadeLista.addAll(consulta.getResultList());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return entidadeLista;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean removerTodasEntidades() {
		try {
			
			for (Entidade entidade : this.recuperarTodasEntidades()) {
				this.em.remove(entidade);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
