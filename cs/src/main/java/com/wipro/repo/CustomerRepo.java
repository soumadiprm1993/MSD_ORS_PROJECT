package com.wipro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	List<Customer> findAllByName(String name);
	Customer findByEmail(String email);

}
