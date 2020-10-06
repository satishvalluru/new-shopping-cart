package com.java.service;

import java.util.List;

import com.java.model.User;

public interface UserService {
	
	public void addUser(User user);

	public List<User> getAllUsers();

	public void deleteUser(Integer userId);

	public User getUser(int userid);

	public User updateUser(User user);
}
