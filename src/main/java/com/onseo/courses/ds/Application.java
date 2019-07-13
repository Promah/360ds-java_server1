package com.onseo.courses.ds;

import com.onseo.courses.ds.logger.Logging;
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
			Logging.getLogger().info("Application run");
		}
		catch (Exception ex){
			Logging.getLogger().error("Startup application error"+ ex.getStackTrace());
		}
	}
}
