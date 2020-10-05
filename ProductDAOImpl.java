package com.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addProduct(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);

	}

	public List<Product> getAllProducts() {

		return sessionFactory.getCurrentSession().createQuery("from Product")
				.list();
	}

	@Override
	public Product getProduct(int prodId) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, prodId);
	}

}