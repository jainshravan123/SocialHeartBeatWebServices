package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.DonateItem;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.dao.DonateItemDAO;

public class DonateItemService 
{
	  
	    DonateItemDAO donateItemDAO = new DonateItemDAO();    
	
	
	    //Submitting a donation request
		public DonateItem createNewDonateItemRequest(DonateItem donateItem) throws SQLException, ClassNotFoundException
		{
			System.out.println("Inside Donate Item Service");
			return donateItemDAO.createNewDonateItemRequest(donateItem);	
		}
		
		//Getting the Status of donated item
		public ArrayList<DonateItem> getDonatedItemsStatus(int user_id) throws SQLException, ClassNotFoundException
		{
			return donateItemDAO.getDonatedItemsStatus(user_id);
		}
		
		
		//Getting status of single donated item
		public DonateItem getSingleDonatedItemStatus(int user_id, int donated_item_id) throws SQLException, ClassNotFoundException{
			return donateItemDAO.getSingleDonatedItemStatus(user_id, donated_item_id);
		}

}
