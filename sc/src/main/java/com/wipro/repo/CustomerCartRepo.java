package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.pojo.CustomerCart;

public interface CustomerCartRepo extends JpaRepository<CustomerCart, Integer> {
	
//	CustomerCart findByCustomerId(int id);

}
