package com.movieshop.model;

public class Cart {

	private int id;
	private boolean isPaid;
	private Order order;

	public Cart(int id, boolean isPaid, Order order) {
		this.id = id;
		this.isPaid = isPaid;
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Cart() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

}
