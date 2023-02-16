package com.chengjunxi.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.chengjunxi.webshop.product.Product;
import com.chengjunxi.webshop.product.ProductRepository;
import com.chengjunxi.webshop.product.Category;
import com.chengjunxi.webshop.product.CategoryRepository;
import com.chengjunxi.webshop.user.User;
import com.chengjunxi.webshop.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableAsync
public class WebShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}

@Component
class DataLoader {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	public DataLoader(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}

	@PostConstruct
	private void loadData() {
		productRepository.saveAll(List.of(
			new Product(1,"aha",100,"This is aha.",10,"fruit"),
			new Product(2, "oho", 200, "This is oho.", 5,"fruit"),
			new Product(3, "bla", 300, "This is bla.", 5,"soda")
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
		categoryRepository.saveAll(List.of(
			new Category(1,"fruit"),
			new Category(2, "soda")
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
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("123123");
		userRepository.saveAll(List.of(
			new User(1,"xcj",encodedPassword)
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