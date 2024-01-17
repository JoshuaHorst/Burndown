package com.burndown.core.entity;

import java.util.Set;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="produktbacklog")
public class ProducktBacklog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="produktbacklog")
	@JsonManagedReference
	private Set<Story> story;
	
	private String name;
	
	public ProducktBacklog(Long id, Set<Story> story, String name) {
		super();
		this.id = id;
		this.story = story;
		this.name = name;
	}

	public ProducktBacklog(Set<Story> story, String name) {
		super();
		this.story = story;
		this.name = name;
	}
	

	public ProducktBacklog() {}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
