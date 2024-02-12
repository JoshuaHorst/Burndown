package com.burndown.factory;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.application.PersonalDataApplication;
import com.burndown.core.application.UserApplication;
import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.ProducktBacklog;
import com.burndown.core.entity.User;
import com.burndown.dto.PersonalDataDto;
import com.burndown.dto.ProduktBacklogDto;
import com.burndown.exceptions.UserNotFoundException;
import com.burndown.repository.UserRepository;

@Service
public class PersonalDataFactory {
	
	@Autowired
	private UserRepository userRepository;
	
	public PersonalData toEntity(PersonalDataDto dto){
			User user =userRepository.findById(dto.getId()).orElseThrow(() -> new UserNotFoundException(dto.getId()));
			return new PersonalData(dto.getId(),dto.getName(),LocalDate.parse(dto.getBirthday()),user);
	}
	
	public PersonalDataDto toDto(PersonalData personalData) {
		return new PersonalDataDto(personalData.getId(), personalData.getName(), personalData.getBirthday().toString());
	}
}


