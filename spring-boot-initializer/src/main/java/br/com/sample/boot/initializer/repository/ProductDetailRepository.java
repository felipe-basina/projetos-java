package br.com.sample.boot.initializer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.sample.boot.initializer.model.ProductDetail;

@Repository
public interface ProductDetailRepository extends
		PagingAndSortingRepository<ProductDetail, String> {

	@Query("select p from ProductDetail p where UPPER(p.productName) like UPPER(?1) or "
			+ "UPPER(p.longDescription) like UPPER(?1)")
	List<ProductDetail> search(String term);

}
