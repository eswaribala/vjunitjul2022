package com.virtusa.bankingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.bankingapi.models.Bank;

public interface BankRepository  extends JpaRepository<Bank,Long>{

}
