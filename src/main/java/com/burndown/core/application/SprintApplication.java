package com.burndown.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Sprint;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;
import com.burndown.factory.SpringFactory;
import com.burndown.factory.SprintBacklogFactory;
import com.burndown.repository.SprintBacklogRepository;
import com.burndown.repository.SprintRepository;

@Service
public class SprintApplication {
	
	@Autowired
	SprintRepository sprintrepository;
	
	@Autowired
	SprintBacklogFactory sprintbacklogfactory;
	
	@Autowired
	SpringFactory sprintfactory;
	
	@Autowired
	SprintBacklogRepository sprintbacklogrepository;
	
	public void createSprint(SprintDto sprint, SprintBacklogDto sprintbacklog ) {
		
		Sprint t = sprintrepository.save(sprintfactory.toEntity(sprint));
		sprintbacklog.setId(t.getId());
		sprintbacklogrepository.save(sprintbacklogfactory.toEntity(sprintbacklog));
		
	}
	

}
