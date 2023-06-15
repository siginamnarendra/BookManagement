package com.orderService.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.orderService.model.Cart;

@FeignClient(url = "http://localhost:8082/cart", name = "Cart-Client")
public interface CartClient {

	@DeleteMapping("/deletefromcart/{cartItemId}")
	public String deleteFromCart(@PathVariable("cartItemId") int cartItemId);

	@GetMapping("/getCartItems")
	public Cart getCart(@RequestHeader("loggedInUserName") String username);

}
