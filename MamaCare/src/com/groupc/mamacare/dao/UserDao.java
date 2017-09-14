package com.groupc.mamacare.dao;

import com.groupc.mamacare.model.User;

/**
 * DAO Interface used to manage User CRUD operations
 * @author ckabahuma
 *
 */
public interface UserDao {
	public void saveUser(User user);
	public void deleteUser(int id);
	public User getUser(String username, String password);
	public User editUser(User user);
}
