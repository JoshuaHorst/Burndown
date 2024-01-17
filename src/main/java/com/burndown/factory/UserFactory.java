package com.burndown.factory;

import org.springframework.stereotype.Service;

import com.burndown.core.entity.PersonalData;
import com.burndown.core.entity.Role;
import com.burndown.core.entity.SprintBacklog;
import com.burndown.core.entity.User;
import com.burndown.dto.SprintBacklogDto;
import com.burndown.dto.UserLargeDto;

@Service
public class UserFactory {
	
	
	
	public UserLargeDto toLargeDto(User user,PersonalData data, Role role) {
		return new UserLargeDto(user.getId(),role.getId(), role.getRoleName(), role.getPermissions(), data.getName(), data.getBirthday());	
	}
	
	
}
