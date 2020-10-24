package com.brentam.micro.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieCatalogServiceApplication {

//	@Bean
//	public WebClient.Builder getWebClient(){
//	   return WebClient.builder();
//	}
	@Bean
	@LoadBalanced
	public RestTemplate getNewRestTemplate(){
//		HttpComponentsClientHttpRequestFactory fac= new HttpComponentsClientHttpRequestFactory();
//		fac.setConnectTimeout(3000);
//	   return new RestTemplate(fac);
return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
