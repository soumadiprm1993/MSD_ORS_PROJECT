package com.wipro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	
	private int itemId;
    private int productId;
	private String productName;
	private int quantity;
	private int price;
	
}
