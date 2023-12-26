package com.techm.teamviewer.bean;

import java.util.List;

public class OrderRequest {

    private double orderPrice;
    
	private String orderBy;
	
	private String shippingAddress;
	
	private String fromAddress;
	
	private List<OrderItemDTO> dtoList;

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	

	public List<OrderItemDTO> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<OrderItemDTO> dtoList) {
		this.dtoList = dtoList;
	}

	@Override
	public String toString() {
		return "OrderRequest [orderPrice=" + orderPrice + ", orderBy=" + orderBy + ", shippingAddress="
				+ shippingAddress + ", fromAddress=" + fromAddress + "]";
	}
}
