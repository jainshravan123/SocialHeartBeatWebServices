package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.social.heartbeat.bean.Module;
import org.social.heartbeat.util.DBConnection;

public class ModuleDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	    //Getting all of the Enabled Modules
		public ArrayList<Module> getAllEnabledModule() throws ClassNotFoundException, SQLException
		{
				
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_module");
			rs1  = ps1.executeQuery();
			
			ArrayList<Module> enabled_module_list = new ArrayList<Module>();
			
			while(rs1.next())
			{
				Module module = new Module();
				module.setId(rs1.getInt(1));
				module.setModule_name(rs1.getString(2));
				module.setModule_icon(rs1.getString(3));
				module.setStatus(rs1.getInt(4));
				
				enabled_module_list.add(module);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return enabled_module_list;
		}
		
}
