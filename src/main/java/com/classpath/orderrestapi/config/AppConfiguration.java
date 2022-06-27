package com.classpath.orderrestapi.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @ConditionalOnProperty(prefix = "app", value = "loadUser", havingValue = "true", matchIfMissing = true)
    @Bean
    User user() {
        return new User();
    }
    
    @ConditionalOnBean(name="user")
    @Bean
    User userBasedOnBean() {
        return new User();
    }
    
    @ConditionalOnMissingBean(name="user")
    @Bean
    User userBasedOnMissingBean() {
        return new User();
    }
    
    @ConditionalOnMissingClass(value = "com.xyz.ABC")
    @Bean
    User userBasedOnClass() {
        return new User();
    }

}


class User {
	
}
