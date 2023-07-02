package com.wipro.pojo;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ORS_Cart_Model_Cart {
	
    private int cartId ;
    private Set<ORS_Cart_Model_LineItems> lineItems;
    
}
