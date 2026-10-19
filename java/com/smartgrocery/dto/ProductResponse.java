package com.smartgrocery.dto;

public class ProductResponse {
	
	private Long id;
	private String name;
	private double price;
	private int quantity;
	
	public ProductResponse() {
		
	}
	
	public ProductResponse(Long id, String name, double price, int quantity){
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Long getId(){
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

}
