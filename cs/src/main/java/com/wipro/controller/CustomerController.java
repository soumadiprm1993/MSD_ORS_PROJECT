package com.wipro.controller;



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

import com.wipro.Exception.ResourceAlreadyExistException;
import com.wipro.dto.CustomerRequest;
import com.wipro.entity.Customer;
import com.wipro.services.CustomerServices;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServices cs;
	
	@GetMapping("/hi")
	public String test() {
		return "Hi";
	}
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest cr) throws ResourceAlreadyExistException{
		Customer res = cs.addCustomer(cr);
		System.out.println(cr.toString());
		return new ResponseEntity<> (res,HttpStatus.CREATED);
	}
	
	@GetMapping("/searchCustomer/{id}")
	public ResponseEntity<?> searchCustomer(@PathVariable int id){
     Customer customer = cs.searchCustomer(id);
     return new ResponseEntity<>(customer,HttpStatus.OK);
     
	}
	@GetMapping("/allCustomer")
	public ResponseEntity<?> allCustomer(){
		return new ResponseEntity<>(cs.allCustomer(),HttpStatus.OK);
	}
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") int id, @RequestBody CustomerRequest customer){
		Customer res = cs.updateCustomer(customer.getCustomer(), id);
	     return new ResponseEntity<>(res,HttpStatus.CREATED);

	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable int id) {
		return cs.deleteCustomer(id);
	}
	
}
