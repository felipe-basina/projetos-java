package spring.jpa.eclipselink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.UsuarioAutenticacao;
import spring.jpa.eclipselink.domain.UsuarioAutorizacao;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.AutenticacaoRepository;
import spring.jpa.eclipselink.repository.AutorizacaoRepository;

@Service
public class ServiceImpl implements ServiceInterface {

	@Autowired
	private AutenticacaoRepository auteRepo;

	@Autowired
	private AutorizacaoRepository autoRepo;

	public UsuarioAutenticacao recuperarUsuarioAutenticacao(String login)
			throws DataException {
		return auteRepo.buscarUsuarioPorLogin(login);
	}

	public UsuarioAutorizacao listarRecursosUsuario(String login)
			throws DataException {
		return autoRepo.listarRecursosUsuario(login);
	}
}
