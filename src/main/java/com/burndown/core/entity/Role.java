package com.burndown.core.entity;


import java.util.Set;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity(name="ROLE")
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="role")
	@JsonManagedReference
	private Set<User> user;
	
	private String roleName;
	private String permissions;

	public Role(Long id, String roleName, String permissions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
	}
	
	
	
	public Role(Long id, Set<User> user, String roleName, String permissions) {
		super();
		this.id = id;
		this.user = user;
		this.roleName = roleName;
		this.permissions = permissions;
	}


	public Role( Set<User> user, String roleName, String permissions) {
		this.user = user;
		this.roleName = roleName;
		this.permissions = permissions;
	}

	public Role( String roleName, String permissions) {
		this.roleName = roleName;
		this.permissions = permissions;
	}
	public Role() {
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
	
}
