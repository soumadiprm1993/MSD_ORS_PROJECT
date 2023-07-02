package com.wipro.ORS.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.ORS.Model.ORS_Inventory_Model;

@Repository
public interface ORS_Inventory_Repo extends JpaRepository<ORS_Inventory_Model, Integer> {
	
	ORS_Inventory_Model findByProductId(int productId);

}
