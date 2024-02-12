package com.burndown.core.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burndown.core.entity.Role;
import com.burndown.dto.RoleDto;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.factory.RoleFactory;
import com.burndown.repository.RoleRepository;
import com.burndown.restcontroller.MessagePtoducerKafka;
import com.burndown.restcontroller.RabbitAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class RoleApplication {

	
	private  RoleRepository roleRepository;

	
	private RoleFactory roleFactory;
	
	@Autowired
	private RabbitAnswer rabbitAnswer;
	
    @Autowired
    private MessagePtoducerKafka messageProducer;

	
	
	@Autowired
	public RoleApplication(RoleRepository roleRepository, RoleFactory roleFactory) {
		super();
		this.roleRepository = roleRepository;
		this.roleFactory = roleFactory;
	}



	public RoleApplication() {
	}

	
	
	public void createnewrole(RoleDto roleDto) throws JsonProcessingException {
		Role role = roleRepository.save(roleFactory.toEntity(roleDto));
		roleDto = roleFactory.toDto(role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(roleDto);
		message = message.concat("/role_update");
		rabbitAnswer.sendAnswer(message);
		
	}
	
	@Transactional
	public void updateRole(RoleDto roleDto)throws JsonProcessingException {
		Role role =roleRepository.findById(roleDto.getId()).orElseThrow(() -> new RoleNotFoundException(roleDto.getId()));
		role.setPermissions(roleDto.getPermissions());
		role.setRoleName(roleDto.getRoleName());
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(role);
		message = message.concat("/role_update");
		rabbitAnswer.sendAnswer(message);
	}
	
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
		String message = id.toString() + "has been Deteletd:role_delete";
		rabbitAnswer.sendAnswer(message);
		
	}
	
	public Iterable<Role> getRole(){
		return roleRepository.findAll();
	}

	
	public void getRoleByIDAnswer(Long id) throws JsonProcessingException {
		System.out.println("test" + id);
		System.out.println("");
		
		Role r = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		RoleDto role = roleFactory.toDto(r);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(role);
		message = message.concat("/role_by_id");
		rabbitAnswer.sendAnswer(message);
	}
	
	public void getRoleByIDAnswerKafka(Long id) throws JsonProcessingException {
		System.out.println("test" + id);
		System.out.println("");
		
		Role r = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		RoleDto role = roleFactory.toDto(r);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(role);
		message = message.concat("/role_by_id");
		messageProducer.sendMessage("my-topic", message);
	}
	
	public void createnewroleKafka(RoleDto roleDto) throws JsonProcessingException {
		Role role = roleRepository.save(roleFactory.toEntity(roleDto));
		roleDto = roleFactory.toDto(role);
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(roleDto);
		message = message.concat("/role_update");
		messageProducer.sendMessage("my-topic", message);
		
	}
	
	@Transactional
	public void updateRoleKafka(RoleDto roleDto)throws JsonProcessingException {
		Role role =roleRepository.findById(roleDto.getId()).orElseThrow(() -> new RoleNotFoundException(roleDto.getId()));
		role.setPermissions(roleDto.getPermissions());
		role.setRoleName(roleDto.getRoleName());
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(role);
		message = message.concat("/role_update");
		messageProducer.sendMessage("my-topic", message);;
	}
	
	public void deleteRoleKafka(Long id) {
		roleRepository.deleteById(id);
		String message = id.toString() + "has been Deteletd:role_delete";
		rabbitAnswer.sendAnswer(message);
		
	}

}
