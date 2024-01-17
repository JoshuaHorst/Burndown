package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.burndown.core.entity.PersonalData;

@Repository
public interface PersonalDataRepository extends CrudRepository<PersonalData, Long> {

}
