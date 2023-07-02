package com.wipro.ORS.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ORS_Inventory_Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Sequencial")
	@GenericGenerator(name="Sequencial",
	strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters= {
			@Parameter(name="initial_value", value="1000")
	})
	@Column(unique=true)
    private int inventoryId;
	private int productId;
	private int quantity;
}
