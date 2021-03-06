package com.virtusa.bankingapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.bankingapi.models.Bank;
import com.virtusa.bankingapi.models.Trader;
import com.virtusa.bankingapi.repositories.TraderRepository;

@Service
public class TraderService {
	@Autowired
	private TraderRepository traderRepository;
	@Autowired
	private BankService bankService;
	
	private boolean status;
	
	public Trader addTrader(long bankId,Trader trader) {
		Bank bank=this.bankService.getBankByCode(bankId);
		if(bank!=null)
		{
			trader.setBank(bank);
		    this.traderRepository.save(trader);
		    return trader;
		}
		else
			return null;
		
		
    }
    
    
    public List<Trader> getAllTraders(){
    	return this.traderRepository.findAll();
    }
	
    
    public Trader getTraderById(long traderId) {
    	return this.traderRepository.findById(traderId).orElse(null);
    }
    
    public boolean deleteTraderById(long traderId) {
    	this.traderRepository.deleteById(traderId);
    	if(this.getTraderById(traderId)==null)
    		status=true;
    	return status;
    		
    }

}
