package com.groupc.mamacare.model;

import java.io.Serializable;

/**
 * Representation of a user in the momcare application
 * @author jmpango
 *
 */
public class User implements Serializable, Comparable<User>{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private int id;
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(User userz) {
		return this.username.compareToIgnoreCase(userz.getUsername());
	}

}
