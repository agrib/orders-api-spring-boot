package com.classpath.orderrestapi.util;

import static java.util.stream.IntStream.range;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.classpath.orderrestapi.model.LineItem;
import com.classpath.orderrestapi.model.Order;
import com.classpath.orderrestapi.repository.OrderRepository;
import com.github.javafaker.Faker;

@Configuration
public class BootstrapApplicationData implements ApplicationListener<ApplicationReadyEvent>{

	private final OrderRepository orderRepository;
	
	private final Faker faker = new Faker();
	
	public BootstrapApplicationData(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// insert records into the database
		// new Order("Ramesh", "Aravind", 12, 34, true, true ,false);
		
		//for(int i = 0; i < 100; i++) {
		range(0, 101)
			.forEach(index -> {
				String name = faker.name().firstName();
				Order order = Order
					       .builder()
						       .name(name)
						       .orderDate(faker.date().past(5, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
						       .email(name+"@"+faker.internet().domainName())
					       .build();
				
				IntStream.range(1,  faker.number().numberBetween(2, 5)).forEach(value -> {
					LineItem lineItem = LineItem
											.builder()
												.name(faker.commerce().productName())
												.qty(faker.number().numberBetween(2, 5))
												.price(faker.number().randomDouble(2, 500, 800))
											.build();
					order.addLineItem(lineItem);
					});
					Stream<LineItem> streamOfLineItems = order.getLineItems().stream();
					double totalOrderPrice = streamOfLineItems
												.map(li -> li.getQty() * li.getPrice())
												.reduce((v1 , v2) -> v1 + v2)
												.orElse(0d);
					order.setPrice(totalOrderPrice);
				this.orderRepository.save(order);
			});
	}
}
