package com.burndown.core.entity;

import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name="sprint")
public class Sprint {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date start;
	
	private Date end;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	@JsonBackReference
	private User creator;
	
    @OneToOne
    @PrimaryKeyJoinColumn
	private SprintBacklog sprintBacklog;
	
	
    
	public Sprint(Date start, Date end, User creator) {
		this.start = start;
		this.end = end;
		this.creator = creator;
	}
	
	public Sprint() {}
	

	public Sprint(Long id, Date start, Date end, User creator) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.creator = creator;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	

}
