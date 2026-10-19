package com.smartgrocery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductRequest {
	
	@NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
	private String name;
	
	@Positive(message = "Price must be greater than 0")
	private double price;
	
	@PositiveOrZero(message = "Quantity cannot be negative")
	private int quantity;
	
	public ProductRequest() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
