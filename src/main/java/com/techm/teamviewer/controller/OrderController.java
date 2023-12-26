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

import com.techm.teamviewer.bean.Order;
import com.techm.teamviewer.bean.OrderDTO;
import com.techm.teamviewer.bean.OrderRequest;
import com.techm.teamviewer.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@PostMapping
	public ResponseEntity<OrderDTO> createOrders(@RequestBody OrderDTO orderDTO){
		return new ResponseEntity<>(orderServiceImpl.createOrder(orderDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderRequest>> getAllOrders(){
		return new ResponseEntity<>((orderServiceImpl.getAllOrders()), HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<OrderRequest> getOrderById(@PathVariable("id") long orderId){
		return new ResponseEntity<>(orderServiceImpl.getOrderById(orderId), HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<OrderRequest> updateOrder(@RequestBody Order order,@PathVariable("id") long orderId){
		return new ResponseEntity<>(orderServiceImpl.updateProduct(order, orderId), HttpStatus.OK);
	} 
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable("id") long orderId){
		orderServiceImpl.deleteByOrderId(orderId);
		return new ResponseEntity<>("Order deleted Successfully",HttpStatus.OK);
	}
}
