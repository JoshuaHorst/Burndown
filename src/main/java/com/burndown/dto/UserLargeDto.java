package com.burndown.dto;

import java.time.LocalDate;

import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.Role;
import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.Story;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

public class UserLargeDto {
	
	private Long id;
	private Long roleId;
	private String roleName;
	private String permissions;
	private String name;
	private String birthday;
	
	
	public UserLargeDto(Long id, Long roleId, String roleName, String permissions, String name, String birthday) {
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.permissions = permissions;
		this.name = name;
		this.birthday = birthday;
	}
	

	
	public UserLargeDto( Long roleId, String roleName, String permissions, String name, String birthday) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.permissions = permissions;
		this.name = name;
		this.birthday = birthday;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	
	
}
