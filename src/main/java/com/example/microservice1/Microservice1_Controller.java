package com.example.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/api/")
public class Microservice1_Controller {

	@Autowired
	private Microservice1_Service1 service;
	
	@GetMapping("get")
	public ResponseEntity<String> getUsers() {
		
		
		return new ResponseEntity<String>(service.callService2(), HttpStatus.OK);
	}
}
