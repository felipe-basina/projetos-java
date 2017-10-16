package generic.dao.interfaces.dao;

import generic.dao.interfaces.GenericDao;
import generic.dao.model.Teste;

public interface TesteDao extends GenericDao<Teste> {

	Teste create(Teste t);

	Teste find(Object id);

}
