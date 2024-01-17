package com.burndown.core.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.Role;
import com.burndown.dto.RoleDto;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.factory.RoleFactory;
import com.burndown.repository.RoleRepository;

@Service
public class RoleApplication {

	@Autowired
	private  RoleRepository roleRepository;
	
	@Autowired
	private RoleFactory roleFactory;

	
	public void createnewrole(Role role) {
		roleRepository.save(role);
		
	}
	
	public RoleDto getRoleByID(Long id) throws PersonalDataNotFoundException {
		
		return roleFactory.toDto( roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id)));
		}
	
	
	
	public Iterable<Role> getRole(){
		return roleRepository.findAll();
	}

}
