package com.burndown.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.dto.SprintDto;

@Service
public class SpringFactory {
	
	@Autowired
	private UserFactory userFactory;
	
	public Sprint toEntity(SprintDto dto){
		if(dto.getId()!=null) {
		return new Sprint (dto.getId(), dto.getStart(), dto.getEnd(), userFactory.toEntity(dto.getCreator()));
		}else {
			return new Sprint (dto.getStart(), dto.getEnd(), userFactory.toEntity(dto.getCreator()));
		}
	}
	
	public SprintDto toDto(Sprint sprint) {
		return new SprintDto(sprint.getId(), sprint.getStart(),sprint.getEnd(), userFactory.toDto( sprint.getCreator()));
		
	}
}
