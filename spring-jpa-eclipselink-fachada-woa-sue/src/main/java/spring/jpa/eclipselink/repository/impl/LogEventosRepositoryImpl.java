package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.LogEventos;
import spring.jpa.eclipselink.repository.LogEventosRepository;

@Repository("logEventosRepositoryImpl")
public class LogEventosRepositoryImpl extends GenericRepositoryImpl<LogEventos>
		implements LogEventosRepository {

}
