package com.javaguides.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.inventoryservice.dto.InventoryResponse;
import com.javaguides.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController  {

	@Autowired
	private InventoryService inventoryService;
	
	
	//http://localhost:8083/api/inventory/iphone_14,iphone-14_red --> Path Variable
	//http://localhost:8083/api/inventory?skuCode=iphone_14&skuCode=iphone-14_red 
//	@GetMapping("/{sku-code}")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
		return inventoryService.isInStock(skuCode);
	}
	
}
