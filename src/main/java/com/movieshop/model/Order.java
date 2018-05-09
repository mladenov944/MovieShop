package com.movieshop.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Order {

	private LocalDateTime date;
	private float totalPrice;
	private String address;
	private List<Movie> movies;

	
	public Order(String address, float totalPrice, List<Movie> movies) {
		this.date = LocalDateTime.now();
		this.totalPrice = totalPrice;
		this.address = address;
		this.movies = movies;
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


	public List<Movie> getMovies() {
		return Collections.unmodifiableList(movies);
	}

}
