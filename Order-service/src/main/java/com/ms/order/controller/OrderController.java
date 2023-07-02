package com.ms.order.controller;

import java.util.List;

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

import com.ms.order.model.Order;
import com.ms.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderServ;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		System.out.println("Controller"+order.getLineItems());
		Order ord= orderServ.createOrder(order);
		return new ResponseEntity<Order>(ord, HttpStatus.CREATED);
	}

	/*
	 * @PostMapping("/orders") public Order addOrders(@RequestBody
	 * List<OrderDetails> orders) { return orderServ.createOrders(orders); }
	 */

	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderwithId(@PathVariable int id) {
		Order ord= orderServ.getOrderById(id);
		return new ResponseEntity<Order>(ord, HttpStatus.FOUND);
	}

	@GetMapping("/orders")
	public ResponseEntity <List<Order>> getAllOrders() {
		List<Order> ord = orderServ.getOrdersAll();
		return new ResponseEntity <List<Order>>(ord,HttpStatus.OK);
	}

	@PutMapping("/order/{id}") 
	public ResponseEntity<Order> updateOrderwithId(@PathVariable int id,@RequestBody Order order) 
	  { 
		System.out.println(order);
		Order ord= orderServ.updateOrder(id,order); 
		return new ResponseEntity<Order> (ord,HttpStatus.OK);
	  }

	@DeleteMapping("/order/{id}") 
	public ResponseEntity<String> deleteOrderById(@PathVariable int id) 
	{
		String str= orderServ.deleteOrder(id); 
		return new ResponseEntity<String> (str,HttpStatus.OK);
	}
	 

}
