package org.social.heartbeat.service;

import org.social.heartbeat.bean.*;
import org.social.heartbeat.dao.CsrInitDAO;

import java.sql.SQLException;
import java.util.ArrayList;


public class CsrInitService 
{

	CsrInitDAO csrInitDAO = new CsrInitDAO();
	
	
	//Getting all of the categories
	public ArrayList<CsrInitCategory> getAllCsrInitCategories() throws ClassNotFoundException, SQLException
	{
		return csrInitDAO.getAllCsrInitCategories();
	}
	
	
	//Getting CSRInit Category By Category ID
	public CsrInitCategory getSingleCsrInitCategoryByCatId(int catId) throws ClassNotFoundException, SQLException
	{
		return csrInitDAO.getSingleCsrInitCategoryByCatId(catId);
	}
	
	//Getting Categories Data by use of category id
	public ArrayList<CsrInitCatData> getCsrInitCatData(int catId) throws ClassNotFoundException, SQLException
	{
		return csrInitDAO.getCsrInitCatData(catId);
	}
	
} 
