package com.virtusa.bankingapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private long accountNo;
	private AccountType accountType;
	private long balance;

}
