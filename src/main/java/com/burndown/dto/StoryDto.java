package com.burndown.dto;

import java.util.Date;
import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.User;


public class StoryDto {
	
	private Long id;
	
	private String description;
	private UserDto creator;
	private UserDto assigne;
	private Long sprintbacklog;
	private Long produktbacklog;
	private int storypoints;
	private String creationDate;
	private String closingDate;
	private boolean done;


	public StoryDto(Long id, String description, UserDto creator, UserDto assigne, Long sprintbacklog,
			Long produktbacklog, int storypoints, String creationDate, String closingDate, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.creator = creator;
		this.assigne = assigne;
		this.sprintbacklog = sprintbacklog;
		this.produktbacklog = produktbacklog;
		this.storypoints = storypoints;
		this.creationDate = creationDate;
		this.closingDate = closingDate;
		this.done = done;
	}
	
	public StoryDto( String description, UserDto creator, UserDto assigne, Long sprintbacklog,
			Long produktbacklog, int storypoints, String creationDate, String closingDate, boolean done) {
		super();
		this.description = description;
		this.creator = creator;
		this.assigne = assigne;
		this.sprintbacklog = sprintbacklog;
		this.produktbacklog = produktbacklog;
		this.storypoints = storypoints;
		this.creationDate = creationDate;
		this.closingDate = closingDate;
		this.done = done;
	}
	
	public StoryDto(Long id, String description, int storypoints, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.storypoints = storypoints;
		this.done = done;
	}

	public StoryDto() {
		super();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStorypoints() {
		return storypoints;
	}


	public void setStorypoints(int storypoints) {
		this.storypoints = storypoints;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getClosingDate() {
		return closingDate;
	}


	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}


	public boolean isDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}

	public UserDto getAssigne() {
		return assigne;
	}

	public void setAssigne(UserDto assigne) {
		this.assigne = assigne;
	}

	public Long getSprintbacklog() {
		return sprintbacklog;
	}

	public void setSprintbacklog(Long sprintbacklog) {
		this.sprintbacklog = sprintbacklog;
	}

	public Long getProduktbacklog() {
		return produktbacklog;
	}

	public void setProduktbacklog(Long produktbacklog) {
		this.produktbacklog = produktbacklog;
	}
	
	
	
	
}
