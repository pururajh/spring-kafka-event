package com.boot.pubsub.commute.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.boot.pubsub.commute.kafka.data.CustomerMessage;
@Configuration
public class KafkaConfiguration {
	

	@Bean
	public ProducerFactory<String, CustomerMessage> produceFactory(){	
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, CustomerMessage>(config);
	}
	@Bean
	public KafkaTemplate<String, CustomerMessage> kafkaTemplate(){
		return new KafkaTemplate<String, CustomerMessage>(produceFactory());
	}
	
	

}
