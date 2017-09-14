package com.groupc.mamacare.dao.impl;

import com.groupc.mamacare.dao.UserDao;
import com.groupc.mamacare.database.Database;
import com.groupc.mamacare.model.User;

import android.content.Context;

/**
 * DAO class for mamacare app
 * @author gorishaba
 *
 */
public class UserDaoImpl implements UserDao{

	private Database database;
	
	public UserDaoImpl(Context context) {
		database = new Database(context);
	}
	
	@Override
	public void saveUser(User user) {
		database.saveUser(user);
		
	}

	@Override
	public void deleteUser(int id) {
		database.deleteUser(id);
		
	}

	@Override
	public User getUser(String username, String password) {
		return database.getUser(username, password);
	}

	@Override
	public User editUser(User user) {
		return database.editUser(user);
	}

}
