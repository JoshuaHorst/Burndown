package com.burndown.core.entity;


import java.util.Set;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private PersonalData personalData;
	
	@ManyToOne
	@JoinColumn(name="role_user", nullable=false)
	@JsonBackReference
	private Role role;
	
	@OneToMany(mappedBy="creator")
	@JsonManagedReference
	private Set<Story> storyCreator;
	
	@OneToMany(mappedBy="assigne")
	@JsonManagedReference
	private Set<Story> storyAssignee;
	
	@OneToMany(mappedBy="creator")
	@JsonManagedReference
	private Set<Sprint> sprint;

	public User(Long id, PersonalData personalData, Role role, Set<Story> storyCreator, Set<Story> storyAssignee,
			Set<Sprint> sprint) {
		super();
		this.id = id;
		this.personalData = personalData;
		this.role = role;
		this.storyCreator = storyCreator;
		this.storyAssignee = storyAssignee;
		this.sprint = sprint;
	}
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Story> getStoryCreator() {
		return storyCreator;
	}

	public void setStoryCreator(Set<Story> storyCreator) {
		this.storyCreator = storyCreator;
	}

	public Set<Story> getStoryAssignee() {
		return storyAssignee;
	}

	public void setStoryAssignee(Set<Story> storyAssignee) {
		this.storyAssignee = storyAssignee;
	}

	public Set<Sprint> getSprint() {
		return sprint;
	}

	public void setSprint(Set<Sprint> sprint) {
		this.sprint = sprint;
	}
	
	
	
	
	

}
