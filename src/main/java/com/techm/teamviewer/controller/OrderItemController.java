package com.techm.teamviewer.controller;

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

import com.techm.teamviewer.bean.OrderItemRequest;
import com.techm.teamviewer.bean.OrderItemResponse;
import com.techm.teamviewer.service.OrderItemServiceImpl;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

	@Autowired
	private OrderItemServiceImpl orderItemServiceImpl;

	@PostMapping
	public ResponseEntity<OrderItemResponse> createOrderItem(@RequestBody OrderItemRequest request) {
		return new ResponseEntity<>(orderItemServiceImpl.createOrderItem(request), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderItemResponse>> getAllOrderItems() {

		return new ResponseEntity<>((orderItemServiceImpl.getAllOrdersItem()), HttpStatus.OK);
	}
	
	@GetMapping("{id}") 
	public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable("id") int id){ 
		return new ResponseEntity<>((orderItemServiceImpl.getOrderItemById(id)), HttpStatus.OK);
	}
	
	@PutMapping("{id}") 
	public ResponseEntity<OrderItemResponse> updateOrderItem(OrderItemRequest request, @PathVariable("id") int id) {
		return new ResponseEntity<OrderItemResponse>(orderItemServiceImpl.updateOrderItem(request, id), HttpStatus.OK);
	}
	 
	@DeleteMapping("{id}") 
	public ResponseEntity<String> deleteItemById(@PathVariable("id") int id){ 
		orderItemServiceImpl.deleteByOrderItemId(id);
		return new ResponseEntity<>("OrderItem Deleted Successfully", HttpStatus.OK);
	}
	

}
