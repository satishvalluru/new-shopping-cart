package com.java.dao;

import java.util.List;

import com.java.model.Product;

public interface ProductDAO {

	public void addProduct(Product product);

	public List<Product> getAllProducts();

	public Product getProduct(int prodId);

	
}
