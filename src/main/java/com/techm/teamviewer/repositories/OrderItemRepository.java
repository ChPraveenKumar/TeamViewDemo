package com.techm.teamviewer.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techm.teamviewer.bean.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Serializable> {

	OrderItem findByOrderItemId(long itemId);
}
