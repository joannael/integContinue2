package com;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	static Logger logger = Logger.getLogger("Logger");

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
			logger.log(Level.INFO, "Application demarree !");

		} catch (Exception e) {
			logger.log(Level.INFO, "Application demarree !");
		}   
	} 
}
