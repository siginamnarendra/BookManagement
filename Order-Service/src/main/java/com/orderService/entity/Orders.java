package com.orderService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	private String orderId;
	private String customerId;
	@OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItems> orderItems = new ArrayList<OrderItems>();

}
