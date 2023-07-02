package com.wipro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.entity.ProductEntity;

@Service
public interface ProductService {
	
	public ProductEntity addProduct(ProductEntity product);
	public ProductEntity updateProduct(ProductEntity product,int id);
	public String deleteProduct(int id);
	public List<ProductEntity> searchProduct();
	
}
