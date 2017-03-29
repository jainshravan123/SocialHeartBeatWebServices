package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.social.heartbeat.bean.DonateCategory;
import org.social.heartbeat.bean.DonateItem;
import org.social.heartbeat.bean.DonateItemStatus;
import org.social.heartbeat.bean.Module;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.util.DBConnection;

public class DonateItemDAO 
{
	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Submitting a donation request
	public DonateItem createNewDonateItemRequest(DonateItem donateItem) throws SQLException, ClassNotFoundException
	{
		
		System.out.println("Inside Donate Item DAO");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String submission_date = df.format(dateobj);
		donateItem.setSubmission_date(submission_date);
		donateItem.setView_by_admin(submission_date);
		donateItem.setLast_processed(submission_date);
		
		con1 =  db.getConnection();
		String generatedColumns[] = {"id"};
		ps1  = con1.prepareStatement("insert into hb_donate_items(user_id, cat_id, no_of_items, status, description, submission_date, view_by_admin, last_processed) values(?, ?, ?, ?, ?, ?, ?, ?)", generatedColumns);
		ps1.setInt(1, donateItem.getUser().getId());
		ps1.setInt(2, donateItem.getDonateCategory().getId());
		ps1.setInt(3, donateItem.getNoOfItems());
		ps1.setInt(4, donateItem.getDonateItemStatus().getId());
		ps1.setString(5, donateItem.getDescription());
		ps1.setString(6, donateItem.getSubmission_date());
		ps1.setString(7, donateItem.getView_by_admin());
		ps1.setString(8, donateItem.getLast_processed());
		
		
	    int result  = ps1.executeUpdate();
	    rs1 = ps1.getGeneratedKeys();
	    
	    
	    
	    if(result > 0 )
	    {
		    while(rs1.next())
		    {
		    	donateItem.setId(rs1.getInt(1));
		    }
	    }
	    
	    rs1.close();
	    ps1.close();
		con1.close();
		
		return donateItem;
	}
	
	//Getting the Status of donated item
	public ArrayList<DonateItem> getDonatedItemsStatus(int user_id) throws SQLException, ClassNotFoundException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select t1.id, t1.user_id, t2.id As `cat_id`, t2.cat_name AS `cat_name`, t1.no_of_items, t3.id AS `status_id`, t3.status AS `status_name`, t1.description, t1.submission_date, t1.view_by_admin, t1.last_processed from `hb_donate_items` AS `t1`, `hb_donate_category` AS `t2`, `hb_donation_status` AS `t3` WHERE t1.cat_id = t2.id AND t1.status = t3.id AND `user_id`=?");
		ps1.setInt(1, user_id);
		rs1  = ps1.executeQuery();
		
		ArrayList<DonateItem> donated_items = new ArrayList<DonateItem>();
		
		while(rs1.next())
		{
			DonateItem donateItem = new DonateItem();
			
			User user = new User();
			DonateCategory donateCategory  = new DonateCategory();
			DonateItemStatus donateItemStatus = new DonateItemStatus();
			
			donateItem.setId(rs1.getInt(1));
			user.setId(rs1.getInt(2));
			donateCategory.setId(rs1.getInt(3));
			donateCategory.setCategory(rs1.getString(4));
			donateItem.setNoOfItems(rs1.getInt(5));
			donateItemStatus.setId(rs1.getInt(6));
			donateItemStatus.setStatus(rs1.getString(7));
			donateItem.setDescription(rs1.getString(8));
			donateItem.setSubmission_date(rs1.getString(9));
			donateItem.setView_by_admin(rs1.getString(10));
			donateItem.setLast_processed(rs1.getString(11));
			
			donateItem.setDonateCategory(donateCategory);
			donateItem.setDonateItemStatus(donateItemStatus);
			
			donated_items.add(donateItem);
			
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return donated_items;
		
	}
	
	//Getting status of single donated item
	public DonateItem getSingleDonatedItemStatus(int user_id, int donated_item_id) throws SQLException, ClassNotFoundException{
		
		con1 = db.getConnection();
		//ps1  = con1.prepareStatement("select * from hb_donate_items where user_id=? && id=?");
		ps1  = con1.prepareStatement("select t1.id, t1.user_id, t2.id As `cat_id`, t2.cat_name AS `cat_name`, t1.no_of_items, t3.id AS `status_id`, t3.status AS `status_name`, t1.description, t1.submission_date, t1.view_by_admin, t1.last_processed from `hb_donate_items` AS `t1`, `hb_donate_category` AS `t2`, `hb_donation_status` AS `t3` WHERE t1.cat_id = t2.id AND t1.status = t3.id AND t1.user_id=? AND t1.id=?");
		ps1.setInt(1, user_id);
		ps1.setInt(2, donated_item_id);
		rs1  = ps1.executeQuery();
		
		DonateItem donateItem = new DonateItem();
		
		while(rs1.next())
		{
			
			User user = new User();
			DonateCategory donateCategory  = new DonateCategory();
			DonateItemStatus donateItemStatus = new DonateItemStatus();
			
			donateItem.setId(rs1.getInt(1));
			user.setId(rs1.getInt(2));
			donateCategory.setId(rs1.getInt(3));
			donateCategory.setCategory(rs1.getString(4));
			donateItem.setNoOfItems(rs1.getInt(5));
			donateItemStatus.setId(rs1.getInt(6));
			donateItemStatus.setStatus(rs1.getString(7));
			donateItem.setDescription(rs1.getString(8));
			donateItem.setSubmission_date(rs1.getString(9));
			donateItem.setView_by_admin(rs1.getString(10));
			donateItem.setLast_processed(rs1.getString(11));
			
			donateItem.setDonateCategory(donateCategory);
			donateItem.setDonateItemStatus(donateItemStatus);
			
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return donateItem;
	}
	
	//Get donate category details by category id
	public  DonateCategory getDonateCategoryDetails(DonateCategory donateCategory_p) throws SQLException, ClassNotFoundException
	{
		Connection       con2 = null;
		PreparedStatement ps2 = null;
		ResultSet         rs2 = null;
		
		con2 = db.getConnection();
		ps2  = con2.prepareStatement("select * from hb_donate_category where id=?");
		ps2.setInt(1, donateCategory_p.getId());
		rs2  = ps2.executeQuery();
		DonateCategory donateCategory = new DonateCategory();
		while(rs2.next()){
			donateCategory.setId(rs2.getInt(1));
			donateCategory.setCategory(rs2.getString(2));
		}
		
		return donateCategory;
	}
	
	//Get donated Item Status details by donate status id
	public DonateItemStatus getDonatedItemStatusDetails(DonateItemStatus donateItemStatus_p) throws SQLException, ClassNotFoundException
	{
		Connection       con2 = null;
		PreparedStatement ps2 = null;
		ResultSet         rs2 = null;
		
		con2 = db.getConnection();
		ps2  = con2.prepareStatement("select * from hb_donation_status where id=?");
		ps2.setInt(1, donateItemStatus_p.getId());
		rs2  = ps2.executeQuery();
		DonateItemStatus donateItemStatus = new DonateItemStatus();
		while(rs2.next()){
			donateItemStatus.setId(rs2.getInt(1));
			donateItemStatus.setStatus(rs2.getString(2));
		}
		
		return donateItemStatus;
	}
	
}
