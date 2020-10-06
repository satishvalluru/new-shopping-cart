package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.ProductDAO;
import com.java.model.Product;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public void addProduct(Product product) {
		productDAO.addProduct(product);

	}

	@Override
	public List<Product> getAllProducts() {
	
		return productDAO.getAllProducts();
	}

	@Override
	public Product getProdct(int prodId) {
		return productDAO.getProduct(prodId);
	}
	

}
