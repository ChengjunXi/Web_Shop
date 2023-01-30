package com.chengjunxi.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Component;

import com.chengjunxi.webshop.product.Product;
import com.chengjunxi.webshop.product.ProductRepository;

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
				new Product(2, "oho", 200, "This is oho.", 5)
				// new Product(3,"aha",300,"This is aha.",10),
				// new Product(4,"aha",400,"This is aha.",10),
				// new Product(5,"aha",500,"This is aha.",10),
				// new Product(6,"aha",600,"This is aha.",10),
				// new Product(7,"aha",700,"This is aha.",10),
				// new Product(8,"aha",800,"This is aha.",10),
				// new Product(9,"aha",900,"This is aha.",10),
				// new Product(10,"aha",1000,"This is aha.",10),
				// new Product(11,"aha",1100,"This is aha.",10),
				// new Product(12,"aha",1200,"This is aha.",10)
		));
	}
}