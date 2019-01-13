package com.example.securitysample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/floor1")
public class FirstFloorController {

	
	@GetMapping("office1")
	public ResponseEntity<?> enterOffice(){
		
		return new ResponseEntity<>("You are inside office 1", HttpStatus.OK);
	}
	
	@GetMapping("office2")
	public ResponseEntity<?> enterOffice2(){
		
		return new ResponseEntity<>("You are inside office 2", HttpStatus.OK);
	}
}
