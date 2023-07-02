package com.wipro.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {
	
    private int inventoryId;
	private int productId;
	private int quantity;
	
}
