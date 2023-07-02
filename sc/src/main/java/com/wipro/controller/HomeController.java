package com.wipro.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wipro.Exception.OutOfStockException;
import com.wipro.Exception.ResourceExistException;
import com.wipro.Exception.ResourceNotCreatedException;
import com.wipro.Exception.ResourceNotFoundException;
import com.wipro.pojo.ORS_Cart_Model_Cart;
import com.wipro.pojo.ProductInventory;
import com.wipro.repo.CustomerCartRepo;
import com.wipro.repo.CustomerOrderRepo;
import com.wipro.service.ORS_Shopping_Service;
import com.wipro.pojo.CustomerRequest;

@RestController
@RequestMapping("api/shoppingservice")
public class HomeController {
	
	@Autowired
	ORS_Shopping_Service shopping;
	
	@Autowired
	CustomerCartRepo customerCartRepo;
	
	@Autowired
	CustomerOrderRepo customerOrderRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	String fallBackMessage = "Return back to the previous page ---- Error Found :: ";
	
	@GetMapping("/")
	public String indexPage() {
		return shopping.indexPage();
	}
	
	@PostMapping("/products/")
	@HystrixCommand(fallbackMethod = "addProductInventoryFallBack")
	ResponseEntity<?> addProductInventory(@RequestBody ProductInventory productDetails) throws ResourceNotCreatedException{
		Map<String,Object> Product_Inventory = shopping.addProductInventory(productDetails);
	    return new ResponseEntity<>(Product_Inventory,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/customer/")
	@HystrixCommand(fallbackMethod = "addCustomerFallBack")
	ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customer) throws ResourceNotCreatedException, ResourceExistException{
		String Customer_Cart = shopping.addCustomerCart(customer);
		if(Customer_Cart.contains("already exist")) {
			throw new ResourceExistException(Customer_Cart);
		}
	    return new ResponseEntity<>(Customer_Cart,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/customer/{customerId}/cart")
	@HystrixCommand(fallbackMethod = "addItemsToCartFallBack")
	ResponseEntity<?> addItemsToCart(@RequestBody ORS_Cart_Model_Cart cartDetails, @PathVariable int customerId) throws ResourceNotFoundException, OutOfStockException{
		ORS_Cart_Model_Cart cart = shopping.updateCart(customerId, cartDetails);
	    return new ResponseEntity<>(cart,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/customer/{customerId}/order")
	@HystrixCommand(fallbackMethod = "orderItemsFallBack")
	ResponseEntity<?> orderItems(@PathVariable int customerId) throws ResourceNotFoundException, ResourceNotCreatedException{
		Map<String, Object> orderDetails = shopping.placeOrder(customerId);
	    return new ResponseEntity<>(orderDetails,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer/{customerId}/order")
	@HystrixCommand(fallbackMethod = "getCustomerOrderFallBack")
	ResponseEntity<?> getCustomerOrder(@PathVariable int customerId) throws ResourceNotFoundException{
		
		Map<String,Object> Product_Inventory = shopping.getOrderCustomerDetails(customerId);
		return new ResponseEntity<>(Product_Inventory,HttpStatus.ACCEPTED);
		
	}


	/*	------------------ Fall Back Method	------------------ */
	
	ResponseEntity<?> addProductInventoryFallBack(@RequestBody ProductInventory productDetails, Throwable e) {
		if(e.getMessage()==null) {
			return new ResponseEntity<>(fallBackMessage+"Unknown",HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<>(fallBackMessage+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}
	
	ResponseEntity<?> addCustomerFallBack(@RequestBody CustomerRequest customer, Throwable e) {
		if(e.getMessage()==null) {
			return new ResponseEntity<>(fallBackMessage+"Unknown",HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<>(fallBackMessage+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}

	ResponseEntity<?> addItemsToCartFallBack(@RequestBody ORS_Cart_Model_Cart cartDetails, @PathVariable int customerId, Throwable e) {
		if(e.getMessage()==null) {
			return new ResponseEntity<>(fallBackMessage+"Unknown",HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<>(fallBackMessage+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}

	ResponseEntity<?> orderItemsFallBack(@PathVariable int customerId, Throwable e) {
		if(e.getMessage()==null) {
			return new ResponseEntity<>(fallBackMessage+"Unknown",HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<>(fallBackMessage+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}

	ResponseEntity<?> getCustomerOrderFallBack(@PathVariable int customerId, Throwable e) {
		if(e.getMessage()==null) {
			return new ResponseEntity<>(fallBackMessage+"Unknown",HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<>(fallBackMessage+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}


}
