package com.cartService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

	private Integer bookId;
	private String bookTitle;
	private Integer quantity;
	private Integer bookPrice;
	private String customerId;
}
