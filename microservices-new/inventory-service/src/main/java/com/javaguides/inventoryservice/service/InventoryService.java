package com.javaguides.inventoryservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaguides.inventoryservice.dto.InventoryResponse;
import com.javaguides.inventoryservice.model.Inventory;
import com.javaguides.inventoryservice.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
//	private Logger logger = LoggerFactory.getLogger(getClass());

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Transactional(readOnly = true)
 	public List<InventoryResponse> isInStock(List<String> skuCode) {
     logger.info("Checking stock for SKUs: {}", skuCode);
        
        // Fetch inventories by SKU codes
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCode);
        logger.info("Found Inventories: {}", inventories);

        // Map SKU codes to inventory responses
        return skuCode.stream()
            .map(sku -> {
                Inventory inventory = inventories.stream()
                    .filter(inv -> inv.getSkuCode().equals(sku))
                    .findFirst()
                    .orElse(null);

                InventoryResponse response = new InventoryResponse();
                response.setSkuCode(sku);
                response.setInStock(inventory != null && inventory.getQuantity() > 0);
                return response;
            })
            .toList();
	}

	private InventoryResponse inventoryCheck(Inventory inventory) {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setSkuCode(inventory.getSkuCode());
		inventoryResponse.setInStock(inventory.getQuantity()>0);
		
		return inventoryResponse;
		
	}
	
		
}
