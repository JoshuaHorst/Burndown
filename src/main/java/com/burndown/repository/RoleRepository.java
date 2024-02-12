package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.burndown.core.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	
}
