package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.Sprint;
import com.burndown.dto.ProduktBacklogDto;
import com.burndown.dto.SprintDto;

@Service
public class ProduktBacklogFactory<BrodukBacklogDto> {

	public ProducktBacklog toEntity(ProduktBacklogDto dto){
		if(dto.getId()!=null) {
			return new ProducktBacklog (dto.getId(), dto.getStory(), dto.getName());
		}else {
			return new ProducktBacklog (dto.getStory(), dto.getName());
		}
	}
	
	public ProduktBacklogDto toDto(ProducktBacklog backlog) {
		return new ProduktBacklogDto(backlog.getId(), backlog.getStory(), backlog.getName());
		
	}
}


