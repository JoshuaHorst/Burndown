package com.burndown.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.ProducktBacklog;
import com.burndown.dto.ProduktBacklogDto;
import com.burndown.factory.ProduktBacklogFactory;
import com.burndown.repository.ProduktBacklogRepository;
import com.burndown.restcontroller.MessagePtoducerKafka;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProduktBacklogApplication {
	
	
	@Autowired
	ProduktBacklogRepository producktBacklogRepository;
	
	@Autowired
	ProduktBacklogFactory productBacklogFactory;
	
	@Autowired
	RabbitAnswer rabbitAnswer;
	
    @Autowired
    private MessagePtoducerKafka messageProducer;
	
	public void createProducktBacklog(ProduktBacklogDto dto) throws JsonProcessingException {
		ProducktBacklog backlog = producktBacklogRepository.save(productBacklogFactory.toEntity(dto));
		ProduktBacklogDto logDto = productBacklogFactory.toDto(backlog);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(logDto);
		message = message.concat("/backlog_create");
		rabbitAnswer.sendAnswer(message);
	}
	
	public void deleteProducktBacklog(Long id) {
		producktBacklogRepository.deleteById(id);
		String message = id.toString() + "has been Deteletd:backlog_delete";
		rabbitAnswer.sendAnswer(message);
		}
	

	public void createProducktBacklogKafka(ProduktBacklogDto dto) throws JsonProcessingException {
		ProducktBacklog backlog = producktBacklogRepository.save(productBacklogFactory.toEntity(dto));
		ProduktBacklogDto logDto = productBacklogFactory.toDto(backlog);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(logDto);
		message = message.concat("/backlog_create");
		messageProducer.sendMessage("my-topic", message);;
	}
	
	public void deleteProducktBacklogKafka(Long id) {
		producktBacklogRepository.deleteById(id);
		String message = id.toString() + "has been Deteletd:backlog_delete";
		messageProducer.sendMessage("my-topic", message);;
		}
}
