package com.cestar.beans;

public class Book {
	private int id;
	private String name;
	private String author;
	private double price;
	private String genre;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String name, String author, double price, String genre) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.genre = genre;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
