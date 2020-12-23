package com.example.productprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductPriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPriceApplication.class, args);
	}

	//old way of dependency injection create bean and in controller autowired for older spring versions
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
