package com.javaguides.orderservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.orderservice.dto.OrderLineItemsDto;
import com.javaguides.orderservice.dto.OrderRequest;
import com.javaguides.orderservice.model.Order;
import com.javaguides.orderservice.model.OrderLineItems;
import com.javaguides.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	@TimeLimiter(name = "inventory" )
	@Retry(name = "inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
	 List<OrderLineItemsDto> list = orderRequest.getOrderLineItemsDtoList();
	 list.stream().forEach(System.out::println);
System.out.println("newwwwwwwwwwwwwwww");
	return CompletableFuture.supplyAsync(()-> orderService.placeOder(orderRequest));	
	}
	
	
	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
		return CompletableFuture.supplyAsync(()-> "Opps! Something went wrong, please order after some time"); 
	}
}
