package com.gian.shopifyAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ShopifyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopifyApiApplication.class, args);
	}

}