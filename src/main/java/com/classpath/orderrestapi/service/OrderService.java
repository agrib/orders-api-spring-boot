package com.classpath.orderrestapi.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Map<String, Object> fetchAllOrders(int pageNo, int size, String strDirection, String property){
		//Java 11 and beyond
		//return Set.copyOf(this.orderRepository.findAll());
		//set the pagination support
		Sort.Direction direction = strDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Pageable pageRequest = PageRequest.of(pageNo, size, direction, property);
		Page<Order> pageResponse = this.orderRepository.findAll(pageRequest);
		
		int numberOfElements = pageResponse.getNumberOfElements();
		long totalElements = pageResponse.getTotalElements();
		int totalPages = pageResponse.getTotalPages();
		List<Order> data = pageResponse.getContent();
		
		Map<String, Object> responseMap = new LinkedHashMap<>();
		
		responseMap.put("elements", numberOfElements);
		responseMap.put("total-elements", totalElements);
		responseMap.put("total-pages", totalPages);
		responseMap.put("data", data);
		
		return responseMap;
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
