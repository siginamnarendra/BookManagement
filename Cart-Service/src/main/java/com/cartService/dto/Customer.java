package com.cartService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private String customerName;
	private String customerEmail;
	private String customerContactNumber;
	private String customerAddress;
	private String password;

}
