package com.chengjunxi.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class WebShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}

@Component
class DataLoader {
	private final ProductRepository productRepository;

	public DataLoader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostConstruct
	private void loadData() {
		productRepository.saveAll(List.of(
				new Product(1,"aha",100,"This is aha.",10),
				new Product(2,"oho",500,"This is oho.",5)
		));
	}
}