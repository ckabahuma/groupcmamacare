package com.groupc.mamacare.dao;

import java.util.List;

import com.groupc.mamacare.model.Visit;
import com.groupc.mamacare.model.Woman;

/**
 * This is the primary DAO interface for mamacare application
 * @author jmpango
 *
 */
public interface MamaCareDao {
	public List<Woman> getWomen();
	public List<Woman> saveWoman(Woman woman);
	public void deleteWoman(int id);
	public Woman editWoman(Woman woman);
	public void saveVisit(Visit visit);
	public Woman getWomanById(int id);
	public Visit getVisitByTypeAndWomanId(String visitType, int womanId);
}
