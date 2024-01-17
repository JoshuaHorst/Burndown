package com.burndown.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burndown.core.application.RoleApplication;
import com.burndown.core.entity.Role;
import com.burndown.dto.RoleDto;



@RestController
@RequestMapping(value="/role")
public class RoleRestContoller {
	
	@Autowired
   private RoleApplication roleApplication;
	

	@GetMapping(value="/get/{id}")
	public ResponseEntity<RoleDto> getRoleByID(@PathVariable(value = "id") Long id){
		
		RoleDto Role = roleApplication.getRoleByID(id);
		
		if(Role.getPermissions() != null) {
			return new ResponseEntity<>(
				      Role, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
				      null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value= "/get")
	public ResponseEntity<Iterable<Role>> getRole(){
		
		Iterable<Role> Role = roleApplication.getRole();
		
		if(Role != null) {
			return new ResponseEntity<>(
				      Role, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
				      null, HttpStatus.BAD_REQUEST);
		}
		
	}
}
