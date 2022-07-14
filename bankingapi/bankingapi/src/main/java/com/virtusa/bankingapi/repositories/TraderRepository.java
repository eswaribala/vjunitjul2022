package com.virtusa.bankingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.bankingapi.models.Trader;

public interface TraderRepository extends JpaRepository<Trader,Long>{

}
