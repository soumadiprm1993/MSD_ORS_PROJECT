package com.wipro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
	
	int productId;
	String name;
	String description;
	double price;
	
}
