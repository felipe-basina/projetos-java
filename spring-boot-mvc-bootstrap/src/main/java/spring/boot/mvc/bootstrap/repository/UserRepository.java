package spring.boot.mvc.bootstrap.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import spring.boot.mvc.bootstrap.model.User;

@Transactional
public interface UserRepository extends BaseRepository<User, Long> {

	List<User> findByName(String name);

	User findByEmail(String email);
	
	@Override
	// @Query(value="SELECT MAX(IDENTIFICADOR) FROM REGISTRO_TBL",
	// nativeQuery=true)
	@Query(value = "SELECT MAX(id) FROM user", nativeQuery = false)
	public long getLastId();
}
