package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.Story;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.StoryDto;

@Service
public class StoryFactory {
	
	
	public Story toEntity(StoryDto dto){
		if(dto.getId()!=null) {
			return new Story(dto.getId(), dto.getDescription(), dto.getCreator(), dto.getAssigne(), dto.getSprintbacklog(), dto.getProduktbacklog(), dto.getStorypoints(), dto.getCreationDate(), dto.getClosingDate(), dto.isDone());
		}else {
			return new Story(dto.getId(), dto.getDescription(), dto.getCreator(), dto.getAssigne(), dto.getSprintbacklog(), dto.getProduktbacklog(), dto.getStorypoints(), dto.getCreationDate(), dto.getClosingDate(), dto.isDone());
		}
	}
	
	public StoryDto toDto(Story story) {
		return new StoryDto(story.getId(), story.getDescription(), story.getCreator(), story.getAssigne(), story.getSprintbacklog(), story.getProduktbacklog(), story.getStorypoints(), story.getCreationDate(), story.getClosingDate(), story.isDone());
		
	}
}
