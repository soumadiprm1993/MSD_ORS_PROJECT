package com.wipro.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.pojo.CustomerOrder;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {
	
	Set<CustomerOrder> findByCustomerId(int customerid);

}
