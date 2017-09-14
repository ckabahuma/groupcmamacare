package com.groupc.mamacare.service.impl;

import java.util.List;

import com.groupc.mamacare.dao.MamaCareDao;
import com.groupc.mamacare.dao.impl.MamaCareDaoImpl;
import com.groupc.mamacare.model.Visit;
import com.groupc.mamacare.model.Woman;
import com.groupc.mamacare.service.MamaCareService;

import android.content.Context;

/**
 * This class is responsible for saving a woman
 * 
 * @author jmpango
 *
 */
public class MamaCareServiceImpl implements MamaCareService {
	@SuppressWarnings("unused")
	private Context context;
	private MamaCareDao mamaCareDao;

	public MamaCareServiceImpl(Context context) {
		this.context = context;
		mamaCareDao = new MamaCareDaoImpl(context);
	}
	
	@Override
	public List<Woman> getWomen() {
		return mamaCareDao.getWomen();
	}

	@Override
	public List<Woman> saveWoman(Woman woman) {
		return mamaCareDao.saveWoman(woman);

	}

	@Override
	public void deleteWoman(int id) {
		mamaCareDao.deleteWoman(id);

	}

	@Override
	public Woman getWomanById(int id) {
		return mamaCareDao.getWomanById(id);
	}

	@Override
	public Woman editWoman(Woman woman) {
		return mamaCareDao.editWoman(woman);
	}

	@Override
	public Visit getVisitByTypeAndWomanId(String visitType, int womanId) {
		return mamaCareDao.getVisitByTypeAndWomanId(visitType, womanId);
	}

	@Override
	public void saveVisit(Visit visit) {
		mamaCareDao.saveVisit(visit);
	}

}
