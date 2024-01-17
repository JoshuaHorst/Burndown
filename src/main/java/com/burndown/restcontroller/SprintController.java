package com.burndown.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burndown.core.application.SprintApplication;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.SprintDto;

@RestController
@RequestMapping(value="/sprint")
public class SprintController {
	
	SprintApplication sprintapplication;
	
	@PostMapping("/create")
	public ResponseEntity createSprintController(@RequestBody SprintDto sprint, SprintBacklogDto backlog ){
		
		
		sprintapplication.createSprint(sprint, backlog);
		
		
		return new ResponseEntity<>(
			      null, HttpStatus.OK);
	}
	}
	


