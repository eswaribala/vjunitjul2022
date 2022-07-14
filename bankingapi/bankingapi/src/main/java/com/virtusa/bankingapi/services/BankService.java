package com.virtusa.bankingapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.bankingapi.models.Bank;
import com.virtusa.bankingapi.repositories.BankRepository;

@Service
public class BankService {
    @Autowired 
	private BankRepository bankRepository; 
    private boolean status;
    //crud
    
    public Bank addBank(Bank bank) {
    	return this.bankRepository.save(bank);
    }
    
    
    public List<Bank> getAllBanks(){
    	return this.bankRepository.findAll();
    }
	
    
    public Bank getBankByCode(long bankCode) {
    	return this.bankRepository.findById(bankCode).orElse(null);
    }
    
    public boolean deleteBankByCode(long bankCode) {
    	this.bankRepository.deleteById(bankCode);
    	if(this.getBankByCode(bankCode)==null)
    		status=true;
    	return status;
    		
    }
    
    
    
}
