package com.burndown.core.entity;

import java.util.Set;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="sprintbacklog")
public class SprintBacklog {

	@Id
	@Column(name = "sprint_id")
	private Long id;
	
	@OneToMany
	private Set<Story> story;
	
    @OneToOne
    @MapsId
    @JoinColumn(name = "sprint_id")
	private Sprint sprint;

	public SprintBacklog(Sprint sprint) {
		super();
		this.sprint = sprint;
	}
	
	public SprintBacklog() {
	}

	public SprintBacklog(Long id, Sprint sprint, Set<Story> story) {
		super();
		this.id = id;
		this.story = story;
		this.sprint = sprint;
	}

	public SprintBacklog(Set<Story> story, Sprint sprint) {
		super();
		this.story = story;
		this.sprint = sprint;
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

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

    
}
