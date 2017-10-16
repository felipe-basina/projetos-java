package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl2;
import generic.dao.interfaces.dao.TesteDao;
import generic.dao.model.Teste;

import org.springframework.stereotype.Repository;

@Repository
public class TesteDaoImpl extends GenericDaoImpl2<Teste> implements TesteDao {
	
}

