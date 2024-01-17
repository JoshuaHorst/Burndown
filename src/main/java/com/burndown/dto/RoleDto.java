package com.burndown.dto;

import java.util.Set;

import com.burndown.core.entity.User;

import jakarta.persistence.OneToMany;

public class RoleDto {
	
	private Long id;
	private String roleName;
	private String permissions;
	private Set<User> user;
	
	
	
	public RoleDto(Long id, String roleName, String permissions, Set<User> user) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
		this.user = user;
	}
	
	public RoleDto(Long id, String roleName, String permissions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
	}




	public RoleDto(String roleName, String permissions) {
		super();
		this.roleName = roleName;
		this.permissions = permissions;
	}




	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
