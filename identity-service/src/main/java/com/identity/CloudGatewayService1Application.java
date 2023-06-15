package com.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayService1Application.class, args);
	}

}
