package com.javaguides.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
