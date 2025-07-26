package com.project.delivery.serviceregistre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistreApplication.class, args);
	}

}
