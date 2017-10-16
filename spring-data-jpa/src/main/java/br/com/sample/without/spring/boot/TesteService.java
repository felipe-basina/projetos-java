package br.com.sample.without.spring.boot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TesteService.class);
	
	private EntityManager em;

	@Autowired
	public TesteService(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Teste> getAllTeste() {
		
		List<Teste> testes = new ArrayList<Teste>();
		
		try {
			
			/*String sql = "SELECT "
					+ " t.IDENTIFICADOR as idT, t.DESCRICAO as descriptionT, t.DH_CRIACAO as dateT, "
					+ " o.IDENTIFICADOR_O as idO, o.DESCRICAO_O as descriptionO, o.DH_CRIACAO_O as dateO "
					+ " FROM Teste t LEFT JOIN OTeste o ON o.IDENTIFICADOR_O = t.IDENTIFICADOR ORDER BY t.IDENTIFICADOR DESC";
			
			Query query = em.createNativeQuery(sql, "TesteResultMapping");*/
			
			Query query = em.createNamedQuery("getTesteAndOTeste");
			
			testes = this.setTesteObjects((ArrayList<ObjectReturn>) query.getResultList());
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return testes;
	}
	
	private List<Teste> setTesteObjects(List<ObjectReturn> or) {
		
		List<Teste> testes = new ArrayList<Teste>();

		if (or == null
				|| or.size() <= 0) {
			return testes;
		}
		
		try {
			
			for (ObjectReturn o : or) {
				Teste teste = new Teste(o.getIdT(), o.getDescriptionT(), o.getDateT());

				OTeste oTeste = null;
				
				Long idO = o.getIdO();
				if (idO != null) {
					oTeste = new OTeste(idO, o.getDescriptionO(), o.getDateO());
				}

				if (testes.contains(teste)) {
					for (Teste t : testes) {
						if (t.equals(teste)) {
							if (oTeste != null) {
								t.getOtestes().add(oTeste);
								break;
							}
						}
					}
				} else {
					if (oTeste != null) {
						teste.getOtestes().add(oTeste);
					}
					testes.add(teste);
				}
				
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return testes;
	}
}
