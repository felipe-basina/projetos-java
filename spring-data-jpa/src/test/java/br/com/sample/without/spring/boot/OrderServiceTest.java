package br.com.sample.without.spring.boot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveOrder() throws Exception {
		Assert.assertNotNull(orderRepository);
		
		int totalOfElements = 3;
		
		List<Order> orders = new ArrayList<Order>();
		for (int index = 0; index < totalOfElements; index++) {
			Order order = new Order(index + 1, new Date());

			Item item = new Item("item" + index, new Double(index + index));
			item.setOrder(order);
			
			order.getItens().add(item);
			orders.add(order);
		}
		
		Order saved = new Order(6, new Date());
		saved.getItens().add(new Item("item-teste", 12.6));
		saved.getItens().get(0).setOrder(saved);
		
		Mockito.when(orderRepository.getAllOrderWithItemJoin()).thenReturn(orders);
		
		List<Order> ordersReturned = orderService.saveOrder(saved);
		
		Mockito.verify(orderRepository).save((Order) Mockito.anyObject());
		Mockito.verify(orderRepository).getAllOrderWithItemJoin();
		Mockito.verify(orderRepository, Mockito.times(1)).save((Order) Mockito.anyObject());
		Mockito.verify(orderRepository, Mockito.times(1)).getAllOrderWithItemJoin();
		Mockito.verifyNoMoreInteractions(orderRepository);
		
		Assert.assertEquals(totalOfElements, ordersReturned.size());
	}

	@Test(expected = Exception.class)
	public void testSaveOrderWithException() throws Exception {
		Mockito.doThrow(new Exception("Exception JUnit")).when(orderRepository).getAllOrderWithItemJoin();
		orderService.saveOrder((Order) Mockito.any());
	}

}
