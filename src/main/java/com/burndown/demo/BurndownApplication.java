package com.burndown.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
@EntityScan(basePackages = {"com.burndown.core.entity"})
@ComponentScan(basePackages={"com.burndown.core.application","com.burndown.restcontroller","com.burndown.factory","com.burndown.demo",})
@EnableJpaRepositories(basePackages={"com.burndown.repository"})
public class BurndownApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurndownApplication.class, args);
	
		 
	}
	

}
