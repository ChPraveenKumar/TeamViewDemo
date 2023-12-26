package com.techm.teamviewer.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.techm.teamviewer.bean.OrderItemRequest;
import com.techm.teamviewer.bean.OrderItemResponse;
import com.techm.teamviewer.service.OrderItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrerItemControllerTest {

	@InjectMocks
	OrderItemController orderItemController;
	
	@Mock
	OrderItemServiceImpl orderItemServiceImpl;
	
	@Test
	void orderItemTest() {
		Mockito.when(orderItemServiceImpl.createOrderItem(Mockito.any())).thenReturn(getOrderItemResponse());
		ResponseEntity<OrderItemResponse> createOrder = orderItemController.createOrderItem(new OrderItemRequest());
		Assertions.assertEquals(10, createOrder.getBody().getQuantity());
	}

	private OrderItemResponse getOrderItemResponse() {
		OrderItemResponse response = new OrderItemResponse();
		response.setOrderId(1);
		response.setOrderItemId(1);
		response.setQuantity(10);
		return response;
	}
	
	@Test
	void getAllOrderItemTest() {
		Mockito.when(orderItemServiceImpl.getAllOrdersItem()).thenReturn(List.of((getOrderItemResponse())));
		ResponseEntity<List<OrderItemResponse>> allOrderItems = orderItemController.getAllOrderItems();
		Assertions.assertEquals(1, allOrderItems.getBody().get(0).getOrderItemId());
	}
	
	@Test
	void getOrderItemByIdTest() {
		Mockito.when(orderItemServiceImpl.getOrderItemById(Mockito.anyInt())).thenReturn(getOrderItemResponse());
		ResponseEntity<OrderItemResponse> orderItemById = orderItemController.getOrderItemById(Mockito.anyInt());
		Assertions.assertEquals(1,orderItemById.getBody().getOrderItemId());
	}
	
	@Test
	void getOrderItemUpdatedById() {
		Mockito.when(orderItemServiceImpl.updateOrderItem(Mockito.any(OrderItemRequest.class),Mockito.anyInt())).thenReturn(getOrderItemResponse());
		ResponseEntity<OrderItemResponse> updateOrderItem = orderItemController.updateOrderItem(getOrderItems(),1);
		Assertions.assertEquals(10, updateOrderItem.getBody().getQuantity());
	}
	private OrderItemRequest getOrderItems() {
		OrderItemRequest itemRequest=new OrderItemRequest();
		itemRequest.setQuantity(1);
		return itemRequest;
	}
	
	@Test
	void getOrderItemDeleteById() {
		Mockito.doNothing().when(orderItemServiceImpl).deleteByOrderItemId(Mockito.anyInt());
		ResponseEntity<String> response = orderItemController.deleteItemById(2);
		Assertions.assertEquals("OrderItem Deleted Successfully",response.getBody());
	}
	
}
