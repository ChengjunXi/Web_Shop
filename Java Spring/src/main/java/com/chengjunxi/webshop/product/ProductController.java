package com.chengjunxi.webshop.product;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
@RequestMapping("/products")
class ProductController {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/by_category/{category}")
	List<Integer> getProductIdsByCategory(@PathVariable String category) {
		List<Product> products = productRepository.findAllByCategory(category);
		List<Integer> ids = new ArrayList<>();
		for (Product product : products) {
			ids.add(product.getId());
		}
		return ids;
	}

	@GetMapping("/{id}")
	Optional<Product> getProductById(@PathVariable String id) {
		return productRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("/meta/num_products")
	Long getProductCount() {
		return productRepository.count();
	}

	@GetMapping("/meta/category_list")
	List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}

	@PostMapping
	Product postProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/{id}")
	ResponseEntity<Product> putProduct(@PathVariable String id,
			@RequestBody Product product) {

		return (productRepository.existsById(Integer.parseInt(id)))
				? new ResponseEntity<>(productRepository.save(product), HttpStatus.OK)
				: new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	void deleteProduct(@PathVariable String id) {
		productRepository.deleteById(Integer.parseInt(id));
	}
}

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
@RequestMapping("/product_img")
class ProductImgController {
	@GetMapping("/{id}")
	@ResponseBody
	byte[] getImgById(@PathVariable String id) throws IOException {
		InputStream input = getClass().getResourceAsStream("/static/imgs/" + id + ".jpg");
		return IOUtils.toByteArray(input);
	}
}