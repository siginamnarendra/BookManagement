package com.orderService.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Cart {

	private String customerId;
	private List<CartItems> cartItems = new ArrayList<CartItems>();
	 
}
