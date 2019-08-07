package com.kebernetes.cicd.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KoolearnEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KoolearnEurekaApplication.class).web(true).run(args);

	}
}