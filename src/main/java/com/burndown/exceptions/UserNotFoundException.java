package com.burndown.exceptions;

public class UserNotFoundException  extends RuntimeException {

	public UserNotFoundException(Long id) {
		    super("Could not find User " + id);
		  }
		}

