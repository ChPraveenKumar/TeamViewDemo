package com.techm.teamviewer.bean;

public class OrderItemRequest {
	
	private int quantity;
	
	private long orderId;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItemRequest [quantity=" + quantity + ", orderId=" + orderId + "]";
	}	
}
