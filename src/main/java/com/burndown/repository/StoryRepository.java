package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.burndown.core.entity.Story;


@Repository
public interface StoryRepository extends CrudRepository<Story, Long>{
	
	

}