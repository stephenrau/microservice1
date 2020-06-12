package com.example.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Microservice1_Service1 {

	@Autowired
	private RestTemplate restTemplate;


//	@HystrixCommand(fallbackMethod = "fallbackForService2", 
//			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", 
//			value = "5000") })
	@Retryable(value = {RestClientException.class}, maxAttempts = 4, backoff = @Backoff(delay = 10000))	
	public String callService2() {

		System.out.println( " callService2() ........................  ");
		
		return restTemplate.getForObject("http://localhost:8082/v1/api/get", String.class);
	}

	@Recover
	public String fallbackForService2() {

		return "this is fallbackMethod";
	}
}
