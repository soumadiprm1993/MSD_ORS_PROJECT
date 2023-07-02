package com.wipro.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {	
	private int id;
	
	String name;
	String email;
	
	List<CustomerAddress> address;
	
}
