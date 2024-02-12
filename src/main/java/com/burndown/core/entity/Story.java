package com.burndown.core.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="story")
public class Story {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="storycreator_user", nullable=false)
	@JsonBackReference
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="storyAssignee_user")
	@JsonBackReference
	private User assigne;
	
	@ManyToOne
	@JoinColumn(name="story_sprintbacklog")
	@JsonBackReference
	private SprintBacklog sprintbacklog;
	
	@ManyToOne
	@JoinColumn(name="id_produktbacklog", nullable=false)
	@JsonBackReference
	private ProducktBacklog produktbacklog;
	
	private int storypoints;
	
	private LocalDate creationDate;
	
	private LocalDate closingDate;
	
	private boolean done;
	

	public Story(Long id, String description, User creator, User assigne, SprintBacklog sprintbacklog,
			ProducktBacklog produktbacklog, int storypoints, LocalDate localDate, LocalDate localDate2, boolean done) {
		super();
		this.id = id;
		this.description = description;
		this.creator = creator;
		this.assigne = assigne;
		this.sprintbacklog = sprintbacklog;
		this.produktbacklog = produktbacklog;
		this.storypoints = storypoints;
		this.creationDate = localDate;
		this.closingDate = localDate2;
		this.done = done;
	}
	
	
	public Story() {}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}
	
	
	
}
