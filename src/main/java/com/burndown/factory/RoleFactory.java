package com.burndown.factory;

import org.springframework.stereotype.Service;
import com.burndown.core.entity.Role;
import com.burndown.dto.RoleDto;

@Service
public class RoleFactory {
	
	public Role toEntity(RoleDto dto){
		if(dto.getId()!=null) {
			return new Role (dto.getId(), dto.getRoleName(), dto.getPermissions());
		}else {
			return new Role (dto.getRoleName(), dto.getPermissions());
		}
	}
	
	public RoleDto toDto(Role role) {
		return new RoleDto(role.getId(), role.getRoleName(), role.getPermissions(),role.getUser());
		
	}
}



