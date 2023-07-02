package com.wipro.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.Exception.OutOfStockException;
import com.wipro.Exception.ResourceNotCreatedException;
import com.wipro.Exception.ResourceNotFoundException;
import com.wipro.pojo.Customer;
import com.wipro.pojo.CustomerCart;
import com.wipro.pojo.CustomerOrder;
import com.wipro.pojo.CustomerRequest;
import com.wipro.pojo.InventoryEntity;
import com.wipro.pojo.ORS_Cart_Model_Cart;
import com.wipro.pojo.ORS_Cart_Model_LineItems;
import com.wipro.pojo.Order;
import com.wipro.pojo.ProductEntity;
import com.wipro.pojo.ProductInventory;
import com.wipro.repo.CustomerCartRepo;
import com.wipro.repo.CustomerOrderRepo;

@Service
public class ORS_Shopping_Service {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	CustomerCartRepo customerCartRepo;
	
	@Autowired
	CustomerOrderRepo customerOrderRepo;
	
	public String indexPage() {
		return "Welcome To The Online Retail Store";
	}
	
	public Map<String, Object> addProductInventory(ProductInventory productDetails) throws ResourceNotCreatedException{
		Map<String, Object> list = new HashMap<>();
	    final String baseProductUrl ="http://PRODUCT-SERVICE/products/addProduct";
	    ProductEntity productObj = restTemplate.exchange(baseProductUrl,HttpMethod.POST, new HttpEntity<>(productDetails), ProductEntity.class).getBody();
	    final String baseInvUrl = "http://INVENTORY-SERVICE/inventory/addInventory";   
	    InventoryEntity invObj = new InventoryEntity();
	    invObj.setProductId(productObj.getProductId());
	    invObj.setQuantity(productDetails.getQuantity());       
	    InventoryEntity inventoryObj = restTemplate.exchange(baseInvUrl, HttpMethod.POST, new HttpEntity<>(invObj), InventoryEntity.class).getBody();
	    list.put("Product Details", productObj);
	    list.put("Inventory Details", inventoryObj);
		return list;
	}

	public String addCustomerCart(CustomerRequest customerDetails) throws ResourceNotCreatedException{
		final String baseCustomerUrl = "http://CUSTOMER-SERVICE/customer/addCustomer";
		Customer customer = new Customer();
	    try {
	    	customer = restTemplate.exchange(baseCustomerUrl, HttpMethod.POST, new HttpEntity<>(customerDetails), Customer.class).getBody();
	    } catch(Exception e) {
	    	return ("Customer is already exist with email id : " + customerDetails.getCustomer().getEmail());
	    }
	    final String baseCartUrl = "http://CART-SERVICE/cart/addCart"; 
	    ORS_Cart_Model_Cart cartDetails = new ORS_Cart_Model_Cart();
	    ORS_Cart_Model_Cart cart = restTemplate.exchange(baseCartUrl, HttpMethod.POST, new HttpEntity<>(cartDetails), ORS_Cart_Model_Cart.class).getBody();
	    CustomerCart customerCart = new CustomerCart(customer.getId(),cart.getCartId());
	    customerCartRepo.save(customerCart);
		return ("Cart Id :: " + customerCart.getCartId() + "\nCustomer ID :: " + customerCart.getCustomerId());
	}

	public ORS_Cart_Model_Cart updateCart(int customerId, ORS_Cart_Model_Cart cartDetails) throws ResourceNotFoundException, OutOfStockException {
		CustomerCart customerCart = customerCartRepo.findById(customerId).orElse(null);
		int flag = 0;
		if(customerCart != null) {
			
			for(ORS_Cart_Model_LineItems lineItems:cartDetails.getLineItems()) {
				final String baseInventoryUrl2 = "http://INVENTORY-SERVICE/inventory/inventory/"+lineItems.getProductId();
			    InventoryEntity invObj = restTemplate.getForEntity(baseInventoryUrl2, InventoryEntity.class).getBody();

			    int quantityDif = invObj.getQuantity()-lineItems.getQuantity();
			    
			    if(quantityDif<0) {
			    	flag=1;
			    	break;
			    }
			}
			if(flag==0) {
				
				final String baseCartUrl = "http://CART-SERVICE/cart/cart/"+customerCart.getCartId();
			    ORS_Cart_Model_Cart cart = restTemplate.exchange(baseCartUrl,HttpMethod.PUT,  new HttpEntity<>(cartDetails), ORS_Cart_Model_Cart.class).getBody();
			    System.out.println(flag);
			    return cart;
				
			}
			else 
				throw new OutOfStockException("Out of stock");
		}
		else
			throw new ResourceNotFoundException("Customer Id is not found...");
	}

