package com.formacionbdi.springboot.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formacionbdi.springboot.app.commons.models.entity"})
public class MsvcProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductsApplication.class, args);
	}

}
