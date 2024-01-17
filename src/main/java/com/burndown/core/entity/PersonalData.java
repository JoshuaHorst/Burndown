package com.burndown.core.entity;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name="personaldata")
@Table(name="personaldata")
public class PersonalData {

	@Id
	@Column(name = "user_id")
	private Long id;
	
	private String name;
	
	private LocalDate birthday;
	
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
	private User user;

	public PersonalData(Long id, String name, LocalDate birthday, User user) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.user= user;
	}
	
	public PersonalData() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	
	
}
