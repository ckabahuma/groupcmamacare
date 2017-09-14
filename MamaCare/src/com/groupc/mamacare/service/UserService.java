package com.groupc.mamacare.service;

import com.groupc.mamacare.model.User;

/**
 * This interface is responsible for managing the User POJO
 * 
 * @author jmpango
 *
 */
public interface UserService {
	public void saveUser(User user);
	public void deleteUser(int id);
	public User getUser(String username, String password);
	public User editUser(User user);
}
