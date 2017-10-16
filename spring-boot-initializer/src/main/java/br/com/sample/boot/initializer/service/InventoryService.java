package br.com.sample.boot.initializer.service;

import org.springframework.stereotype.Component;

@Component
public class InventoryService {

	public boolean isValidInventory(String inventoryId) {
		if (inventoryId == null
				|| "".equals(inventoryId)) {
			return false;
		}
		return true;
	}

}
