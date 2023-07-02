package com.wipro.ORS.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ORS.Exception.*;
import com.wipro.ORS.Model.ORS_Cart_Model_Cart;
import com.wipro.ORS.Model.ORS_Cart_Model_LineItems;
import com.wipro.ORS.Repo.ORS_Cart_Repo_Cart;
import com.wipro.ORS.Repo.ORS_Cart_Repo_LineItems;

@Service
public class ORS_Cart_Service {
	
	@Autowired
	ORS_Cart_Repo_Cart cart_repo;
	
	@Autowired
	ORS_Cart_Repo_LineItems lineItems_repo;
	
	public String indexPage() {
		return "Welcome To The Online Retail Store";
	}

	public ORS_Cart_Model_Cart createCart(ORS_Cart_Model_Cart cartDetails) throws ResourceNotCreatedException {
		ORS_Cart_Model_Cart createdCart = cart_repo.save(cartDetails);
		return createdCart;
	}

	public ORS_Cart_Model_Cart getCartById(int id) throws ResourceNotFoundException {
		
		if(cart_repo.existsById(id)) {
			ORS_Cart_Model_Cart cart = cart_repo.findById(id).get();
			return cart;
		}
		else {
			throw new ResourceNotFoundException("Cart id not Found");
		}
		
	}

	public ORS_Cart_Model_Cart updateCartDetails(ORS_Cart_Model_Cart editedCartDetails, int id) throws ResourceNotFoundException {
		System.out.println("-------------UPdate Service Calling with id = "+id +" ------------------------");
		Set<ORS_Cart_Model_LineItems> temp = new HashSet<ORS_Cart_Model_LineItems>();
		if(cart_repo.existsById(id)) {
				for(ORS_Cart_Model_LineItems lineItem:cart_repo.findById(id).get().getLineItems()) {
					temp.add(lineItem);
					System.out.println("\n" + temp + "\n");
			}
			temp.addAll(editedCartDetails.getLineItems());
			editedCartDetails.setLineItems(temp);
			editedCartDetails.setCartId(id);
			ORS_Cart_Model_Cart updatedCart = cart_repo.save(editedCartDetails);
			return updatedCart;
		}
		else {
			throw new ResourceNotFoundException("Cart Details not Found");
		}
	}

	public String deleteCart(int id) throws ResourceNotFoundException {
		if(cart_repo.existsById(id)) {
			Set<ORS_Cart_Model_LineItems> lineItems = cart_repo.findById(id).get().getLineItems();
			ORS_Cart_Model_Cart cart = cart_repo.findById(id).get();
			cart.getLineItems().removeAll(lineItems);
			cart_repo.save(cart);
			lineItems_repo.deleteAll(lineItems);
			return ("Cart Details Has Been Deleted :: Cart ID "+id);
		}
		else {
			throw new ResourceNotFoundException("Cart id not Found");
		}
	}

}
