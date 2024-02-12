package com.burndown.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.burndown.repository.RoleRepository;
import com.burndown.restcontroller.RabbitListener;

@Component
public class OnStartup implements ApplicationListener<ApplicationStartedEvent> {
	
	@Autowired
	RabbitListener rabbitListener;

	
	
	@Override
	  public void onApplicationEvent(final ApplicationStartedEvent event) {
		  
		  rabbitListener.ReceiveTaskChangedNotificationRabbitMq();
	  }
	}



