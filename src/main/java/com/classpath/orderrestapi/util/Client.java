package com.classpath.orderrestapi.util;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Client implements CommandLineRunner{
	
	private final ApplicationContext applicationContext;
	
	public Client(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beanNames = this.applicationContext.getBeanDefinitionNames();
		
		
		//declarative style
		Arrays.asList(beanNames)
				.stream()
				.filter(beanName -> beanName.startsWith("user"))
				.forEach(bean -> System.out.println("Bean :: "+ bean));
				
	}

}
