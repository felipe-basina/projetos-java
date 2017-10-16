package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.ParametroSistema;
import spring.jpa.eclipselink.repository.ParametroSistemaRepository;

@Repository("parametroSistemaRepositoryImpl")
public class ParametroSistemaRepositoryImpl extends
		GenericRepositoryImpl<ParametroSistema> implements
		ParametroSistemaRepository {

}
