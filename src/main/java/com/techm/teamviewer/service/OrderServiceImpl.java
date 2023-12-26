package com.techm.teamviewer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.teamviewer.bean.Order;
import com.techm.teamviewer.bean.OrderDTO;
import com.techm.teamviewer.bean.OrderItem;
import com.techm.teamviewer.bean.OrderItemDTO;
import com.techm.teamviewer.bean.OrderRequest;
import com.techm.teamviewer.repositories.OrderItemRepository;
import com.techm.teamviewer.repositories.OrderRepo;

@Service
public class OrderServiceImpl {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderItemRepository orderItemRepository;

	public List<OrderRequest> getAllOrders() {

		return convertGetAllOrders(orderRepo.findAll());
	}
	private List<OrderRequest> convertGetAllOrders(List<Order> allOrders) {
		List<OrderRequest> orderRequestList = new ArrayList<>();
		for(Order order : allOrders) {
			orderRequestList.add(convertGetOrderIdResponse(order));
		}
		return orderRequestList;
	}
	
	public OrderDTO createOrder(OrderDTO orderDTO) {
		List<OrderItem> items = new ArrayList<>();
		Order order = new Order();
		order.setOrderBy(orderDTO.getOrderBy());
		order.setOrderDate(new Date());
		order.setOrderPrice(orderDTO.getOrderPrice());
		order.setFromAddress(orderDTO.getFromAddress());
		order.setShippingAddress(orderDTO.getShippingAddress());
		orderDTO.getItemList().forEach(t -> {
			OrderItem item = new OrderItem();
			item.setOrderItemQuantity(t.getQuantity());
			items.add(item);
		});
		order.setOrderItems(items);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss Z");
		orderDTO.setOrderDate(sdf.format(order.getOrderDate()));
		orderRepo.save(order);
		return orderDTO;
	}

	public OrderRequest getOrderById(long orderId) {
		return convertGetOrderIdResponse(orderRepo.findByOrderId(orderId));
	}

	private OrderRequest convertGetOrderIdResponse(Order order) {
		OrderRequest orderRequest=new OrderRequest();
		orderRequest.setOrderPrice(order.getOrderPrice());
		orderRequest.setOrderBy(order.getOrderBy());
		orderRequest.setFromAddress(order.getFromAddress());
		orderRequest.setShippingAddress(order.getShippingAddress());
		List<OrderItemDTO> dtoList = new ArrayList<>();
		order.getOrderItems().forEach(t -> {
			OrderItemDTO dto = new OrderItemDTO();
			dto.setQuantity(t.getOrderItemQuantity());
			dtoList.add(dto);
		});
		orderRequest.setDtoList(dtoList);
		return orderRequest;
	}

	public OrderRequest updateProduct(Order order, long orderId) {
		Order existingOrder = orderRepo.findByOrderId(orderId);
		existingOrder.setOrderBy(order.getOrderBy());
		existingOrder.setOrderDate(new Date());
		existingOrder.setOrderPrice(order.getOrderPrice());
		existingOrder.setFromAddress(order.getFromAddress());
		existingOrder.setShippingAddress(order.getShippingAddress());
		return convertGetOrderIdResponse(orderRepo.save(existingOrder));
	}

	public void deleteByOrderId(long orderId) {
		orderRepo.deleteById(orderId);
	}

}
