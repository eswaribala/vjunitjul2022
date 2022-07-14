package com.virtusa.bankingapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.bankingapi.models.Trader;
import com.virtusa.bankingapi.services.TraderService;


@RestController
@RequestMapping("/traders")
public class TraderController {
	@Autowired
	private TraderService traderService;
	
	//post
	@PostMapping(value="/{bankId}",params = "version=1.0")
	public ResponseEntity<?> addTrader(@PathVariable("bankId") long bankId,@RequestBody Trader trader){
		Trader traderObj=this.traderService.addTrader(bankId,trader);
		if(traderObj!=null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(traderObj);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trader Not Created");
		
	}
	
	//get
	@GetMapping(value="/",params = "version=1.0")
	public List<Trader> getAllTraders(){
		return this.traderService.getAllTraders();
	}
	
	

	
	
		
	
	
}
