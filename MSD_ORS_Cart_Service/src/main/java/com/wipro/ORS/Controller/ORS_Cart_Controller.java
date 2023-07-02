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
import com.wipro.ORS.Model.ORS_Cart_Model_Cart;
import com.wipro.ORS.Service.ORS_Cart_Service;

@RestController
@RequestMapping("/cart")
public class ORS_Cart_Controller {
	
	@Autowired
	ORS_Cart_Service cart_service;
	
	@GetMapping("/")
	public String indexPage() {
		return cart_service.indexPage();
	}
	
	@PostMapping("/addCart")
	public ResponseEntity<ORS_Cart_Model_Cart> createCart(@RequestBody ORS_Cart_Model_Cart cartDetails) throws ResourceNotCreatedException {
		
		ORS_Cart_Model_Cart createdCart = cart_service.createCart(cartDetails);
		return new ResponseEntity<ORS_Cart_Model_Cart>(createdCart,HttpStatus.CREATED);	
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<ORS_Cart_Model_Cart> getCart(@PathVariable int id) throws ResourceNotFoundException {
		ORS_Cart_Model_Cart cart = cart_service.getCartById(id);
		return new ResponseEntity<ORS_Cart_Model_Cart>(cart,HttpStatus.FOUND);
	}
	
	@PutMapping("/cart/{id}")
	public ResponseEntity<ORS_Cart_Model_Cart> updateCartDetails(@RequestBody ORS_Cart_Model_Cart editedCartDetails, @PathVariable int id) throws ResourceNotFoundException, ResourceNotCreatedException {
		
		ORS_Cart_Model_Cart updatedCart = cart_service.updateCartDetails(editedCartDetails, id);
		return new ResponseEntity<ORS_Cart_Model_Cart>(updatedCart,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable int id) throws ResourceNotFoundException {
		String deleteMsg = cart_service.deleteCart(id);
		return  new ResponseEntity<String>(deleteMsg,HttpStatus.OK);
	}

}
