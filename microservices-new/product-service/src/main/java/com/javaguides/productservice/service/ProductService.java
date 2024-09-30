package com.javaguides.productservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.productservice.dto.ProductRequest;
import com.javaguides.productservice.dto.ProductResponse;
import com.javaguides.productservice.model.Product;
import com.javaguides.productservice.repository.ProductRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setSkuCode(productRequest.getSkuCode());
        // Further logic to save the product or perform additional operations.
        
        productRepository.save(product);
          logger.info("Product {} is saved successfully", product.getId());
    }
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		return products.stream().map(this::mapToProductResponse).toList();
		
	}
	
 private ProductResponse mapToProductResponse(Product product) {
	 
	 ProductResponse productResponse = new ProductResponse();
	 productResponse.setDescription(product.getDescription());
	 productResponse.setId(product.getId());
	 productResponse.setName(product.getName());
	 productResponse.setPrice(product.getPrice());
	 productResponse.setSkuCode(product.getSkuCode());
	 return productResponse;
 }
}
