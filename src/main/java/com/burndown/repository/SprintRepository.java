package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.burndown.core.entity.Role;
import com.burndown.core.entity.Sprint;


@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long>{
	
	

}