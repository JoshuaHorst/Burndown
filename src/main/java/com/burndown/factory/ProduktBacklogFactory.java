package com.burndown.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.Sprint;
import com.burndown.dto.ProduktBacklogDto;
import com.burndown.dto.SprintDto;

@Service
public class ProduktBacklogFactory<BrodukBacklogDto> {
	
	@Autowired
	StoryFactory storyFactory;

	public ProducktBacklog toEntity(ProduktBacklogDto dto){
		if(dto.getId()!=null) {
			return new ProducktBacklog (dto.getId(), null, dto.getName());
		}else {
			return new ProducktBacklog (null,dto.getName());
		}
	}
	
	public ProduktBacklogDto toDto(ProducktBacklog backlog) {
		return new ProduktBacklogDto(backlog.getId(), null, backlog.getName());
		
	}
}


