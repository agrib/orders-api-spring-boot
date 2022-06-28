package com.classpath.orderrestapi.controller;

import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Map<String, Object> fetchAllOrders(
			@RequestParam(name = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "order", defaultValue = "asc", required = false) String direction,
			@RequestParam(name = "field", defaultValue = "name", required = false) String property
			){
		return this.orderService.fetchAllOrders(pageNo, size, direction, property);
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