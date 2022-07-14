package com.virtusa.bankingapp.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private long customerId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private boolean status;
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Customer customer=(Customer)obj;
		return this.customerId == customer.getCustomerId();
	}
	

}
