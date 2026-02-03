package com.rays.test;

public class Order {

	private String orderNo;
	private String item;
	private String name;

	public Order() {
	}

	public Order(String orderNo, String item, String name) {
		this.orderNo = orderNo;
		this.item = item;
		this.name = name;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
