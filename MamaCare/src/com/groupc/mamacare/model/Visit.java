package com.groupc.mamacare.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Representation of a visit in the MamaCare application
 * @author ckabahuma
 *
 */
public class Visit implements Serializable, Comparable<Visit>{

	private static final long serialVersionUID = 1L;
	private int id;
	private String visitType;
	private Date visitDate;
	private int isVacinated;
	private int womanId;
	
	public Visit(int id, String visitType, Date visitDate, int isVacinated, int womanId) {
		this.id = id;
		this.visitType = visitType;
		this.visitDate = visitDate;
		this.isVacinated = isVacinated;
		this.womanId = womanId;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getVisitType() {
		return visitType;
	}



	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}



	public Date getVisitDate() {
		return visitDate;
	}



	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public int getWomanId() {
		return womanId;
	}

	public void setWomanId(int womanId) {
		this.womanId = womanId;
	}

	public int getIsVacinated() {
		return isVacinated;
	}

	public void setIsVacinated(int isVacinated) {
		this.isVacinated = isVacinated;
	}

	@Override
	public int compareTo(Visit visitz) {
		return this.visitType.compareToIgnoreCase(visitz.getVisitType());
	}

}
