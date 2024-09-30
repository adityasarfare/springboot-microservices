package com.javaguides.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder	
public class InventoryResponse {

	private String skuCode;
	private boolean  isInStock;
	
	public InventoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InventoryResponse(String skuCode, boolean isInStock) {
		super();
		this.skuCode = skuCode;
		this.isInStock = isInStock;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public boolean isInStock() {
		return isInStock;
	}
	public void setInStock(boolean inStock) {
		this.isInStock = inStock;
	}
	
	
}
