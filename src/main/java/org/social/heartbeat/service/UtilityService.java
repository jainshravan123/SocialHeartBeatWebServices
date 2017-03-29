package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.*;
import org.social.heartbeat.dao.UtilityDAO;

public class UtilityService 
{

	UtilityDAO utilityDAO = new UtilityDAO();
	
	//Getting All Utility Enabled Categories
	public ArrayList<UtilityCategory> getEnabledUtilityCategories() throws ClassNotFoundException, SQLException
	{
			return utilityDAO.getEnabledUtilityCategories();
	}
	
}
