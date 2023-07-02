package com.wipro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.Exception.ResourceAlreadyExistException;
import com.wipro.dto.CustomerRequest;
import com.wipro.entity.Customer;
import com.wipro.repo.CustomerAddressRepo;
import com.wipro.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerServices {
    @Autowired
    CustomerRepo repo;
    @Autowired
    CustomerAddressRepo addRepo;
	@Override
	public Customer addCustomer(CustomerRequest customer) throws ResourceAlreadyExistException {
		if(customer!=null) {	
			Customer customerEmailValidation = repo.findByEmail(customer.getCustomer().getEmail());
            if(customerEmailValidation == null) {
            	Customer rt =repo.save(customer.getCustomer());
    			return rt;
            }
            throw new ResourceAlreadyExistException("Customer is Already Exist...");
		}
		else {
			throw new NullPointerException();
		}
	}

	@Override
	public Customer updateCustomer(Customer customer, int id) {
         
		Customer customerOb = repo.findById(id).orElse(null);
		if(customerOb!=null) {
			customerOb.setEmail(customer.getEmail());
			customerOb.setName(customer.getName());
			customerOb.setAddress(customer.getAddress());
			repo.save(customerOb);
			return customerOb;
		}
		else {
			throw new NullPointerException();
		}
	}

	@Override
	public String deleteCustomer(int id) {
		Customer cs = repo.findById(id).orElse(null);
        if(cs!=null) {
        	repo.delete(cs);
        	return "Successfully Deleted record of id= "+id;
        }
        else {
        	return "Customer Data Not Found ! deletion not possible";
        }
	}

	@Override
	public Customer searchCustomer(int id) {
        Customer customer=  repo.findById(id).orElse(null);
        if(customer==null) {
        	throw new NullPointerException();
        }
		return customer;
	}

	@Override
	public List<Customer> allCustomer() {
	    List<Customer> allcustomers = repo.findAll();
	    if(!allcustomers.isEmpty()) {
	    	return allcustomers;
	    }
	    else {
	    	throw new NullPointerException();
	    }
	}

}
