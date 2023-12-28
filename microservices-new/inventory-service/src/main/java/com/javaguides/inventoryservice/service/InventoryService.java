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
		logger.info("Wait Started");
//		try {
//		Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("Wait Ended ");
		
		
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(this::inventoryCheck).toList();
		
	}

	private InventoryResponse inventoryCheck(Inventory inventory) {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setSkuCode(inventory.getSkuCode());
		inventoryResponse.setInStock(inventory.getQuantity()>0);
		
		return inventoryResponse;
		
	}
	
		
}
