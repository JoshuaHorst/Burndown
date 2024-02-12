package com.burndown.dto;

import java.util.Set;

import com.burndown.core.entity.User;

import jakarta.persistence.OneToMany;

public class RoleDto {
	
	private Long id;
	private String roleName;
	private String permissions;
	
	
	
	public RoleDto(Long id, String roleName, String permissions, Set<User> user) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
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
	public RoleDto() {}


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
