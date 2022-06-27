package com.classpath.orderrestapi.controller;

import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.classpath.orderrestapi.model.Order;
import com.classpath.orderrestapi.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
	
	private final OrderService orderService;
	
	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public Set<Order> fetchAllOrders(){
		return this.orderService.fetchAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long orderId){
		return this.orderService.fetchOrderById(orderId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable("id") long orderId){
		this.orderService.deleteOrderById(orderId);
	}
	
	@PostMapping
	public Order saveOrder(@RequestBody Order order) {
		return this.orderService.save(order);
	}
}