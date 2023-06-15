package com.cartService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartService.entity.Cart;
import com.cartService.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@PostMapping("/addtocart/{bookId}/{quantity}")
	public String addToCart(@PathVariable("bookId") int bookId, @PathVariable("quantity") int quantiry,
			@RequestHeader("loggedInUserName") String username) {
		return cartService.saveToCart(bookId, quantiry, username);
	}

	@DeleteMapping("/deletefromcart/{cartItemId}")
	public String deleteFromCart(@PathVariable("cartItemId") int cartItemId) {
		return cartService.deleteFromCart(cartItemId);
	}

	@GetMapping("/getCartItems")
	public Cart getCartItems(@RequestHeader("loggedInUserName") String username) {
		return cartService.getCartItems(username);
	}

}
