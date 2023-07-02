package com.wipro.ORS.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ORS.Exception.ResourceNotFoundException;
import com.wipro.ORS.Model.ORS_Inventory_Model;
import com.wipro.ORS.Repo.ORS_Inventory_Repo;

@Service
public class ORS_Inventory_Service {

	@Autowired
	ORS_Inventory_Repo inventory_repo;
	
	public String indexPage() {
		return "Welcome To The Online Retail Store";
	}

	public ORS_Inventory_Model createInventory(ORS_Inventory_Model inventoryDetails) {
		ORS_Inventory_Model createdInventory = inventory_repo.save(inventoryDetails);
		return createdInventory;
	}

	public ORS_Inventory_Model getInventoryById(int id) throws ResourceNotFoundException {

		ORS_Inventory_Model inventory = inventory_repo.findByProductId(id);
		if(inventory!=null) {
			return inventory;
		}
		else {
			throw new ResourceNotFoundException("Product id not Found");
		}
	}

	public ORS_Inventory_Model updateInventoryDetails(ORS_Inventory_Model editedInventoryDetails, int id) throws ResourceNotFoundException {
		ORS_Inventory_Model inventory = inventory_repo.findByProductId(id);
		if(inventory!=null) {
			inventory.setQuantity(editedInventoryDetails.getQuantity());;
			ORS_Inventory_Model updatedInventory = inventory_repo.save(inventory);
			return updatedInventory;
		}
		else {
			throw new ResourceNotFoundException("Inventory Details not Found");
		}
	}

	public String deleteInventory(int id) throws ResourceNotFoundException {
		ORS_Inventory_Model inventory = inventory_repo.findByProductId(id);
		if(inventory!=null) {
			inventory_repo.delete(inventory);
			return ("Inventory Details Has Been Deleted :: Product ID "+id);
		}
		else {
			throw new ResourceNotFoundException("Product id not Found");
		}
	}

}
