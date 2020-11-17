package com.dzs.dtms2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@EnableDiscoveryClient
@Slf4j
public class AaServerApplication {

	public static void main(String[] args) {


		SpringApplication.run(AaServerApplication.class, args);
		log.info("Server start at localhost:9989");
	}
}
