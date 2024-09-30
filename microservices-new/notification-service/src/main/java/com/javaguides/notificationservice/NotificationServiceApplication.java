package com.javaguides.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class NotificationServiceApplication {

	private Logger logger = LoggerFactory.getLogger(getClass());
  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
  public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
	  
	  logger.info("Received notification for Order - {}", orderPlacedEvent.getOrderNumber());
  }
  
  @Bean
  public StringJsonMessageConverter jsonConverter() {
      return new StringJsonMessageConverter();
  }
  
 
}
