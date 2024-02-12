package com.burndown.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {
	
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

    @KafkaListener(topics = "my-topic-back", groupId = "my-group-id")
    public void listen(String incoming) throws JsonProcessingException {
    	
    	String[] message = incoming.split("/");
    	
    	if(message[1].equals("role_by_id") == true) {
        	Long id = Long.parseLong(message[0]);
        	roleApplication.getRoleByIDAnswerKafka(id);
        }
        
        if(message[1].equals("role_update") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	RoleDto role = mapper.readValue(message[0], RoleDto.class);
        	roleApplication.updateRoleKafka(role);
        }
        
        if(message[1].equals("role_delete") == true) {
        	Long id = Long.parseLong(message[0]);
        	roleApplication.deleteRoleKafka(id);
        }
        
        if(message[1].equals("role_by_id") == true) {
        	Long id = Long.parseLong(message[0]);
        	roleApplication.getRoleByIDAnswerKafka(id);
        }
        
        if(message[1].equals("role_create") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	RoleDto role = mapper.readValue(message[0], RoleDto.class);
        	roleApplication.createnewroleKafka(role);
        }
        
        if(message[1].equals("backlog_create") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	ProduktBacklogDto dto = mapper.readValue(message[0], ProduktBacklogDto.class);
        	producktBacklogApplication.createProducktBacklogKafka(dto);
        }
        
        if(message[1].equals("backlog_delete") == true) {
        	Long id = Long.parseLong(message[0]);
        	producktBacklogApplication.deleteProducktBacklogKafka(id);
        }
        
        if(message[1].equals("user_delete") == true) {
        	Long id = Long.parseLong(message[0].replace("\"", ""));
        	userAppliaction.deleteUserKafka(id);
        }
        
        if(message[1].equals("user_create") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	UserDto dto = mapper.readValue(message[0], UserDto.class);
        	userAppliaction.createnewUserKafka(dto);
        }
        
        if(message[1].equals("user_update") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	UserDto dto = mapper.readValue(message[0], UserDto.class);
        	userAppliaction.updateUserKafka(dto);
        }
        
        if(message[1].equals("user_login") == true) {
        	Long id = Long.parseLong(message[0]);
        	userAppliaction.getLoginKafka(id);
        }
        
        if(message[1].equals("sprint_create") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	String[] message2 = message[0].split(";");
        	SprintDto dto = mapper.readValue(message2[0], SprintDto.class);
        	SprintBacklogDto dot2 = mapper.readValue(message2[1], SprintBacklogDto.class);
        	sprintApplication.createSprintKafka(dto, null);
        }
        
        if(message[1].equals("story_points") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	String[] message2 = message[0].split("%");
        	Long id = Long.parseLong(message2[0]);
        	int points = Integer.parseInt(message2[1]);
        	storyApplication.updateStorypointsKafka(id, points);
        }
        
        
        if(message[1].equals("story_done") == true) {
        	Long id = Long.parseLong(message[0]);
        	storyApplication.setstorydoneKafka(id);
        }
        
        if(message[1].equals("story_get") == true) {
        	Long id = Long.parseLong(message[0]);
        	storyApplication.getStoryKafka(id);
        }
        
        if(message[1].equals("story_create") == true) {
        	ObjectMapper mapper = new ObjectMapper();
        	StoryDto story = mapper.readValue(message[0], StoryDto.class);
        	storyApplication.createStoryKafka(story);
        }
        
        if(message[1].equals("story_update") == true) {
        	StoryDto dto = storyFactory.fromJson(message[0]);
        	storyApplication.updateStoryKafka(dto);
        }
        
        if(message[1].equals("story_sprint") == true) {
        	Long id = Long.parseLong(message[0]);
        	storyApplication.getStoriesBySprintKafka(id);
        }
        
        
        
    }

}