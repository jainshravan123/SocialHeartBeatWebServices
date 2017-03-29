package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.*;
import org.social.heartbeat.util.DBConnection;



public class CsrInitDAO 
{

	
	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	
	//Getting all of the CSRInit Categories
	public ArrayList<CsrInitCategory> getAllCsrInitCategories() throws ClassNotFoundException, SQLException
	{
		
		
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_csr_init_category");
		rs1  = ps1.executeQuery();
		
		ArrayList<CsrInitCategory> csr_init_cat_list = new ArrayList<CsrInitCategory>();
		
		while(rs1.next())
		{
			CsrInitCategory csrInitCategory = new CsrInitCategory();
			csrInitCategory.setId(rs1.getInt(1));
			csrInitCategory.setCat(rs1.getString(2));
			csrInitCategory.setCat_icon(rs1.getString(3));
			csrInitCategory.setStatus(rs1.getInt(4));
			
			csr_init_cat_list.add(csrInitCategory);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return csr_init_cat_list;
	}
	
	
	//Getting CSRInit Category By Category ID
	public CsrInitCategory getSingleCsrInitCategoryByCatId(int catId) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_csr_init_category where id = ?");
		ps1.setInt(1, catId);
		rs1  = ps1.executeQuery();
		
		CsrInitCategory csrInitCategory = new CsrInitCategory();
		
		while(rs1.next())
		{			
			csrInitCategory.setId(rs1.getInt(1));
			csrInitCategory.setCat(rs1.getString(2));
			csrInitCategory.setCat_icon(rs1.getString(3));	
			csrInitCategory.setStatus(rs1.getInt(4));
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return csrInitCategory;
	}
	
	
	
	//Getting CSRInit Categories Data
	public ArrayList<CsrInitCatData> getCsrInitCatData(int catId) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_csr_init_data where csr_init_id = ?");
		ps1.setInt(1, catId);
		rs1  = ps1.executeQuery();
		
		ArrayList<CsrInitCatData> csrInitCatDataList = new ArrayList<CsrInitCatData>();
		
		while(rs1.next())
		{
			CsrInitCategory csrInitCategory = new CsrInitCategory();
			
			
			CsrInitCatData csrInitCatData = new CsrInitCatData();
			csrInitCatData.setId(rs1.getInt(1));
			csrInitCategory.setId(rs1.getInt(2));
			csrInitCatData.setCat_data_heading(rs1.getString(3));
			csrInitCatData.setCat_data(rs1.getString(4));
			
			csrInitCatData.setCsrInitCategory(csrInitCategory);
			
			csrInitCatDataList.add(csrInitCatData);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return csrInitCatDataList;
	}
	
	
	
	
}
