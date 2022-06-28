package com.classpath.orderrestapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="orders")

@Data
@Builder
@AllArgsConstructor
public class Order implements Serializable{
	
	private static final long serialVersionUID = -9167446676885721620L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Customer name cannot be blank")
	@NotNull(message = "Customer name is mandatory")
	private String name;
	
	@Email(message="email is not in correct format")
	private String email;
	
	@Min(value = 400, message = "min order price should be 400")
	@Max(value = 40000, message = "max order price should be 40000")
	private double price;
	
	//This is mandate from Hibernate
	private Order() {}
	
	@PastOrPresent(message="order date cannot be in the future")
	private LocalDate orderDate;
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<LineItem> lineItems;
	
	public void addLineItem(LineItem lineItem) {
		if (this.lineItems == null) {
			this.lineItems = new HashSet<>();
		}
		this.lineItems.add(lineItem);
		lineItem.setOrder(this);
	}

}
