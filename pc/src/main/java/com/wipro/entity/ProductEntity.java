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
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	String name;
	String description;
	double price;
	
}
