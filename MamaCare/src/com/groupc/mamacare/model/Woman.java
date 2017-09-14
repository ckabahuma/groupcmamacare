package com.groupc.mamacare.model;

import java.io.Serializable;
import java.util.List;

/**
 * This model class represents a woman in the mamacare application
 * 
 * @author jmpango
 *
 */
public class Woman implements Serializable, Comparable<Woman> {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int age;
	private String address;
	private int id;
	private List<Visit> visit;

	public Woman(int id, String firstName, String lastName, int age, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.id = id;
	}
	public Woman(int id, String firstName, String lastName, int age, String address, List<Visit> visit) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.id = id;
		this.visit = visit;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Visit> getVisit() {
		return visit;
	}
	public void setVisit(List<Visit> visit) {
		this.visit = visit;
	}
	@Override
	public int compareTo(Woman woman) {
		return this.lastName.compareToIgnoreCase(woman.getLastName());
	}

}
