package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.CartDAO;
import com.java.model.Cart;

@Service
@Transactional
public class CartsServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;

	@Override
	public void addCart(Cart cart) {
		cartDAO.addCart(cart);

		

	}

	

}

