package br.com.sample.without.spring.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(name = "Order.findWithItemJoin")
	List<Order> getAllOrderWithItemJoin();
	
}
