package com.javaguides.productservice.controller;

import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.ec.GMSignatureSpi.sha256WithSM2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.productservice.dto.ProductRequest;
import com.javaguides.productservice.dto.ProductResponse;
import com.javaguides.productservice.model.Product;
import com.javaguides.productservice.service.ProductService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
		System.out.println(productRequest);
		
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
		System.out.println( productService.getAllProducts());
		return productService.getAllProducts();
	}
}
