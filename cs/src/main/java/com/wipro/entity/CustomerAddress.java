package com.wipro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	String addressType;
	String DoorNo;
	String streetName;
	String layout;
	String city;
	String pincode;

}
