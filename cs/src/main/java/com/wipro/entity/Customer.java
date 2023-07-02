package com.wipro.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	String name;
	String email;
	
	@OneToMany(targetEntity = CustomerAddress.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_fk",referencedColumnName = "id")
	List<CustomerAddress> address;
	
	
	
}
