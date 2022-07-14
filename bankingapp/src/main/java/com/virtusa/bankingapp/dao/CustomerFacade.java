package com.virtusa.bankingapp.dao;

import java.util.List;
import java.util.stream.Stream;

import com.virtusa.bankingapp.models.Customer;

public interface CustomerFacade {
	
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(long customerId);
	void addCustomers();
   
}
