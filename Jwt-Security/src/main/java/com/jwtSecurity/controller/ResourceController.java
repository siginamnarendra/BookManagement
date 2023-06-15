package com.jwtSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	
	@GetMapping("/hellouser")
	public String helloUser(){
		return "Hello User";
	}
	
	@GetMapping("/helloadmin")
	public String helloAdmin(){
		return "Hello Admin";
	}

}