package com.boot.pubsub.commute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.pubsub.commute.kafka.data.CustomerMessage;

@RestController
@RequestMapping("customer")
public class CustomerService {
	
	@Autowired
	private KafkaTemplate<String, CustomerMessage> kafkaTemplate;
	
	private static final String TOPIC = "puru-test-topic";
	
	@GetMapping("/pub/{name}")
	public String postMessage(@PathVariable("name") final String name) {
		kafkaTemplate.send(TOPIC, getCustomer(name));
		return "Message posted to kafka successfully";
	}
	
	private CustomerMessage getCustomer(String name) {
		CustomerMessage customerMessage = new CustomerMessage();
		customerMessage.setFirstName(name);
		customerMessage.setLastName("harichandan");
		customerMessage.setAge("38");
		customerMessage.setStatus("Active");
		return customerMessage;
	}
	

}
