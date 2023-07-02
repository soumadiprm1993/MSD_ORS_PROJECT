package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {

}
