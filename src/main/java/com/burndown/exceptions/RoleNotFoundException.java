package com.burndown.exceptions;

public class RoleNotFoundException extends RuntimeException {

	public RoleNotFoundException(Long id) {
		    super("Could not find Role" + id);
		  }
	}


