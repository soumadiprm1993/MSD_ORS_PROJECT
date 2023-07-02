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

import com.wipro.entity.ProductEntity;
import com.wipro.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductServiceImpl ps;
	
	@GetMapping("/")
	public String indexPage() {
		return "Welcome to product service";
	}
	
	@PostMapping("/addProduct")
	public ProductEntity addProduct(@RequestBody ProductEntity product) {
		return ps.addProduct(product);
	}
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestBody ProductEntity product) {
		return new ResponseEntity<>(ps.updateProduct(product, id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/searchProduct")
	public ResponseEntity<?> searchProduct(){
		return new ResponseEntity<>(ps.searchProduct(),HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		return new ResponseEntity<>(ps.deleteProduct(id),HttpStatus.OK);
	}
}
