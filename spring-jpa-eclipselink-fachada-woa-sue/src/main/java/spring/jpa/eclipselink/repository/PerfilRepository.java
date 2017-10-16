package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.Perfil;
import spring.jpa.eclipselink.exception.DataException;

public interface PerfilRepository extends GenericRepository<Perfil> {

	Perfil recuperarPermissoesPerfil(Perfil perfil) throws DataException;

	Perfil recuperarPerfilPorNome(String nomePerfil) throws DataException;
}
