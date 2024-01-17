package com.burndown.dto;

import java.util.Date;
import com.burndown.core.entity.User;



public class SprintDto {
	
	private Long id;
	
	private Date start;
	
	private Date end;

	private User creator;
	
	
	public SprintDto(Long id, Date start, Date end, User creator) {
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
