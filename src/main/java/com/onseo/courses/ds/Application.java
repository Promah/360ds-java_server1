package com.onseo.courses.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		runApplication(args);
	}

	private static void runApplication(String[] args){
		try {
			SpringApplication.run(Application.class, args);
		}
		catch (Exception ex){
			System.err.println("Startup application error"+ ex.getStackTrace());
		}
	}
}
