package com.orderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.orderService.entity.Orders;
import com.orderService.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/listOrders")
	public List<Orders> listOrders(@RequestHeader("loggedInUserName") String customerId) {

		return orderService.getOrdersByCustomer(customerId);

	}

	@GetMapping("/getOrderById/{orderId}")
	public Orders getOrderById(@PathVariable("orderId") String orderId) {
		return orderService.getOrderById(orderId);

	}

	@PostMapping("/add-cart")
	public Orders addFromCart(@RequestHeader("loggedInUserName") String customerId) {
		return orderService.addFromCart(customerId);
	}

}