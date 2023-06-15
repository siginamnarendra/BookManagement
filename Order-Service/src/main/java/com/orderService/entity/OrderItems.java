package com.orderService.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

	private String customerId;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cartItemId;
	private String bookTitle;
	private Integer bookPrice;
	private Integer quantity;
	private int bookId;
	private Date orderDate;

	@ManyToOne()
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private Orders orders;

}
