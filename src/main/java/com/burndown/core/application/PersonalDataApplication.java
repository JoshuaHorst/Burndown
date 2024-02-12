package com.burndown.core.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.PersonalData;
import com.burndown.dto.PersonalDataDto;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.factory.PersonalDataFactory;
import com.burndown.repository.PersonalDataRepository;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class PersonalDataApplication {
	
	@Autowired
	private PersonalDataRepository personalDataRepository;
	
	@Autowired
	private PersonalDataFactory personalDataFactory;
	
	@Autowired
	private RabbitAnswer rabbitAnswer;
	


	public void getPersonalDataByID(Long id) throws PersonalDataNotFoundException, JsonProcessingException {
	
		PersonalData data = personalDataRepository.findById(id).orElseThrow(() -> new PersonalDataNotFoundException(id));
		PersonalDataDto dto = personalDataFactory.toDto(data);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(":data_by_id");
		rabbitAnswer.sendAnswer(message);
		
	}
	
	public Iterable<PersonalData> getPersonalData(){
		return personalDataRepository.findAll();
	}
	
	public void createPersonalData(PersonalData data) {
		personalDataRepository.save(data);
		
	}
}
