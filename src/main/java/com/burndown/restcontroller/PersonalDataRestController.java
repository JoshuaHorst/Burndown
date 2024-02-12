package com.burndown.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burndown.core.application.PersonalDataApplication;
import com.burndown.core.entity.PersonalData;

@RestController
@RequestMapping("personaldata")
public class PersonalDataRestController {

	@Autowired
	private PersonalDataApplication personalDataApplication;
	
	/*@GetMapping("/get/{id}")
	public ResponseEntity<PersonalData> getPersonalDataByID(@PathVariable(value = "id") Long id){
		
		PersonalData personalData = personalDataApplication.getPersonalDataByID(id);
		
		if(personalData != null) {
			return new ResponseEntity<>(
				      personalData, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
				      null, HttpStatus.BAD_REQUEST);
		}
		
	}*/
	
	@GetMapping("/get")
	public ResponseEntity<Iterable<PersonalData>> getPersonalData(){
		
		Iterable<PersonalData> personalData = personalDataApplication.getPersonalData();
		
		if(personalData != null) {
			return new ResponseEntity<>(
				      personalData, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
				      null, HttpStatus.BAD_REQUEST);
		}
		
	}
}