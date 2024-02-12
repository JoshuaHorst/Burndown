package com.burndown.restcontroller;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@Component
public class RabbitAnswer {
	

	private static final String EXCHANGE_NAME = "task_update_exchange_answer";
	

	public void sendAnswer(String message) {
	
	ConnectionFactory factory = new ConnectionFactory();
	factory.setHost("localhost");
	try (Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel()) {

	    channel.exchangeDeclare(EXCHANGE_NAME, "topic");

	    channel.basicPublish(EXCHANGE_NAME, "test_answer", null, message.getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + "test" + "':'" + message + "'");
		}catch(Exception e){
			System.out.println("failure");
		}
	
}
}
