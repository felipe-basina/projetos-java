package spring.jpa.query.methods.service;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.query.methods.model.UsuarioAutenticacao;
import spring.jpa.query.methods.model.UsuarioAutorizacao;
import spring.jpa.query.methods.repository.UsuarioAutenticacaoRepository;

@Service
public class BaseService extends GenericService implements IBaseService {

	@Autowired
	private EntityManager em;

	@Autowired
	private UsuarioAutenticacaoRepository auteRepo;

	@Override
	public UsuarioAutenticacao findByLogin(String login) {
		return auteRepo.findByLogin(login);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioAutorizacao> findByEmail(String email) {

		Query consulta = em.createQuery(this.getMessage(
				"consulta.autorizacao.findbyemail").getText());
		consulta.setHint("eclipselink.join-fetch",
				"ua.matrizAutorizacoes.perfil");
		consulta.setHint("eclipselink.join-fetch",
				"ua.matrizAutorizacoes.recurso");
		consulta.setHint("eclipselink.join-fetch",
				"ua.matrizAutorizacoes.recurso.recursoParametros");
		consulta.setParameter("email", email);

		try {
			List<UsuarioAutorizacao> usuarios = (Vector<UsuarioAutorizacao>) consulta
					.getResultList();
			System.out.println("\t\n Tamanho da lista :: " + usuarios.size()
					+ "\n");
			return usuarios;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return null;
	}
}
