package com.movieshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

	private LocalDateTime date;
	private float totalPrice;
	private String address;
	private List<Movie> movies;

	// tuk trqbva da se dobavqt filmite !!!

	public Order() {
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
