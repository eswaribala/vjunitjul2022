package com.virtusa.bankingapi.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class FullName {

	@Column(name="First_Name",nullable = false,length = 50)
	private String firstName;
	@Column(name="Middle_Name",nullable = true,length = 50)
	private String middleName;
	@Column(name="Last_Name",nullable = false,length = 50)
	private String lastName;
}
