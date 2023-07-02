package com.wipro.ORS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ORS.Exception.ResourceNotCreatedException;
import com.wipro.ORS.Exception.ResourceNotFoundException;
import com.wipro.ORS.Model.ORS_Inventory_Model;
import com.wipro.ORS.Service.ORS_Inventory_Service;

@RestController
@RequestMapping("/inventory")
public class ORS_Inventory_Controller {
	
	@Autowired
	ORS_Inventory_Service inventory_service;
	
	@GetMapping("/")
	public String indexPage() {
		return inventory_service.indexPage();
	}
	
	@PostMapping("/addInventory")
	public ResponseEntity<ORS_Inventory_Model> createInventory(@RequestBody ORS_Inventory_Model inventoryDetails) throws ResourceNotCreatedException {
		ORS_Inventory_Model createdInventory = inventory_service.createInventory(inventoryDetails);
		return new ResponseEntity<ORS_Inventory_Model>(createdInventory,HttpStatus.CREATED);	
	}
	
	@GetMapping("/inventory/{id}")
	public ResponseEntity<ORS_Inventory_Model> getInventory(@PathVariable int id) throws ResourceNotFoundException {
		ORS_Inventory_Model inventory = inventory_service.getInventoryById(id);
		return new ResponseEntity<ORS_Inventory_Model>(inventory,HttpStatus.FOUND);
	}
	
	@PutMapping("/inventory/{id}")
	public ResponseEntity<ORS_Inventory_Model> updateInventoryDetails(@RequestBody ORS_Inventory_Model editedInventoryDetails, @PathVariable int id) throws ResourceNotFoundException, ResourceNotCreatedException {
		
		ORS_Inventory_Model updatedInventory = inventory_service.updateInventoryDetails(editedInventoryDetails, id);
		return new ResponseEntity<ORS_Inventory_Model>(updatedInventory,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable int id) throws ResourceNotFoundException {
		String deleteMsg = inventory_service.deleteInventory(id);
		return  new ResponseEntity<String>(deleteMsg,HttpStatus.OK);
	}

}
