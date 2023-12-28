package com.javaguides.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javaguides.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
