package spring.jpa.query.methods.service;

public interface IBaseService {

	long getIdFromEntityManager();

	void deleteById(long id);

	long getLastId();
}
