package com.burndown.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.burndown.core.application.ProduktBacklogApplication;
import com.burndown.core.application.RoleApplication;
import com.burndown.core.application.SprintApplication;
import com.burndown.core.application.StoryApplication;
import com.burndown.core.application.UserApplication;
import com.burndown.dto.ProduktBacklogDto;
import com.burndown.dto.RoleDto;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;
import com.burndown.dto.StoryDto;
import com.burndown.dto.UserDto;
import com.burndown.factory.StoryFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class RabbitListener {
	
	@Autowired
	RoleApplication roleApplication;
	
	@Autowired
	ProduktBacklogApplication producktBacklogApplication;
	
	@Autowired
	UserApplication userAppliaction;
	
	@Autowired
	SprintApplication sprintApplication;
	
	@Autowired
	StoryApplication storyApplication;
	
	@Autowired
	StoryFactory storyFactory;
	
	private static final String EXCHANGE_NAME = "task_update_exchange";
	
	 public void ReceiveTaskChangedNotificationRabbitMq() {
	        try { 
	            ConnectionFactory factory = new ConnectionFactory();
	            factory.setHost("localhost");
	            Connection connection = factory.newConnection();
	            Channel channel = connection.createChannel();
	            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
	            String queueName = channel.queueDeclare().getQueue();
	            channel.queueBind(queueName, EXCHANGE_NAME, "test");
	            System.out.println("Waiting for messages.");
	            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	                String incoming = new String(delivery.getBody(), "UTF-8");
	                String[] message = incoming.split("/");
	                
	                if(message[1].equals("role_by_id") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	roleApplication.getRoleByIDAnswer(id);
	                }
	                
	                if(message[1].equals("role_update") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	RoleDto role = mapper.readValue(message[0], RoleDto.class);
	                	roleApplication.updateRole(role);
	                }
	                
	                if(message[1].equals("role_delete") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	roleApplication.deleteRole(id);
	                }
	                
	                if(message[1].equals("role_by_id") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	roleApplication.getRoleByIDAnswer(id);
	                }
	                
	                if(message[1].equals("role_create") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	RoleDto role = mapper.readValue(message[0], RoleDto.class);
	                	roleApplication.createnewrole(role);
	                }
	                
	                if(message[1].equals("backlog_create") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	ProduktBacklogDto dto = mapper.readValue(message[0], ProduktBacklogDto.class);
	                	producktBacklogApplication.createProducktBacklog(dto);
	                }
	                
	                if(message[1].equals("backlog_delete") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	producktBacklogApplication.deleteProducktBacklog(id);
	                }
	                
	                if(message[1].equals("user_delete") == true) {
	                	Long id = Long.parseLong(message[0].replace("\"", ""));
	                	userAppliaction.deleteUser(id);
	                }
	                
	                if(message[1].equals("user_create") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	UserDto dto = mapper.readValue(message[0], UserDto.class);
	                	userAppliaction.createnewUser(dto);
	                }
	                
	                if(message[1].equals("user_update") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	UserDto dto = mapper.readValue(message[0], UserDto.class);
	                	userAppliaction.updateUser(dto);
	                }
	                
	                if(message[1].equals("user_login") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	userAppliaction.getLogin(id);
	                }
	                
	                if(message[1].equals("sprint_create") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	String[] message2 = message[0].split(";");
	                	SprintDto dto = mapper.readValue(message2[0], SprintDto.class);
	                	SprintBacklogDto dot2 = mapper.readValue(message2[1], SprintBacklogDto.class);
	                	sprintApplication.createSprint(dto, null);
	                }
	                
	                if(message[1].equals("story_points") == true) {
	                	ObjectMapper mapper = new ObjectMapper();
	                	String[] message2 = message[0].split("%");
	                	Long id = Long.parseLong(message2[0]);
	                	int points = Integer.parseInt(message2[1]);
	                	storyApplication.updateStorypoints(id, points);
	                }
	                
	                
	                if(message[1].equals("story_done") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	storyApplication.setstorydone(id);
	                }
	                
	                if(message[1].equals("story_get") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	storyApplication.getStory(id);
	                }
	                
	                if(message[1].equals("story_create") == true) {
	                  	ObjectMapper mapper = new ObjectMapper();
	                	StoryDto story = mapper.readValue(message[0], StoryDto.class);
	                	storyApplication.createStory(story);
	                }
	                
	                if(message[1].equals("story_update") == true) {
	                	StoryDto dto = storyFactory.fromJson(message[0]);
	                	storyApplication.updateStory(dto);
	                }
	                
	                if(message[1].equals("story_sprint") == true) {
	                	Long id = Long.parseLong(message[0]);
	                	storyApplication.getStoriesBySprint(id);
	                }
	                
	                System.out.println("Received new message '" + delivery.getEnvelope().getRoutingKey() + "':'" + incoming + "'");
	                
	            };
	            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
	        }
	        catch(Exception e) {
	            System.out.println("Fehler: "+ e);
	        }
	    }
}
