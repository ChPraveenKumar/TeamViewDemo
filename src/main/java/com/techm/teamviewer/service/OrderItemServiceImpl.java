package com.techm.teamviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.teamviewer.bean.OrderItem;
import com.techm.teamviewer.bean.OrderItemRequest;
import com.techm.teamviewer.bean.OrderItemResponse;
import com.techm.teamviewer.repositories.OrderItemRepository;
import com.techm.teamviewer.repositories.OrderRepo;

@Service
public class OrderItemServiceImpl {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepo orderRepo;
	
    
	public List<OrderItemResponse> getAllOrdersItem() {
		OrderItem orderItem = new OrderItem();
		OrderItemRequest request=new OrderItemRequest();
		orderItem.setOrderItemQuantity(request.getQuantity());
		orderItem.setOrder(orderRepo.findByOrderId(request.getOrderId()));
		return convertGetOrderAllItems(orderItemRepository.findAll());
	}
	
	private List<OrderItemResponse> convertGetOrderAllItems(List<OrderItem> allItem) {
		
		List<OrderItemResponse> itemResponses=new ArrayList<>();
		for(OrderItem orderItem: allItem) {
			itemResponses.add(convertToResponse(orderItem));
		}
		return itemResponses;
	}

	public OrderItemResponse createOrderItem(OrderItemRequest request) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemQuantity(request.getQuantity());
		orderItem.setOrder(orderRepo.findByOrderId(request.getOrderId()));
		return convertToResponse(orderItemRepository.save(orderItem)) ;
	}
	
	private OrderItemResponse convertToResponse(OrderItem item) {
		OrderItemResponse resp = new OrderItemResponse();
		resp.setOrderId(item.getOrder().getOrderId());
		resp.setOrderItemId(item.getOrderItemId());
		resp.setQuantity(item.getOrderItemQuantity());
		return resp;
	}
	
	public OrderItemResponse getOrderItemById(int id) {
		return convertToResponse(orderItemRepository.getById(id));
	}
	
	public void deleteByOrderItemId(int id) {
		orderItemRepository.deleteById(id);
	}
	
	public OrderItemResponse updateOrderItem(OrderItemRequest request, int itemId) {
		OrderItem exit = orderItemRepository.findByOrderItemId(itemId);
		exit.setOrderItemQuantity(request.getQuantity());
		//ot.setOrderItemId(request.getOrderId());
		return convertToResponse(orderItemRepository.save(exit));
	}
	
}
