package com.techm.teamviewer.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {
	
	@Id
	@Column(name = "OrderItem_Id")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	
	@Column(name = "OrderItem_Quantity")
	@NotNull
	private int orderItemQuantity;

	// many order items can have single order , so it is many to one with order.
    	@ManyToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "O_Id")
    	private Order order;

	// each order item associated with Product and it's required quantity. so it is one to one
    	@OneToOne
    	private Product product;
	
	public int getOrderItemId() {
		return orderItemId;
	}
	
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}
	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderItemQuantity=" + orderItemQuantity + ", order=" + order
				+ "]";
	}
}
