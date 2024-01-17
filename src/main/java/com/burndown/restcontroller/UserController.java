package com.burndown.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burndown.core.application.UserApplication;
import com.burndown.core.entity.Role;
import com.burndown.dto.UserLargeDto;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserApplication userappliaktion;
	
	@GetMapping(value="/login/{id}")
	public ResponseEntity<UserLargeDto> getRoleByID(@PathVariable(value = "id") Long id){
		
		UserLargeDto user = userappliaktion.getLogin(id);
		
		if(user != null) {
			return new ResponseEntity<>(
				      user, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
				      null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
