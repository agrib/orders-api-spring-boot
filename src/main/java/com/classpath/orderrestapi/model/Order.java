package com.classpath.orderrestapi.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="orders")

@Data
@Builder
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String email;
	private double price;
	
	//This is mandate from Hibernate
	private Order() {}
	
	private LocalDate orderDate;
	
	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private Set<LineItem> lineItems;

}
