package com.wipro.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {

	private int id;
	
	String addressType;
	String DoorNo;
	String streetName;
	String layout;
	String city;
	String pincode;

}
