package br.com.sample.without.spring.boot;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	
	private static final SpringContext context = SpringContext.getInstance();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	static {
		LOGGER.debug(" # Logging iniciado com sucesso!");
	}
	
	public static void main(String[] args) {
		Order order = new Order(4, new Date());

		int total = 4;
		for (int index = 0; index < total; index++) {
			Item item = new Item("item" + index, new Double("10." + index));
			item.setOrder(order);
			order.getItens().add(item);
		}
		
		try {
			
			OrderService orderService = context.getBean(OrderService.class);

			List<Order>orders = orderService.saveOrder(order);
			LOGGER.debug(" ####### Total of registers: " + orders.size());
			
			for (Order o : orders) {
				LOGGER.debug(" -----------> " + o.toString());
				for (Item i : o.getItens()) {
					LOGGER.debug("\n...." + i.toString());	
				}
			}
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		} finally {
			System.exit(1);
		}
		
	}
}
