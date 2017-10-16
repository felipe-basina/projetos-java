package spring.boot.mvc.bootstrap.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
@Transactional
public interface BaseRepository<T, ID extends Serializable> extends
		Repository<T, Long> {

	List<T> findAll();

	T findById(Long id);

	void delete(Long id);

	T save(T pojo);

	long getLastId();
}