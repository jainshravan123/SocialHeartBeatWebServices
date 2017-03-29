package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.TipCategoryDataItem;
import org.social.heartbeat.dao.TipDAO;

public class TipService {

	TipDAO tipDao = new TipDAO();
	
	//Getting Tips according to category
	public ArrayList<TipCategoryDataItem> getTipsAccordingToCategory(int cat_id) throws ClassNotFoundException, SQLException{
		return tipDao.getTipsAccordingToCategory(cat_id);
	}
	
		
}
