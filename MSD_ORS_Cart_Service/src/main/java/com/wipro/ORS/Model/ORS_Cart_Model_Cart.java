package com.wipro.ORS.Model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ORS_Cart_Model_Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Sequencial")
	@GenericGenerator(name="Sequencial",
	strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters= {
			@Parameter(name="initial_value", value="1000")
	})
	@Column(unique=true)
    private int cartId ;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ORS_Cart_Model_LineItems> lineItems;

}
