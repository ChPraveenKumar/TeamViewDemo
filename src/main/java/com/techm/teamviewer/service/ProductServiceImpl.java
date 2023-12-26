package com.techm.teamviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.teamviewer.bean.Product;
import com.techm.teamviewer.repositories.ProductRepo;



@Service
public class ProductServiceImpl {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public Product getProductById(long productId) {
		return productRepo.findByProductId(productId);
	}
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	public Product updateProduct(Product product, long productId) {
		Product existingProduct = productRepo.findByProductId(productId);
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductPrice(product.getProductPrice());
		return productRepo.save(existingProduct);
	}
	
	public void deleteByProductId(long productId) {
		productRepo.deleteById(productId);
	}
}
