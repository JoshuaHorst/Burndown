package com.burndown.core.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.PersonalData;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.repository.PersonalDataRepository;


@Service
public class PersonalDataApplication {
	
	@Autowired
	private PersonalDataRepository personalDataRepository;
	


	public PersonalData getPersonalDataByID(Long id) throws PersonalDataNotFoundException {
	
	return personalDataRepository.findById(id).orElseThrow(() -> new PersonalDataNotFoundException(id));
	}
	
	public Iterable<PersonalData> getPersonalData(){
		return personalDataRepository.findAll();
	}
	
	public void createPersonalData(PersonalData data) {
		personalDataRepository.save(data);
		
	}
}
