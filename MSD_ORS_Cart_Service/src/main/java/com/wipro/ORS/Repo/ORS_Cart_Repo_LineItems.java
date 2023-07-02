package com.wipro.ORS.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.ORS.Model.ORS_Cart_Model_LineItems;

@Repository
public interface ORS_Cart_Repo_LineItems extends JpaRepository<ORS_Cart_Model_LineItems, Integer>{

}