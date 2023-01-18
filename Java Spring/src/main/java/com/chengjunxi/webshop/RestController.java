package com.chengjunxi.webshop;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
class ProductController {
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping
	Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Product> getProductById(@PathVariable String id) {
		return productRepository.findById(Integer.parseInt(id));
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product_img")
class ProductImgController {
	@GetMapping("/{id}")
	@ResponseBody
	byte[] getImgById(@PathVariable String id) throws IOException {
		InputStream input = getClass().getResourceAsStream("/static/imgs/" + id + ".jpg");
		return IOUtils.toByteArray(input);
	}
}