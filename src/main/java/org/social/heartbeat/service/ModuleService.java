package org.social.heartbeat.service;

import org.social.heartbeat.dao.ModuleDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import org.social.heartbeat.bean.Module;

public class ModuleService 
{

	ModuleDAO moduleDAO = new ModuleDAO();
	
	
	public ArrayList<Module> getAllEnabledModules() throws ClassNotFoundException, SQLException{
		return moduleDAO.getAllEnabledModule();
	}
	
}
