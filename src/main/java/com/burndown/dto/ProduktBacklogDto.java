package com.burndown.dto;

import java.util.Set;

public class ProduktBacklogDto {


	private Long id;

	private Set<StoryDto> story;
	
	private String name;
	
	public ProduktBacklogDto(Set<StoryDto> story, String name) {
		this.story = story;
		this.name = name;
	}
	
	public ProduktBacklogDto(Long id, Set<StoryDto> story, String name) {

		this.id = id;
		this.story = story;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<StoryDto> getStory() {
		return story;
	}

	public void setStory(Set<StoryDto> story) {
		this.story = story;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
