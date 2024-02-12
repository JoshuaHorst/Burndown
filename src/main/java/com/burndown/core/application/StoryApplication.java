package com.burndown.core.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.Story;
import com.burndown.dto.StoryDto;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.exceptions.SprintNotFoundException;
import com.burndown.exceptions.StoryNotFoundException;
import com.burndown.factory.SpringFactory;
import com.burndown.factory.StoryFactory;
import com.burndown.factory.UserFactory;
import com.burndown.repository.SprintRepository;
import com.burndown.repository.StoryRepository;
import com.burndown.repository.UserRepository;
import com.burndown.restcontroller.MessagePtoducerKafka;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

@Service
public class StoryApplication {

	@Autowired
	StoryRepository storyrepository;
	
	@Autowired
	UserRepository userrepositroy;
	
	@Autowired
	UserFactory userFactory;
	
	@Autowired
	StoryFactory storyfactory;
	
	@Autowired
	private RabbitAnswer rabbitAnswer;
	
    @Autowired
    private MessagePtoducerKafka messageProducer;
    
    @Autowired
	SprintRepository sprintrepository;
    
    @Autowired
	SpringFactory sprintfactory;
    
	@Transactional
	public void updateStorypoints(Long id, int points ) throws JsonProcessingException {
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		story.setStorypoints(points);
		StoryDto dto = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_points");
		rabbitAnswer.sendAnswer(message);
	}
	
	public void setstorydone(Long id) throws JsonProcessingException{
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new StoryNotFoundException(id));
		story.setDone(true);
		story.setClosingDate(LocalDate.now());
		story = storyrepository.save(story);
		StoryDto dto = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_done");
		rabbitAnswer.sendAnswer(message);
		}

	
	public void createStory(StoryDto dto) throws JsonProcessingException {
		System.out.println(dto.getDescription());
		Story story = storyfactory.toEntity(dto);
		story = storyrepository.save(story);
		StoryDto dto2 = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto2);
		message = message.concat("/story_create");
		rabbitAnswer.sendAnswer(message);

	}
	
	@Transactional
	public void updateStory(StoryDto dto ) throws JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Story story =storyrepository.findById(dto.getId()).orElseThrow(() -> new RoleNotFoundException(dto.getId()));
		story.setStorypoints(dto.getStorypoints());
		story.setDescription(dto.getDescription());
		if(story.getClosingDate()!=null) {
		story.setClosingDate(LocalDate.parse(dto.getClosingDate(), formatter));
		}
		StoryDto dto2 = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto2);
		message = message.concat("/story_update");
		rabbitAnswer.sendAnswer(message);

	}
	
	public void getStory(Long id) throws JsonProcessingException {
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		StoryDto dto = storyfactory.toDto(story);
		dto.setAssigne(null);
		dto.setCreator(null);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_get");
		rabbitAnswer.sendAnswer(message);

		
	}
	
	public void getStoriesBySprint(Long id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		Iterable<Story> stories = storyrepository.findAll();
		List<Story> storiesList = new ArrayList<Story>();
		List<Story> storiesAnswer = new ArrayList<Story>();
		stories.forEach(storiesList::add);
		for(int i=0; i<storiesList.size(); i++) {
			if(storiesList.get(i).getSprintbacklog().getId()==id) {
				storiesAnswer.add(storiesList.get(i));
			}
		}
		
		List<StoryDto> dtoList = new ArrayList<StoryDto>();
		String message = new String();
		for(int i=0; i<storiesAnswer.size(); i++) {
			dtoList.add(storyfactory.toDto(storiesAnswer.get(i)));
			if(i<0) {
			message.concat("%"+mapper.writeValueAsString(storyfactory.toDto(storiesAnswer.get(i))));
			}else {
				message.concat(mapper.writeValueAsString(storyfactory.toDto(storiesAnswer.get(i))));
			}
		}
		message.concat("/story_sprint");
		rabbitAnswer.sendAnswer(message);
		
		
		
	}
	
	@Transactional
	public void updateStorypointsKafka(Long id, int points ) throws JsonProcessingException {
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		story.setStorypoints(points);
		StoryDto dto = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_points");
		messageProducer.sendMessage("my-topic", message);
		
	}
	
	public void setstorydoneKafka(Long id) throws JsonProcessingException{
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new StoryNotFoundException(id));
		story.setDone(true);
		story.setClosingDate(LocalDate.now());
		story = storyrepository.save(story);
		StoryDto dto = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_done");
		messageProducer.sendMessage("my-topic", message);
		
		}

	
	public void createStoryKafka(StoryDto dto) throws JsonProcessingException {
		System.out.println(dto.getDescription());
		Story story = storyfactory.toEntity(dto);
		story = storyrepository.save(story);
		StoryDto dto2 = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto2);
		message = message.concat("/story_create");
		messageProducer.sendMessage("my-topic", message);
		

	}
	
	@Transactional
	public void updateStoryKafka(StoryDto dto ) throws JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Story story =storyrepository.findById(dto.getId()).orElseThrow(() -> new RoleNotFoundException(dto.getId()));
		story.setStorypoints(dto.getStorypoints());
		story.setDescription(dto.getDescription());
		if(story.getClosingDate()!=null) {
		story.setClosingDate(LocalDate.parse(dto.getClosingDate(), formatter));
		}
		StoryDto dto2 = storyfactory.toDto(story);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto2);
		message = message.concat("/story_update");
		messageProducer.sendMessage("my-topic", message);
		

	}
	
	public void getStoryKafka(Long id) throws JsonProcessingException {
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		StoryDto dto = storyfactory.toDto(story);
		dto.setAssigne(null);
		dto.setCreator(null);
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		   mapper.registerModule(new JavaTimeModule());
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/story_get");
		messageProducer.sendMessage("my-topic", message);
		

		
	}
	
	public void getStoriesBySprintKafka(Long id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		Iterable<Story> stories = storyrepository.findAll();
		List<Story> storiesList = new ArrayList<Story>();
		List<Story> storiesAnswer = new ArrayList<Story>();
		stories.forEach(storiesList::add);
		for(int i=0; i<storiesList.size(); i++) {
			if(storiesList.get(i).getSprintbacklog().getId()==id) {
				storiesAnswer.add(storiesList.get(i));
			}
		}
		
		List<StoryDto> dtoList = new ArrayList<StoryDto>();
		String message = new String();
		for(int n=0; n<storiesAnswer.size(); n++) {
			dtoList.add(storyfactory.toDto(storiesAnswer.get(n)));
			if(n>1) {
			message= message.concat("%"+mapper.writeValueAsString(storyfactory.toDto(storiesAnswer.get(n))));
			}else {
			message = message.concat(mapper.writeValueAsString(storyfactory.toDto(storiesAnswer.get(n))));
			}
		}
		Sprint sprint = sprintrepository.findById(id).orElseThrow(() -> new SprintNotFoundException(id));
		message = message.concat("%"+mapper.writeValueAsString(sprintfactory.toDto(sprint)));
		message = message.concat("/story_sprint");
		
		messageProducer.sendMessage("my-topic", message);
		System.out.println(message);
		
		
		
	}
	
}
