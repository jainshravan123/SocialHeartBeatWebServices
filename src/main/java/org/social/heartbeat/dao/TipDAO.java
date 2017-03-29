package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.TipCategoryDataItem;
import org.social.heartbeat.util.DBConnection;

public class TipDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Getting Tips according to category
	public ArrayList<TipCategoryDataItem> getTipsAccordingToCategory(int cat_id) throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_tips_data where cat_id=?");
		ps1.setInt(1, cat_id);
		rs1  = ps1.executeQuery();
		
		ArrayList<TipCategoryDataItem> categoryDataItems = new ArrayList<TipCategoryDataItem>();
		
		while(rs1.next()){
			
			TipCategoryDataItem categoryDataItem = new TipCategoryDataItem();
			categoryDataItem.setId(rs1.getInt(1));
			categoryDataItem.setCategory_id(rs1.getInt(2));
			categoryDataItem.setData_item_heading(rs1.getString(3));
			categoryDataItem.setData_item(rs1.getString(4));
			categoryDataItems.add(categoryDataItem);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return categoryDataItems;
	}
}
