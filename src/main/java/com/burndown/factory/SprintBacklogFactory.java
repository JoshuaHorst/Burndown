package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;


@Service
public class SprintBacklogFactory {
	
	public SprintBacklog toEntity(SprintBacklogDto dto){
		if(dto.getId()!=null) {
			return new SprintBacklog(dto.getId(), dto.getSprint(), dto.getStory());
		}else {
			return new SprintBacklog(dto.getStory(), dto.getSprint());
		}
	}
	
	public SprintBacklogDto toDto(SprintBacklog sprintbacklog) {
		return new SprintBacklogDto(sprintbacklog.getId(), sprintbacklog.getStory(), sprintbacklog.getSprint());
		
	}
	
}
