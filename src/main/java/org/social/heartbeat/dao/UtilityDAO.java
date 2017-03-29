package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.*;
import org.social.heartbeat.util.DBConnection;

public class UtilityDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();

	
	//Getting All Utility Enabled Categories
	public ArrayList<UtilityCategory> getEnabledUtilityCategories() throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_utility_category where status = ?");
		ps1.setInt(1, 1);
		rs1  = ps1.executeQuery();
		
		
		ArrayList<UtilityCategory> utilityCategories = new ArrayList<UtilityCategory>();
		
		
		
		
		while(rs1.next())
		{
			UtilityCategory utilityCategory = new UtilityCategory();
			utilityCategory.setId(rs1.getInt(1));
			utilityCategory.setUtility_cat_name(rs1.getString(2));
			utilityCategory.setStatus(rs1.getInt(3));
			utilityCategories.add(utilityCategory);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return utilityCategories;
	}
}
