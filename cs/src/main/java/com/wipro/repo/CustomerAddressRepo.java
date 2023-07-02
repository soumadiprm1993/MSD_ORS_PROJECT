package com.wipro.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.CustomerAddress;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Integer>{

}
