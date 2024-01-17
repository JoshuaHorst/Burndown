package com.burndown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.burndown.core.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
