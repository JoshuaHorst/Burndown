package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;


@Service
public class SprintBacklogFactory {
	
	SpringFactory sprintFacoty;
	
	public SprintBacklog toEntity(SprintBacklogDto dto){
		if(dto.getId()!=null) {
			return new SprintBacklog(dto.getId(), sprintFacoty.toEntity(dto.getSprint()), null);
		}else {
			return new SprintBacklog(null, sprintFacoty.toEntity(dto.getSprint()));
		}
	}
	
	public SprintBacklogDto toDto(SprintBacklog sprintbacklog) {
		return new SprintBacklogDto(sprintbacklog.getId(), null, sprintFacoty.toDto(sprintbacklog.getSprint()));
		
	}
	
}
