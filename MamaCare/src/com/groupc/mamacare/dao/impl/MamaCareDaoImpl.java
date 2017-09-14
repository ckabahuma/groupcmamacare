package com.groupc.mamacare.dao.impl;

import java.util.List;

import com.groupc.mamacare.dao.MamaCareDao;
import com.groupc.mamacare.database.Database;
import com.groupc.mamacare.model.Visit;
import com.groupc.mamacare.model.Woman;

import android.content.Context;

/**
 * MamaCare implementation Class#MamaCareDao
 * @author jmpango
 *
 */
public class MamaCareDaoImpl implements MamaCareDao {
	
	private Database database;
	
	public MamaCareDaoImpl(Context context) {
		database = new Database(context);
	}

	@Override
	public List<Woman> getWomen() {
		return database.getWomen();
	}

	@Override
	public void saveWoman(Woman woman) {
		database.saveWoman(woman);
		
	}

	@Override
	public void deleteWoman(int id) {
		database.deleteWoman(id);
		
	}

	@Override
	public Woman getWomanById(int id) {
		return database.getWoman(id);
	}

	@Override
	public Woman editWoman(Woman woman) {
		return database.editWoman(woman);
	}

	@Override
	public Visit getVisitByTypeAndWomanId(String visitType, int womanId) {
		return database.getVisitByType(visitType, womanId);
	}

	@Override
	public void saveVisit(Visit visit) {
		database.saveVisit(visit);
	}

}
