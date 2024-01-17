package com.burndown.exceptions;

public class PersonalDataNotFoundException extends RuntimeException {

	public PersonalDataNotFoundException(Long id) {
		    super("Could not find Data " + id);
		  }
		}

