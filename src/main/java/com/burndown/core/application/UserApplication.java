package com.burndown.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.Role;
import com.burndown.core.entity.User;
import com.burndown.dto.UserLargeDto;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.exceptions.UserNotFoundException;
import com.burndown.factory.UserFactory;
import com.burndown.repository.RoleRepository;
import com.burndown.repository.UserRepository;

@Service
public class UserApplication {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  RoleRepository roleRepository;
	
	@Autowired
	private UserFactory userfactory;
	
	public User getUserbyID(Long id) throws UserNotFoundException {
		
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		}
	
	public void createnewUser (User user) {
		userRepository.save(user);
	}
	
	public Iterable<User> getUser(){
		return userRepository.findAll();
	}
	
	public UserLargeDto getLogin(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		Role role = user.getRole();
		PersonalData data = user.getPersonalData();
		UserLargeDto dto = userfactory.toLargeDto(user, data, role);
		return dto;
	}
}
