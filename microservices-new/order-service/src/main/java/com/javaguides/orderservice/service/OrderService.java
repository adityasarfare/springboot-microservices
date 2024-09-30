package com.javaguides.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.javaguides.orderservice.config.WebClientConfig;
import com.javaguides.orderservice.dto.InventoryResponse;
import com.javaguides.orderservice.dto.OrderLineItemsDto;
import com.javaguides.orderservice.dto.OrderRequest;
import com.javaguides.orderservice.event.OrderPlacedEvent;
import com.javaguides.orderservice.model.Order;
import com.javaguides.orderservice.model.OrderLineItems;
import com.javaguides.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private KafkaTemplate<String, OrderPlacedEvent > kafkaTemplate;
	
	
	public String placeOder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::maptoDto)
		.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
				.map(OrderLineItems::getSkuCode)
							.toList();
		//Call Inventory service and place order if product is in stock
		
		InventoryResponse[] inventoryResponseArray =  webClientBuilder.build().get()
		.uri("http://inventory-service/api/inventory",
				uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
		.retrieve()
		.bodyToMono(InventoryResponse[].class)
		.block();
		
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse-> inventoryResponse.isInStock());
		System.out.println(allProductsInStock);
		if ( allProductsInStock) {
			System.out.println(order.getOrderNumber());	
		orderRepository.save(order);
		kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
		return "Order place sucessfully";
	}
		else {
			throw new IllegalArgumentException("Product is not in stock, please update the stock");
		}
	}
	
	public OrderLineItems maptoDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		
		return orderLineItems;
	}
}
