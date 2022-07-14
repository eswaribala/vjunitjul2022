package com.virtusa.bankingapi.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
@Data
@Entity
@Table(name="Trader")
public class Trader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TraderId")
	private long traderId;
	@Embedded
	private FullName name;
	@Column(name="DOB")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(foreignKey = @ForeignKey(name = "Bank_Code"), name = "Bank_Code")
	private Bank bank;

}
