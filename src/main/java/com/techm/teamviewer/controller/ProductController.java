package com.techm.teamviewer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.teamviewer.bean.Product;
import com.techm.teamviewer.bean.ProductDTO;
import com.techm.teamviewer.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductServiceImpl productServiceImpl;

	/*
	 * @GetMapping public ResponseEntity<List<ProductDTO>> getAllProducts(){
	 * 
	 * Product product = new Product(); product.setProductId(1);
	 * product.setProductName("LG"); product.setProductDescription("WM");
	 * product.setProductPrice(10000d); return new
	 * ResponseEntity<List<Product>>(List.of(product), HttpStatus.OK);
	 * 
	 * return new
	 * ResponseEntity<>(getProductDTO(productServiceImpl.getAllProducts()),
	 * HttpStatus.OK); }
	 */
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productServiceImpl.createProduct(product), HttpStatus.CREATED);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) {
		return new ResponseEntity<>(productServiceImpl.getProductById(productId), HttpStatus.OK);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") long productId) {
		return new ResponseEntity<>(productServiceImpl.updateProduct(product, productId), HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") long productId) {
		productServiceImpl.deleteByProductId(productId);
		return new ResponseEntity<>("Product deleted Successfully", HttpStatus.OK);
	}

	private List<ProductDTO> getProductDTO(List<Product> prodList) {
		List<ProductDTO> prList = new ArrayList<>();
		prodList.forEach(product -> {
			prList.add(new ProductDTO(product.getProductName(), product.getProductPrice()));
		});
		return prList;
	}
}
