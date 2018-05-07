package com.movieshop.model;

public class Movie {

	private int id;
	private String name;
	private String director;
	private short year;
	private String summary;
	private String picture;
	private float price;
	private String genre;
	private String infoLink;
	private int quantity;
	private Cart cart;

	public Movie(String name, String director, short year, String summary, String picture, float price, String genre,
			String infoLink, short quantity) {
		this.name = name;
		this.director = director;
		this.year = year;
		this.summary = summary;
		this.picture = picture;
		this.price = price;
		this.genre = genre;
		this.infoLink = infoLink;
		this.quantity = quantity;
	}

	public Movie() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getInfoLink() {
		return infoLink;
	}

	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}

	public Cart getTrolley() {
		return cart;
	}

	public void setTrolley(Cart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
