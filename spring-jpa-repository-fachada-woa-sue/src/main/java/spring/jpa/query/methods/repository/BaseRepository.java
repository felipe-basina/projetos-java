package spring.jpa.query.methods.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		Repository<T, Long> {

	List<T> findAll();

	T findById(Long id);

	void delete(Long id);

	T save(T pojo);
}