package com.burndown.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.burndown.core.application.StoryApplication;
import com.burndown.dto.RoleDto;
import com.burndown.dto.StoryDto;
import com.burndown.dto.UserLargeDto;

@RestController
@RequestMapping(value="/story")
public class StoryController {
	
	@Autowired
	StoryApplication storyApplication;
	
	@PostMapping(value="/done/{id}")
	public ResponseEntity<Void> setDoneByID(@PathVariable(value = "id") Long id){
		
			StoryDto story = storyApplication.setstorydone(id);
					
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value="/points/{id}")
	public ResponseEntity<Void> setPointsByID(@PathVariable(value = "id") Long id, @RequestParam String p){
		System.out.println(p);
		
			StoryDto story = storyApplication.updateStorypoints(id, Integer.valueOf(p));
					
			return new ResponseEntity<>(HttpStatus.OK);
	}

}
