package com.burndown.dto;


public class UserDto {

	private Long id;
	private RoleDto role;
	private PersonalDataDto personalData;
	
	
	public UserDto(Long id, RoleDto role, PersonalDataDto personalData) {
		super();
		this.id = id;
		this.role = role;
		this.personalData = personalData;
	}


	public UserDto(Long id, RoleDto role) {
		super();
		this.id = id;
		this.role = role;
	}

	public UserDto() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RoleDto getRole() {
		return role;
	}


	public void setRole(RoleDto role) {
		this.role = role;
	}


	public PersonalDataDto getPersonalData() {
		return personalData;
	}


	public void setPersonalData(PersonalDataDto personalData) {
		this.personalData = personalData;
	}
	
	
	
	
}
