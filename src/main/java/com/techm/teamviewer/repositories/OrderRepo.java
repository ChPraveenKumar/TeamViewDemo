package com.techm.teamviewer.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techm.teamviewer.bean.Order;


@Repository
@Transactional
public interface OrderRepo extends JpaRepository<Order, Serializable> {
	
	Order findByOrderId(Long orderId);
}
