package com.orderService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderService.entity.OrderItems;

public interface OrderItemsJpaRepository extends JpaRepository<OrderItems, Integer> {

}
