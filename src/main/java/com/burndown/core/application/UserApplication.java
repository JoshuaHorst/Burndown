package com.burndown.core.application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.Role;
import com.burndown.core.entity.User;
import com.burndown.dto.PersonalDataDto;
import com.burndown.dto.RoleDto;
import com.burndown.dto.UserDto;
import com.burndown.dto.UserLargeDto;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.exceptions.UserNotFoundException;
import com.burndown.factory.PersonalDataFactory;
import com.burndown.factory.RoleFactory;
import com.burndown.factory.UserFactory;
import com.burndown.repository.PersonalDataRepository;
import com.burndown.repository.RoleRepository;
import com.burndown.repository.UserRepository;
import com.burndown.restcontroller.MessagePtoducerKafka;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserApplication {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  RoleRepository roleRepository;
	
	@Autowired
	private PersonalDataRepository personalDataRepositoy;
	
	@Autowired
	private PersonalDataFactory personalDataFactory;
	
	@Autowired
	private UserFactory userfactory;
	
	@Autowired
	private RabbitAnswer rabbitAnswer;
	
	@Autowired
	private RoleFactory roleFactory;
	
    @Autowired
    private MessagePtoducerKafka messageProducer;
	
	public void getUserbyID(Long id) throws UserNotFoundException, JsonProcessingException {
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		UserDto dto = userfactory.toDto(user);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(":user_get_id");
		rabbitAnswer.sendAnswer(message);
		
		}
	
	@Transactional
	public void createnewUser (UserDto userdto) throws JsonProcessingException {
		User user = userRepository.save(userfactory.toEntity(userdto));
		PersonalData data = new PersonalData(null,userdto.getPersonalData().getName(), LocalDate.parse(userdto.getPersonalData().getBirthday()),user);
		personalDataRepositoy.save(data);
		user.setPersonalData(data);
		userRepository.save(user);
		UserDto dto = userfactory.toDto(user);
		PersonalDataDto dto2 = personalDataFactory.toDto(data);
		dto.setPersonalData(dto2);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat("/user_create");
		rabbitAnswer.sendAnswer(message);
		
	}
	
	@Transactional
	public void updateUser (UserDto userdto) throws JsonProcessingException {
		User user = userRepository.findById(userdto.getId()).orElseThrow(() -> new UserNotFoundException(userdto.getId()));
		Role role = roleRepository.findById(userdto.getRole().getId()).orElseThrow(() -> new RoleNotFoundException(userdto.getRole().getId()));
		user.setRole(role);
		PersonalData personalData = personalDataRepositoy.findById(userdto.getId()).orElseThrow(() -> new PersonalDataNotFoundException(userdto.getId()));
		personalData.setName(userdto.getPersonalData().getName());
		System.out.println(userdto.getPersonalData().getBirthday());
		personalData.setBirthday(LocalDate.parse(userdto.getPersonalData().getBirthday()));
		UserDto dto = userfactory.toDto(user);
		PersonalDataDto dto2 = personalDataFactory.toDto(personalData);
		RoleDto dto3 = roleFactory.toDto(role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat(mapper.writeValueAsString(dto3));
		message = message.concat("/user_update");
		rabbitAnswer.sendAnswer(message);
		
	}
	
	
	public Iterable<User> getUser(){
		return userRepository.findAll();
	}
	
	public void getLogin(Long id) throws JsonProcessingException {
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		Role role = user.getRole();
		PersonalData data = user.getPersonalData();
		UserLargeDto dto = userfactory.toLargeDto(user, data, role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/user_login");
		rabbitAnswer.sendAnswer(message);
	}
	
	public void deleteUser(Long id) {	
		personalDataRepositoy.deleteById(id);
		userRepository.deleteById(id);
		String message = id.toString() + " has been Detelet/user_delete";
		rabbitAnswer.sendAnswer(message);
	}
	
	public void getUserbyIDKafka(Long id) throws UserNotFoundException, JsonProcessingException {
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		UserDto dto = userfactory.toDto(user);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(":user_get_id");
		messageProducer.sendMessage("my-topic", message);
		
		}
	
	@Transactional
	public void createnewUserKafka(UserDto userdto) throws JsonProcessingException {
		User user = userRepository.save(userfactory.toEntity(userdto));
		PersonalData data = new PersonalData(null,userdto.getPersonalData().getName(), LocalDate.parse(userdto.getPersonalData().getBirthday()),user);
		personalDataRepositoy.save(data);
		user.setPersonalData(data);
		userRepository.save(user);
		UserDto dto = userfactory.toDto(user);
		PersonalDataDto dto2 = personalDataFactory.toDto(data);
		dto.setPersonalData(dto2);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat("/user_create");
		 messageProducer.sendMessage("my-topic", message);
		
	}
	
	@Transactional
	public void updateUserKafka(UserDto userdto) throws JsonProcessingException {
		User user = userRepository.findById(userdto.getId()).orElseThrow(() -> new UserNotFoundException(userdto.getId()));
		Role role = roleRepository.findById(userdto.getRole().getId()).orElseThrow(() -> new RoleNotFoundException(userdto.getRole().getId()));
		user.setRole(role);
		PersonalData personalData = personalDataRepositoy.findById(userdto.getId()).orElseThrow(() -> new PersonalDataNotFoundException(userdto.getId()));
		personalData.setName(userdto.getPersonalData().getName());
		System.out.println(userdto.getPersonalData().getBirthday());
		personalData.setBirthday(LocalDate.parse(userdto.getPersonalData().getBirthday()));
		UserDto dto = userfactory.toDto(user);
		PersonalDataDto dto2 = personalDataFactory.toDto(personalData);
		RoleDto dto3 = roleFactory.toDto(role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat(mapper.writeValueAsString(dto2));
		message = message.concat(mapper.writeValueAsString(dto3));
		message = message.concat("/user_update");
		 messageProducer.sendMessage("my-topic", message);
		
	}

	
	public void getLoginKafka(Long id) throws JsonProcessingException {
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		Role role = user.getRole();
		PersonalData data = user.getPersonalData();
		UserLargeDto dto = userfactory.toLargeDto(user, data, role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(dto);
		message = message.concat("/user_login");
		 messageProducer.sendMessage("my-topic", message);
	}
	
	public void deleteUserKafka(Long id) {	
		personalDataRepositoy.deleteById(id);
		userRepository.deleteById(id);
		String message = id.toString() + " has been Detelet/user_delete";
		 messageProducer.sendMessage("my-topic", message);
	}
	
	
}
