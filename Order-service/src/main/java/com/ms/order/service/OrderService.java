package com.ms.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.order.Exceptions.OrderIdNotFound;
import com.ms.order.dao.OrderRepo;
import com.ms.order.model.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

	Order ord;

	public Order createOrder(Order order) {
		ord = new Order();
		System.out.println("Service"+order.getLineItems());
		ord.setLineItems(order.getLineItems());
		orderRepo.save(ord);
		System.out.println(ord.toString());
		return ord;
	}

//	public Order createOrders(List<OrderDetails> orders) {
//		ord = new Order();
//		ord.setOrderDetails(orders);
//		return orderRepo.save(ord);
//	}

	public Order getOrderById(int id) {
		return orderRepo.findById(id).orElseThrow(()->new OrderIdNotFound("Order Id: "+id+" not Found"));
	}

	public List<Order> getOrdersAll() {
		return orderRepo.findAll();
	}

	public Order updateOrder(int id,Order order) {
		Optional<Order> optional = orderRepo.findById(id);
		Order ord = null;
		if (optional.isPresent()) {
			ord = optional.get();
			ord.setLineItems(order.getLineItems());
			orderRepo.save(ord);

		} else {
			 throw new OrderIdNotFound("Order Id: "+id + " Not Found");
		}
		return ord;
	}

	public String deleteOrder(int id) {
		if (orderRepo.findById(id).isPresent()) {
			orderRepo.deleteById(id);
		} else {
			throw new OrderIdNotFound("Order Id: "+id + " Order cannot delete,Not found order id");
		}
		return "Order got deleted";
	}

}
