package com.techm.teamviewer.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techm.teamviewer.bean.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Serializable> {

	Product findByProductId(long productId);


}
