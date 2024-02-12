package com.burndown.factory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.Role;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.Story;
import com.burndown.core.entity.User;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.StoryDto;
import com.burndown.dto.UserDto;
import com.burndown.dto.UserLargeDto;
import com.burndown.exceptions.PersonalDataNotFoundException;
import com.burndown.exceptions.RoleNotFoundException;
import com.burndown.repository.PersonalDataRepository;
import com.burndown.repository.RoleRepository;

@Service
public class UserFactory {

	@Autowired
	private  RoleRepository roleRepository;
	
	@Autowired
	private RoleFactory roleFactory;
	
	@Autowired
	private PersonalDataFactory dataFactory;
	
	@Autowired
	private PersonalDataRepository personalDataRepository;
	
	public User toEntity(UserDto dto) {
		Long p = dto.getRole().getId();
		Role role = roleRepository.findById(p).orElseThrow(() -> new RoleNotFoundException(dto.getRole().getId()));
		Optional<PersonalData> dataopt = null;
		if(dto.getId()!= null) {
		dataopt =	personalDataRepository.findById(dto.getId());;
		}
		if(dataopt!= null&&dataopt.isPresent()) {
			return new User(dto.getId(), dataopt.get(), role );
		}else {
			User user = new User(dto.getId(), null, role );
			return user;
		}
	}
	
	public UserDto toDto(User user) {
		return new UserDto(user.getId(), roleFactory.toDto(user.getRole()), dataFactory.toDto(user.getPersonalData()));
	}
	
	public UserLargeDto toLargeDto(User user,PersonalData data, Role role) {
		return new UserLargeDto(user.getId(),role.getId(), role.getRoleName(), role.getPermissions(), data.getName(), data.getBirthday().toString());	
	}
	
	public  User toLageEntitiy(UserLargeDto dto) {
	Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new RoleNotFoundException(dto.getRoleId()));
	Optional<PersonalData> dataopt = null;
	if(dto.getId()!= null) {
	dataopt =	personalDataRepository.findById(dto.getId());
	}
	if(dataopt!= null&&dataopt.isPresent()) {
		return new User(dto.getId(), dataopt.get(), role );
	}else {
		User user = new User(dto.getId(), null, role );
		return user;
	}
	}	
}
