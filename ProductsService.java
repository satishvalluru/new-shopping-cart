package com.java.service;

import java.util.List;

import com.java.model.Product;

public interface ProductsService {

	public void addProduct(Product product);

	public List<Product> getAllProducts();
	public Product getProdct(int prodId);

}
