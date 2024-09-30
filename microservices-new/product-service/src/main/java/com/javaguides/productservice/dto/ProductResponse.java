package com.javaguides.productservice.dto;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class ProductResponse {

	@Id
	private String id;
	private String skuCode;
	private String name;
	private String description;
	private BigDecimal price;

	
	public ProductResponse(String id, String skuCode, String name, String description, BigDecimal price) {
		super();
		this.id = id;
		this.skuCode = skuCode;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public ProductResponse() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", skuCode=" + skuCode + ", name=" + name + ", description=" + description
				+ ", price=" + price + "]";
	}
	
	
	
	
	
	
}
