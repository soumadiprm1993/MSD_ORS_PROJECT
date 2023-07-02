package com.wipro.ORS.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.ORS.Model.ORS_Cart_Model_Cart;

@Repository
public interface ORS_Cart_Repo_Cart extends JpaRepository<ORS_Cart_Model_Cart, Integer>{

}
