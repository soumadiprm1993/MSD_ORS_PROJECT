package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.ProductEntity;
import com.wipro.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
    
	@Autowired
	ProductRepo repo;
	@Override
	public ProductEntity addProduct(ProductEntity product) {
		if(product!=null) {
		return repo.save(product) ;
		}
		else {
			return null;
		}
	}

	@Override
	public ProductEntity updateProduct(ProductEntity product, int id) {
		ProductEntity product1 = repo.findById(id).orElse(null);
		if(product !=null) {
			product1.setName(product.getName());
			product1.setDescription(product.getDescription());
			product1.setPrice(product.getPrice());
			repo.save(product1);
			return product1;
		}
		return null;
	}

	@Override
	public String deleteProduct(int id) {
		ProductEntity product1 = repo.findById(id).orElse(null);
		if(product1 !=null) {
		repo.delete(product1);
		return "Product with id "+ id +" Has been deleted";
		}
		else {
			return "Product Can't be deleted as no product found";	
		}
		
	}

	@Override
	public List<ProductEntity> searchProduct() {
		return repo.findAll();
	}

}
