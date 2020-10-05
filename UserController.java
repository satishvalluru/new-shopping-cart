package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Cart;
import com.java.model.Item;
import com.java.model.Product;
import com.java.model.User;
import com.java.service.ProductsService;
import com.java.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = Logger
			.getLogger(UserController.class);

	public UserController() {
		System.out.println("UserController()");
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductsService productsService;
	

	@RequestMapping(value = "/")
	public ModelAndView listUser(ModelAndView model) throws IOException {
		List<User> listUser = userService.getAllUsers();
		model.addObject("listUser", listUser);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("UserForm");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("Login");
		
		return model;
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(ModelAndView model) {
		User user = new User();
		if (user.getUsername().equalsIgnoreCase("satish") && user.getPassword().equalsIgnoreCase("satish")) {
			System.out.println(user.getUsername().equalsIgnoreCase("satish"));
			System.out.println(user.getPassword().equalsIgnoreCase("satish"));

			model.setViewName("List");

		} else {
			model.addObject("message", "invalid user");
			model.setViewName("Login");
		}
		return model;
	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authentication(@ModelAttribute User user) {
		
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView products(ModelAndView model) {
		List<Product> listProduct = productsService.getAllProducts();
		System.out.println(listProduct);
		model.addObject("listProduct", listProduct);
		model.setViewName("List");
		return model;
	}
	
	
	@RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
	public ModelAndView addToCart(@PathVariable("id") Integer prodId, HttpSession session,ModelAndView model) {	
		Cart cart = new Cart();
		System.out.println("cart " + cart);
		System.out.println("prodId " + prodId);
		Long totalCost = 0l;
		Product product = null;
		if (session.getAttribute("cart") == null) {
			List<Item> items = new ArrayList<Item>();
			product = productsService.getProdct(prodId);
			System.out.println("product :" + product);
			items.add(new Item(product, 1));
			totalCost += product.getPrice();
			System.out.println("totalCost " + totalCost );
			session.setAttribute("cart", items);
			cart.setCartId(1);
			cart.setItems(items);
			cart.setTotalCost(totalCost);
		} else {
			List<Item> items = (List<Item>) session.getAttribute("cart");
			int index = this.exists(prodId, items);
			product = productsService.getProdct(prodId);
			System.out.println("product :" + product);

			if (index == -1) {
				items.add(new Item(product, 1));
				totalCost += product.getPrice();
			} else {
				totalCost += product.getPrice();
				int quantity = items.get(index).getQuantity() + 1;
				items.get(index).setQuantity(quantity);
			}
			cart.setCartId(1);
			cart.setItems(items);
			cart.setTotalCost(totalCost);
			session.setAttribute("cart", items);
		}
		model.addObject("cartDetails", cart);
		model.setViewName("CartDetails");
		return model;
	}

	private int exists(Integer prodId, List<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getProductId() ==prodId ) {
				return i;
			}
		}
		return -1;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		if (user.getId() == 0) { 
			userService.addUser(user);
		} else {
			userService.updateUser(user);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userService.deleteUser(userId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUser(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);

		return model;
	}

	
}