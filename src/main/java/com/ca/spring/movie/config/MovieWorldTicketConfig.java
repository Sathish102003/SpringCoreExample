/**
 *
 */
package com.ca.spring.movie.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath:context.xml")
//@PropertySource(value = { "classpath:mysql.properties", "classpath:movie_ticket.properties" })
@ComponentScan({ "com.ca.spring.movie.bo", "com.ca.spring.movie.service", "com.ca.spring.movie.dao" })
@EnableTransactionManagement
public class MovieWorldTicketConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("movie_ticket");
        return messageSource;
    }

    @Bean
    public Map<String, Integer> parkingCharges() {
        final Map<String, Integer> parkingCharges = new HashMap<>();
        parkingCharges.put("Bus", 15);
        parkingCharges.put("Car", 12);
        parkingCharges.put("Bike", 10);
        parkingCharges.put("Cycle", 5);
        return parkingCharges;
    }
}
