package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.dto.SprintDto;

@Service
public class SpringFactory {
	
	
	public Sprint toEntity(SprintDto dto){
		if(dto.getId()!=null) {
		return new Sprint (dto.getId(), dto.getStart(), dto.getEnd(), dto.getCreator());
		}else {
			return new Sprint (dto.getStart(), dto.getEnd(), dto.getCreator());
		}
	}
	
	public SprintDto toDto(Sprint sprint) {
		return new SprintDto(sprint.getId(), sprint.getStart(),sprint.getEnd(), sprint.getCreator());
		
	}
}
