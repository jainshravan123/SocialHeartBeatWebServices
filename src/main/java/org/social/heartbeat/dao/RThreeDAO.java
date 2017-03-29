package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.*;

import org.social.heartbeat.util.DBConnection;

public class RThreeDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Getting All Modules
	public ArrayList<RThreeModule> getAllModules() throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_r3_module");
		rs1  = ps1.executeQuery();
		
		ArrayList<RThreeModule> rThreeModules = new ArrayList<RThreeModule>();
		
		while(rs1.next())
		{
			RThreeModule rThreeModule = new RThreeModule();
			rThreeModule.setId(rs1.getInt(1));
			rThreeModule.setModule_name(rs1.getString(2));
			rThreeModule.setModule_icon(rs1.getString(3));
			rThreeModule.setStatus(rs1.getInt(4));
			
			rThreeModules.add(rThreeModule);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return rThreeModules;
	}
	
	//Getting Single Module based on id
    public RThreeModule getSingleModule(int id) throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_r3_module where id=?");
		ps1.setInt(1, id);
		rs1  = ps1.executeQuery();
		
		RThreeModule rThreeModule = new RThreeModule();
		
		while(rs1.next())
		{
			rThreeModule.setId(rs1.getInt(1));
			rThreeModule.setModule_name(rs1.getString(2));
			rThreeModule.setModule_icon(rs1.getString(3));
			rThreeModule.setStatus(rs1.getInt(4));
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return rThreeModule;
	 }
    
    //Getting All Categories
  	public ArrayList<RThreeCategory> getAllCategories() throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_category");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategory> rThreeCategories = new ArrayList<RThreeCategory>();
  		
  		while(rs1.next())
  		{
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(1));
  			rThreeCategory.setCategory_name(rs1.getString(2));
  			rThreeCategory.setAuthor(rs1.getString(3));
  			rThreeCategory.setCreation_date(rs1.getString(4));
  			rThreeCategory.setUpdation_date(rs1.getString(5));
  			
  			rThreeCategories.add(rThreeCategory);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategories;
  	}
  	
  	//Getting Single Category based on id
    public RThreeCategory getSingleCategory(int id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_category where id =?");
  		ps1.setInt(1, id);
  		rs1  = ps1.executeQuery();
  		
  		RThreeCategory rThreeCategory = new RThreeCategory();
  		
  		while(rs1.next())
  		{
  			rThreeCategory.setId(rs1.getInt(1));
  			rThreeCategory.setCategory_name(rs1.getString(2));
  			rThreeCategory.setAuthor(rs1.getString(3));
  			rThreeCategory.setCreation_date(rs1.getString(4));
  			rThreeCategory.setUpdation_date(rs1.getString(5));
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategory;
  	}

    //Getting All Categorical Items
    public ArrayList<RThreeCategorialItem> getAllCategoricalItems() throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_categorical_item");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		
  		while(rs1.next())
  		{
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			rThreeCategorialItem.setAuthor(rs1.getString(9));
  			rThreeCategorialItem.setHeading(rs1.getString(10));
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
  	}
    
    //Getting Single Categorical Item based on id
    public RThreeCategorialItem getSingleCategoricalItem(int id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_categorical_item where id=?");
  		ps1.setInt(1, id);
  		rs1  = ps1.executeQuery();
  		
  		RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  		
  		while(rs1.next())
  		{
  			
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			rThreeCategorialItem.setAuthor(rs1.getString(9));
  			rThreeCategorialItem.setHeading(rs1.getString(10));
  			
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItem;
  	}
    
    //Getting Categorical Item based on categorical id
    public ArrayList<RThreeCategorialItem> getCategoricalItemdByCategory(int category_id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_categorical_item where category_id=?");
  		ps1.setInt(1, category_id);
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		
  		
  		while(rs1.next())
  		{
  			
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			rThreeCategorialItem.setAuthor(rs1.getString(9));
  			rThreeCategorialItem.setHeading(rs1.getString(10));
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
  	}
}
