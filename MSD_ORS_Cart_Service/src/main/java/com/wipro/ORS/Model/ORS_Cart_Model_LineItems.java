package com.wipro.ORS.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ORS_Cart_Model_LineItems {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
    private int itemId;
	private int productId;
	private String productName;
	private int quantity;
	private int price;

}
