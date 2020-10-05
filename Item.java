package com.java.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Item implements Serializable {

	
	@OneToOne
	private Product product;
	
	@Column
	private int quantity;
	
	@Column
	private Long cost;
	
	public Item() {
		
	}
	
	public Item(Product prodct, int i) {
		this.product = prodct;
		this.quantity = i;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Item [product=" + product + ", quantity=" + quantity + ", cost=" + cost + "]";
	}

}
