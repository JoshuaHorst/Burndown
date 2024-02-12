package com.burndown.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;
import com.burndown.factory.SpringFactory;
import com.burndown.factory.SprintBacklogFactory;
import com.burndown.repository.SprintBacklogRepository;
import com.burndown.repository.SprintRepository;
import com.burndown.restcontroller.MessagePtoducerKafka;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SprintApplication {
	
	@Autowired
	SprintRepository sprintrepository;
	
	@Autowired
	SprintBacklogFactory sprintbacklogfactory;
	
	@Autowired
	SpringFactory sprintfactory;
	
	@Autowired
	SprintBacklogRepository sprintbacklogrepository;
	
	 @Autowired
	 private MessagePtoducerKafka messageProducer;
	 
	
	@Autowired
	private RabbitAnswer rabbitAnswer;
	
	public void createSprint(SprintDto sprint, SprintBacklogDto sprintbacklog ) throws JsonProcessingException {
		
		Sprint t = sprintrepository.save(sprintfactory.toEntity(sprint));
		sprintbacklog.setId(t.getId());
		SprintBacklog f = sprintbacklogrepository.save(sprintbacklogfactory.toEntity(sprintbacklog));
		SprintBacklogDto dto2 = sprintbacklogfactory.toDto(f);
		SprintDto dto = sprintfactory.toDto(t);
		
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat(":sprint_create");
		rabbitAnswer.sendAnswer(message);
	}
	
public void createSprintKafka(SprintDto sprint, SprintBacklogDto sprintbacklog ) throws JsonProcessingException {
		
		Sprint t = sprintrepository.save(sprintfactory.toEntity(sprint));
		sprintbacklog.setId(t.getId());
		SprintBacklog f = sprintbacklogrepository.save(sprintbacklogfactory.toEntity(sprintbacklog));
		SprintBacklogDto dto2 = sprintbacklogfactory.toDto(f);
		SprintDto dto = sprintfactory.toDto(t);
		
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat(":sprint_create");
		messageProducer.sendMessage("my-topic", message);
	}
	
	
	
	
	

}
