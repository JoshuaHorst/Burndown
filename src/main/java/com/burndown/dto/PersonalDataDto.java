package com.burndown.dto;

import java.time.LocalDate;

public class PersonalDataDto {
	
	
	private Long id;
	private String name;
	private String birthday;
	
	
	public PersonalDataDto(Long id, String name, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	
	public PersonalDataDto() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
