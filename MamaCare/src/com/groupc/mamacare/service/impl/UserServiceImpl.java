package com.groupc.mamacare.service.impl;

import com.groupc.mamacare.dao.UserDao;
import com.groupc.mamacare.dao.impl.UserDaoImpl;
import com.groupc.mamacare.model.User;
import com.groupc.mamacare.service.UserService;

import android.content.Context;

public class UserServiceImpl implements UserService {
	
	private Context context;
	private UserDao userDao = new UserDaoImpl(context);

	public UserServiceImpl(Context context) {
		this.context = context;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	@Override
	public User getUser(String username, String password) {
		return userDao.getUser(username, password);
	}

	@Override
	public User editUser(User user) {
		return userDao.editUser(user);
	}

}
