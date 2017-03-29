package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.City;
import org.social.heartbeat.bean.OfficeAddress;
import org.social.heartbeat.dao.OfficeAddressDAO;

public class OfficeAddressService {

	OfficeAddressDAO officeAddressDAO = new OfficeAddressDAO();
	
	//Getting all office addresses
	public ArrayList<OfficeAddress> getOfficeAddress() throws SQLException, ClassNotFoundException{
	      return officeAddressDAO.getOfficeAddress();
	}
	
	//Getting all cities
	public ArrayList<City> getAllCities() throws SQLException, ClassNotFoundException
	{
		 return officeAddressDAO.getAllCities();
	}
	
	//Getting nearest TCS Office to user's current location
	public OfficeAddress getNearestTCSOffice(String latitude, String longitude, String country_short_name) throws SQLException, ClassNotFoundException
	{
		return officeAddressDAO.getNearestTCSOffice(latitude, longitude, country_short_name);
	}
	
	//Getting nearest TCS Office to user's current location
	public OfficeAddress getNearestTCSOfficeComplete(String latitude, String longitude) throws SQLException, ClassNotFoundException
	{
		return officeAddressDAO.getNearestTCSOfficeComplete(latitude, longitude);
	}
		
}
