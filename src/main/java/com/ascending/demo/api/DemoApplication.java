package com.ascending.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan

//The following is for development using spring Boot embedded Tomcat
//public class DemoApplication {
//	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//
//		logger.trace("======  DemoApplication, this is a trace level logger info from {}", "Jingrong");
//		logger.debug("======  DemoApplication, this is a debug level logger info from {}", "Jingrong");
//		logger.info("======  DemoApplication, this is a info level logger info from {}", "Jingrong");
//		logger.warn("======  DemoApplication, this is a warn level logger info from {}", "Jingrong");
//		logger.error("======  DemoApplication, this is a error level logger info from {}", "Jingrong");
//	}
//}
//The following is for deploy the app to external Tomcat
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}
}
