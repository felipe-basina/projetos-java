package br.com.sample.without.spring.boot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderService.class);
	
	private OrderRepository orderRepository;

	private EntityManager em;

	@Autowired
	public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
		this.orderRepository = orderRepository;
		this.em = entityManager;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Order> saveOrder(Order order) throws Exception {
		if (order == null
				|| order.getItens().size() <= 0) {
			throw new Exception("Object cannot be null/empty!");
		}
		
		List<Order> orders = new ArrayList<Order>();
		
		try {
			
			LOGGER.debug("Saving order...");
			orderRepository.save(order);
			
			LOGGER.debug("Getting all orders...");
			//orders = this.getAllOrderWithItemJoin();
			orders = this.getAllOrderWithItemJoinInterface();
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return orders;
	}
	
	/**
	 * Exemplo utilizando dependência entity-manager
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private List<Order> getAllOrderWithItemJoin() throws Exception {
		List<Order> orders = new ArrayList<Order>();
		
		try {
			
			TypedQuery<Order> query = em.createNamedQuery("Order.findWithItemJoin", Order.class);
			orders = query.getResultList();
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex);
		}
		
		return orders;
	}
	
	private List<Order> getAllOrderWithItemJoinInterface() throws Exception {
		List<Order> orders = new ArrayList<Order>();
		
		try {
			
			orders = orderRepository.getAllOrderWithItemJoin();
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex);
		}
		
		return orders;
	}
}
