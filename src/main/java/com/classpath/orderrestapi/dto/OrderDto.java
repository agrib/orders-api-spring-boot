package com.classpath.orderrestapi.dto;

import java.time.LocalDate;

public interface OrderDto {
	
	String getName();
	
	String getEmail();

	double getPrice();
	
	LocalDate getOrderDate();
}
