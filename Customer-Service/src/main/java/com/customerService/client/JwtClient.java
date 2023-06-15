package com.customerService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.customerService.dto.AuthRequest;
import com.customerService.dto.UserCredential;

//Its a Identity-service feign-client which takes care of calling the api's of Cart-Service
@Service
@FeignClient(url = "http://localhost:9898/auth", name = "identity-Client")
@ComponentScan
public interface JwtClient {

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential user);

	@PostMapping("/generattoken")
	public String getToken(@RequestBody AuthRequest authRequest);

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token);

}
