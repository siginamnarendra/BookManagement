package com.customerService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.customerService.model.Cart;

//Its a Cart Service feign-client which takes care of calling the api's of Cart-Service
@FeignClient(url = "http://localhost:8082/cart", name = "Cart-Client")
public interface CartClient {

	@PostMapping("/addtocart")
	public Cart addToCart(@RequestBody Cart cart);

	@GetMapping("/getBook/{bookId}")
	@DeleteMapping("/deletefromcart/{cartItemId}")
	public String deleteFromCart(@PathVariable int cartItemId);

	@GetMapping("/getitems/{customerId}")
	public Cart getCartItems(@PathVariable String customerId);

}
