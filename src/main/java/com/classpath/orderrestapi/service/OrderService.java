package com.classpath.orderrestapi.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.classpath.orderrestapi.model.Order;
import com.classpath.orderrestapi.repository.OrderRepository;


@Service
//@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
		

	public OrderService(OrderRepository orderRepository) { 
	  this.orderRepository =  orderRepository; 
	}
	 
	
	public Order save(Order order) {
		return this.orderRepository.save(order);
	}
	
	public Set<Order> fetchAllOrders(){
		//Java 11 and beyond
		//return Set.copyOf(this.orderRepository.findAll());
		return new HashSet<>(this.orderRepository.findAll());
	}
	
	public Order fetchOrderById(long orderId) {
		//imperative style
		/*
		 * Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
		 * if(optionalOrder.isPresent()) { return optionalOrder.get(); } throw new
		 * IllegalArgumentException("invalid order id passed");
		 */
		
		return this.orderRepository
					.findById(orderId)
					.orElseThrow(() -> new IllegalArgumentException("invalid order id passed"));
		}

	public void deleteOrderById(long orderId) {
		this.orderRepository.deleteById(orderId);
	}

}
