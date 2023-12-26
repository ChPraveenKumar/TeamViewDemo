package com.techm.teamviewer.controller;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.techm.teamviewer.bean.Order;
import com.techm.teamviewer.bean.OrderDTO;
import com.techm.teamviewer.bean.OrderItemDTO;
import com.techm.teamviewer.bean.OrderRequest;
import com.techm.teamviewer.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderServiceImpl orderServiceImpl;
	
	@Test
	void testGetAllOrders() {
		Order order=new Order();
		order.setOrderId(10);
		order.setOrderDate(new Date());
		order.setOrderPrice(2000);
		order.setOrderBy("praveen");
		order.setFromAddress("Hyd");
		order.setShippingAddress("Chennai");
		Mockito.when(orderServiceImpl.getAllOrders()).thenReturn(List.of(getOrderRequest()));
		ResponseEntity<List<OrderRequest>> allorders=orderController.getAllOrders();
		Assertions.assertEquals(2000, allorders.getBody().get(0).getOrderPrice());
	}
	private Order getOrders() {
		Order order=new Order();
		order.setOrderId(10);
		order.setOrderDate(new Date());
		order.setOrderPrice(1000);
		order.setOrderBy("praveen");
		order.setFromAddress("Hyd");
		order.setShippingAddress("Chennai");
		return order;
	}
	
	private OrderRequest getOrderRequest() {
		OrderRequest request = new OrderRequest();
		request.setOrderPrice(2000);
		return request;
	}
	
	@Test
	void getOrderByIdTest() {
		Mockito.when(orderServiceImpl.getOrderById(Mockito.anyLong())).thenReturn(getOrderRequest());
		ResponseEntity<OrderRequest> order=orderController.getOrderById(Mockito.anyLong());
		Assertions.assertEquals(2000, order.getBody().getOrderPrice());
	}
	
	@Test
	void updateOrderTest() {
		Mockito.when(orderServiceImpl.updateProduct(Mockito.any(Order.class),Mockito.anyLong())).thenReturn(getOrderRequest());
		ResponseEntity<OrderRequest> updateOrder=orderController.updateOrder(getOrders(),10);
		Assertions.assertEquals(2000, updateOrder.getBody().getOrderPrice());
	}
	
	@Test
	void deleteOrderByIdTest() {
		Mockito.doNothing().when(orderServiceImpl).deleteByOrderId(Mockito.anyLong());
		ResponseEntity<String> response=orderController.deleteOrderById(2);
		Assertions.assertEquals("Order deleted Successfully", response.getBody());
	}
	
	@Test
	void testCreateOrder() {
		Mockito.when(orderServiceImpl.createOrder(Mockito.any())).thenReturn(getOrderDTO());
		ResponseEntity<OrderDTO> createOrder = orderController.createOrders(new OrderDTO());
		Assertions.assertEquals(20000, createOrder.getBody().getOrderPrice());
	}
	
	private OrderDTO getOrderDTO() {
		OrderItemDTO idto = new OrderItemDTO();
		idto.setQuantity(1);
		OrderDTO dto = new OrderDTO();
		dto.setFromAddress("gooty");
		dto.setShippingAddress("bng");
		dto.setOrderBy("dush");
		dto.setOrderPrice(20000);
		dto.setItemList(List.of(idto));
		return dto;
	}
	
	
}
