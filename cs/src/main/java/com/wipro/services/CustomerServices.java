package com.wipro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.Exception.ResourceAlreadyExistException;
import com.wipro.dto.CustomerRequest;
import com.wipro.entity.Customer;

@Service
public interface CustomerServices {
    public Customer addCustomer(CustomerRequest customer) throws ResourceAlreadyExistException;
    public Customer updateCustomer(Customer customer,int id);
    public String deleteCustomer(int id);
    public Customer searchCustomer(int id);
    public List<Customer> allCustomer();
    
}
