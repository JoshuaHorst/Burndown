package com.burndown.dto;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class StoryDto {
	
	private Long id;
	
	private String description;
	private User creator;
	private User assigne;
	private SprintBacklog sprintbacklog;
	private ProducktBacklog produktbacklog;
	private int storypoints;
	private Date creationDate;
	private Date closingDate;
	private boolean done;
	
	
	public StoryDto(Long id, String description, User creator, User assigne, SprintBacklog sprintbacklog,
			ProducktBacklog produktbacklog, int storypoints, Date creationDate, Date closingDate, boolean done) {
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


	public StoryDto(String description, User creator, User assigne, SprintBacklog sprintbacklog,
			ProducktBacklog produktbacklog, int storypoints, Date creationDate, Date closingDate, boolean done) {
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


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public User getAssigne() {
		return assigne;
	}


	public void setAssigne(User assigne) {
		this.assigne = assigne;
	}


	public SprintBacklog getSprintbacklog() {
		return sprintbacklog;
	}


	public void setSprintbacklog(SprintBacklog sprintbacklog) {
		this.sprintbacklog = sprintbacklog;
	}


	public ProducktBacklog getProduktbacklog() {
		return produktbacklog;
	}


	public void setProduktbacklog(ProducktBacklog produktbacklog) {
		this.produktbacklog = produktbacklog;
	}


	public int getStorypoints() {
		return storypoints;
	}


	public void setStorypoints(int storypoints) {
		this.storypoints = storypoints;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getClosingDate() {
		return closingDate;
	}


	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}


	public boolean isDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}
	
	
	
	
}
