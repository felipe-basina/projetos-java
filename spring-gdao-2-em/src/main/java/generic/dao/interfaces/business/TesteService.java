package generic.dao.interfaces.business;

import generic.dao.model.Teste;

import java.util.List;

public interface TesteService {
    Teste create(Teste t);
    Teste find(Object id);
    List<Teste> getAll();
    void delete(Teste t);
}
