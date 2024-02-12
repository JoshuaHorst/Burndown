package com.burndown.exceptions;


public class SprintNotFoundException extends RuntimeException {

	public SprintNotFoundException(Long id) {
		    super("Could not find Sprint: " + id);
		  }
	}
