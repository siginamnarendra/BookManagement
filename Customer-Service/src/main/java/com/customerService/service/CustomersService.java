package com.customerService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.client.JwtClient;
import com.customerService.dao.CustomerJpaRepository;
import com.customerService.dto.AuthRequest;
import com.customerService.dto.UserCredential;
import com.customerService.dto.UserDetails;
import com.customerService.entity.Customer;
import com.customerService.exceptions.UserAlreadyExistedException;
import com.customerService.exceptions.UserNotFoundException;
import com.customerService.exceptions.WrongCredentialEXception;

import feign.FeignException.FeignClientException;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

@Service
public class CustomersService {

	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	@Autowired
	private JwtClient jwtClient;

	@Transactional
	public String saveUser(Customer theCustomer) {
		String email = theCustomer.getCustomerEmail();
			try {
				Customer customer = customerJpaRepository.findById(email).get();
				System.out.println(customer.getCustomerAddress()+" _"+customer.getCustomerName()+"_______");
				throw new UserAlreadyExistedException("email :" + email + " is existed");

				
			}catch(NoSuchElementException es)
			{
				UserCredential user = new UserCredential();
				System.out.println(theCustomer.getCustomerName() + "_______");
				user.setEmail(theCustomer.getCustomerName());
				user.setName(theCustomer.getCustomerEmail());
				user.setPassword(theCustomer.getPassword());
				jwtClient.addNewUser(user);
				Customer savedCustomer = customerJpaRepository.save(theCustomer);
				return savedCustomer.getCustomerName() + " is registered as a user with " + " username : "
						+ savedCustomer.getCustomerEmail();
			}


	}

	public String logUser(AuthRequest authRequest) {

		try {
			return jwtClient.getToken(authRequest);

		}catch(FeignClientException  ex) {
			throw new WrongCredentialEXception("BAD CREDENTIALS");
		}

	}

	@Transactional
	public List<Customer> getAllCustomer() {

		return customerJpaRepository.findAll();

	}

	public UserDetails getCustomerbyid(String id) {
		 Customer customer=customerJpaRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No customer found with the phn: " + id));
		 UserDetails userDetails=new UserDetails();
		 userDetails.setCustomerAddress(customer.getCustomerAddress());
		 userDetails.setCustomerContactNumber(customer.getCustomerContactNumber());
		 userDetails.setCustomerEmail(customer.getCustomerEmail());
		 userDetails.setCustomerName(customer.getCustomerName());
		 userDetails.setPassword("xxxxxxxx");
		return userDetails;
	}
}
