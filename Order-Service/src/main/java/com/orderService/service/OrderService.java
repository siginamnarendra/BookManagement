package com.orderService.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderService.client.CartClient;
import com.orderService.dao.OrderJpaRepository;
import com.orderService.entity.OrderItems;
import com.orderService.entity.Orders;
import com.orderService.model.Cart;
import com.orderService.model.CartItems;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderRepository;
	@Autowired
	private CartClient cartClient;

	public List<Orders> getOrdersByCustomer(String customerId) {
		return orderRepository.findAll();
	}

	public Orders getOrderById(String OrderId) {
		Orders order = new Orders();
		try {
			order = orderRepository.findById(OrderId).get();
			return order;
		} catch (RuntimeException e) {
			return order;
		}
	}

	public Orders addFromCart(String customerId) {
		Cart cart = cartClient.getCart(customerId);
		System.err.println(cart);

		List<CartItems> items = cart.getCartItems();

		Orders order = new Orders();

		String randomOrderId = UUID.randomUUID().toString();

		order.setOrderId(randomOrderId);
		order.setCustomerId(cart.getCustomerId());

		Iterator<CartItems> iterator = items.iterator();
		while (iterator.hasNext()) {
			CartItems item = iterator.next();
			OrderItems oi = new OrderItems();
			oi.setBookId(item.getBookId());
			oi.setBookPrice(item.getBookPrice());
			oi.setBookTitle(item.getBookTitle());
			oi.setCartItemId(item.getCartItemId());
			oi.setQuantity(item.getQuantity());
			oi.setCustomerId(item.getCustomerId());
			oi.setOrderDate(new Date());
			oi.setCustomerId(customerId);

			oi.setOrders(order);
			order.getOrderItems().add(oi);
			cartClient.deleteFromCart(item.getCartItemId());
		}

		return orderRepository.save(order);
	}

}
