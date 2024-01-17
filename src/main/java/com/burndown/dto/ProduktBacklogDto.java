package com.burndown.dto;

import java.util.Set;

import com.burndown.core.entity.Story;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


public class ProduktBacklogDto {


	private Long id;

	private Set<Story> story;
	
	private String name;
	
	public ProduktBacklogDto(Set<Story> story, String name) {
		this.story = story;
		this.name = name;
	}
	
	public ProduktBacklogDto(Long id, Set<Story> story, String name) {

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

	public Set<Story> getStory() {
		return story;
	}

	public void setStory(Set<Story> story) {
		this.story = story;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
