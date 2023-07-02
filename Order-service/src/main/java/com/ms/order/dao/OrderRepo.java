package com.ms.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.order.model.Order;

public interface OrderRepo extends JpaRepository<Order,Integer> {

}
