package com.digitalharbor.hospital;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = { "com.digitalharbor" })
public class App 
{
	private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
    	LOGGER.info("Iniciando la aplicacion");
    	
    	System.setProperty("server.port", "9090");
		SpringApplication.run(App.class, args);
    }
}
