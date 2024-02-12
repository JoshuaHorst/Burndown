package com.burndown.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.Story;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.StoryDto;
import com.burndown.dto.UserDto;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.repository.ProduktBacklogRepository;
import com.burndown.repository.SprintBacklogRepository;
import com.burndown.repository.UserRepository;

@Service
public class StoryFactory {
	
	@Autowired
	UserFactory userFactory;
	
	@Autowired
	SprintBacklogRepository sprintBacklogRepository;
	
	@Autowired
	ProduktBacklogRepository producktBacklogRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SprintBacklogFactory sprintFactory;
	
	public Story toEntity(StoryDto dto){

	 Optional<SprintBacklog> optsprint = sprintBacklogRepository.findById(dto.getSprintbacklog());
	 Optional<ProducktBacklog> optprod = producktBacklogRepository.findById(dto.getProduktbacklog());
	 
	 SprintBacklog sprint = optsprint.get();
	 ProducktBacklog prod = optprod.get();
	 
	if(optsprint.isPresent()&&optprod.isPresent()) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(dto.getId()!=null) {
			return new Story(dto.getId(), dto.getDescription(), userFactory.toEntity(dto.getCreator()), userFactory.toEntity(dto.getAssigne()), sprint, prod, dto.getStorypoints(), LocalDate.parse(dto.getCreationDate(), formatter), LocalDate.parse(dto.getClosingDate(), formatter), dto.isDone());
		}else {
			if(dto.getAssigne()==null) {
				if(dto.getClosingDate()==null) {
			return new Story(new Long(1000),dto.getDescription(), userFactory.toEntity(dto.getCreator()), null, sprint, prod, dto.getStorypoints(), LocalDate.parse(dto.getCreationDate(), formatter), null, dto.isDone());
			}else {
				return new Story(new Long(1000),dto.getDescription(), userFactory.toEntity(dto.getCreator()), null, sprint, prod, dto.getStorypoints(), LocalDate.parse(dto.getCreationDate(), formatter), LocalDate.parse(dto.getClosingDate(), formatter), dto.isDone());
			}
			}else {
				if(dto.getClosingDate()==null) {
					return new Story(new Long(1000),dto.getDescription(), userFactory.toEntity(dto.getCreator()), userFactory.toEntity(dto.getAssigne()), sprint, prod, dto.getStorypoints(), LocalDate.parse(dto.getCreationDate(), formatter), null, dto.isDone());
					}else {
						return new Story(new Long(1000),dto.getDescription(), userFactory.toEntity(dto.getCreator()), userFactory.toEntity(dto.getAssigne()), sprint, prod, dto.getStorypoints(), LocalDate.parse(dto.getCreationDate(), formatter), LocalDate.parse(dto.getClosingDate(),formatter), dto.isDone());
				
			}
		
		}
	}}
	return null;
	}
	
	public StoryDto toDto(Story story) {
		UserDto user =null ;
		String p = null;
		if(story.getAssigne()!=null){ user= userFactory.toDto(story.getAssigne());}
		if(story.getClosingDate()!=null) {p =story.getClosingDate().toString();}
		return new StoryDto(story.getId(), story.getDescription(), userFactory.toDto(story.getCreator()), user,story.getSprintbacklog().getId(), story.getProduktbacklog().getId(), story.getStorypoints(), story.getCreationDate().toString(), p, story.isDone());
		
	}
	
	public StoryDto fromJson(String p) {
		StoryDto story = new StoryDto();
		String[] f= p.split(",");
		String[] n =f[0].split(":");
		if(n[1].equals("null")==false) {
		System.out.println(f[0]);
		story.setId(Long.parseLong(n[1]));
		}
		n =f[1].split(":");
		story.setDescription(n[1]);
		n =f[2].split(":");
		story.setCreator(userFactory.toDto(userRepository.findById(Long.parseLong(n[1])).orElseThrow()));
		n =f[3].split(":");
		story.setSprintbacklog(Long.parseLong(n[1]));
		n =f[4].split(":");
		story.setProduktbacklog(Long.parseLong(n[1]));
		n =f[5].split(":");
		story.setStorypoints(Integer.parseInt(n[1]));
		n =f[6].split(":");
		story.setCreationDate(n[1].replace("\"", ""));
		n =f[7].split(":");
		story.setDone(false);
		return story;
	}

}
