package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.burndown.core.entity.ProducktBacklog;

@Repository
public interface ProduktBacklogRepository extends CrudRepository<ProducktBacklog, Long>{
	
	

}