package com.burndown.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Story;
import com.burndown.dto.StoryDto;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.exceptions.StoryNotFoundException;
import com.burndown.factory.StoryFactory;
import com.burndown.repository.StoryRepository;
import com.burndown.repository.UserRepository;

@Service
public class StoryApplication {

	@Autowired
	StoryRepository storyrepository;
	
	@Autowired
	UserRepository userrepositroy;
	
	@Autowired
	StoryFactory storyfactory;
	
	public StoryDto updateStorypoints(Long id, int points ) {
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		story.setStorypoints(points);
		storyrepository.save(story);
		return storyfactory.toDto(story);
	}
	
	public StoryDto setstorydone(Long id){
		
		Story story =storyrepository.findById(id).orElseThrow(() -> new StoryNotFoundException(id));
		story.setDone(true);
		storyrepository.save(story);
		return storyfactory.toDto(story);
		}
	
}
