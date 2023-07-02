package com.ms.order.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class lineItems {
	
	@Id
	private int itemId;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	
}
