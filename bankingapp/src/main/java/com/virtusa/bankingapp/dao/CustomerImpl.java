package com.virtusa.bankingapp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.virtusa.bankingapp.models.Customer;

import lombok.Data;
@Data
public class CustomerImpl implements CustomerFacade{
	
	private List<Customer> customers;
	
	public CustomerImpl() {
		customers=new ArrayList<Customer>();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.add(customer);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}

	@Override
	public Customer getCustomerById(long customerId) {
		// TODO Auto-generated method stub
		return customers.stream()
		.filter(cust->cust.getCustomerId() == customerId)
		.findFirst().orElse(null);
		
	}
	
	
	public void addCustomers() {
		customers=Arrays.asList(
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari1","Bala1",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari2","Bala2",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari3","Bala3",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari4","Bala4",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true)
	    		);
	}

	

}