	public Map<String, Object> placeOrder(int customerId) throws ResourceNotFoundException, ResourceNotCreatedException {
		Map<String, Object> list = new HashMap<>();
		
		CustomerCart customerCart = customerCartRepo.findById(customerId).orElse(null);
		if(customerCart != null) {
		    final String baseCartUrl = "http://CART-SERVICE/cart/cart/"+customerCart.getCartId();
		    ORS_Cart_Model_Cart cartDetails = restTemplate.getForEntity(baseCartUrl, ORS_Cart_Model_Cart.class).getBody();
            Order order = new Order();
            order.setLineItems(cartDetails.getLineItems());
		    final String baseOrderUrl = "http://ORDER-SERVICE/order/addOrder";
		    Order orderDetails = restTemplate.postForEntity(baseOrderUrl,new HttpEntity<>(order), Order.class).getBody();
		    
		    CustomerOrder customerOrder = new CustomerOrder(orderDetails.getId(), customerId);
		    customerOrderRepo.save(customerOrder);
		    String msg = restTemplate.exchange(baseCartUrl, HttpMethod.DELETE,new HttpEntity<>(cartDetails), String.class).getBody();
		    
		    Set<InventoryEntity> allUpdatedInventoryDetails = updateInventory(orderDetails);
		    

		    list.put("Cart Details", cartDetails);
		    list.put("Order Details", orderDetails);
		    list.put("Cart MSG", msg);
		    list.put("Updated Inventory Details", allUpdatedInventoryDetails);
		    
		    return list;
		    
		}
		else {
			throw new ResourceNotFoundException("Customer Id is not found...");
		}
	}
	


	public Map<String, Object> getOrderCustomerDetails(int customerId) throws ResourceNotFoundException {
		Map<String, Object> list = new HashMap<>();
		int i = 1;
		
		if(!customerOrderRepo.findByCustomerId(customerId).isEmpty()) {
			Set<CustomerOrder> customerOrders = customerOrderRepo.findByCustomerId(customerId);
			for(CustomerOrder customerOrder:customerOrders) {
				final String baseOrderUrl = "http://ORDER-SERVICE/order/order/"+customerOrder.getOrderId();
		    	Order orderDetails = restTemplate.getForEntity(baseOrderUrl, Order.class).getBody();
				list.put(i+". Order Details", orderDetails);
				i++;
			}
			
			final String baseCustomerUrl = "http://CUSTOMER-SERVICE/customer/searchCustomer/"+customerId;
		    Customer customerDetails = restTemplate.getForEntity(baseCustomerUrl, Customer.class).getBody();
		    
		    list.put("Customer Details", customerDetails);
			
			return list;
		}
		else {
			throw new ResourceNotFoundException("Customer Id is not found...");
		}
	}

	private Set<InventoryEntity> updateInventory(Order orderDetails) throws ResourceNotCreatedException{
		
		Set<InventoryEntity> allUpdatedInventoryDetails = new HashSet<>();
		
		orderDetails.getLineItems().forEach((lineItems) -> {

		    final String baseInventoryUrl2 = "http://INVENTORY-SERVICE/inventory/inventory/"+lineItems.getProductId();
		    InventoryEntity invObj = restTemplate.getForEntity(baseInventoryUrl2, InventoryEntity.class).getBody();

		    int updatedQuantity = invObj.getQuantity()-lineItems.getQuantity();
		    invObj.setQuantity(updatedQuantity);
		    InventoryEntity inventoryDetails = restTemplate.exchange(baseInventoryUrl2, HttpMethod.PUT, new HttpEntity<>(invObj), InventoryEntity.class).getBody();
	    	
		    allUpdatedInventoryDetails.add(inventoryDetails);
	    });

		return allUpdatedInventoryDetails;
	}
	
}
