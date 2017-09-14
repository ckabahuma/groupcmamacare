package com.groupc.mamacare.service;

import java.util.List;

import com.groupc.mamacare.model.Visit;
import com.groupc.mamacare.model.Woman;

/**
 * This interface is responsible for managing the Woman POJO
 * 
 * @author jmpango
 *
 */
public interface MamaCareService {
	public List<Woman> getWomen();
	public void saveWoman(Woman woman);
	public void deleteWoman(int id);
	public Woman getWomanById(int id);
	public Woman editWoman(Woman woman);
	public void saveVisit(Visit visit);
	public Visit getVisitByTypeAndWomanId(String visitType, int womanId);
	
}
