package com.java.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.model.Cart;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCart(Cart cart) {
		sessionFactory.getCurrentSession().saveOrUpdate(cart);

	}

}
