package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.UserLocation;
import org.social.heartbeat.client.UserLocationClient;
import org.social.heartbeat.util.DBConnection;

public class LocationDAO 
{

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	public UserLocation storeUserIntialLocation(UserLocation userLocation_p) throws SQLException, ClassNotFoundException, JSONException{
		
		UserLocationClient userLocationClient = new UserLocationClient();
		UserLocation userLocation = userLocationClient.getUserLocationByLatLong(userLocation_p);
		
		System.out.println("::: Inside Location DAO (User Location):::");
		System.out.println(userLocation.getCity());
		System.out.println(userLocation.getCountry());
		userLocation_p.setCity(userLocation.getCity());
		userLocation_p.setCountry(userLocation.getCountry());
		
		con1 =  db.getConnection();
		String generatedColumns[] = {"id"};
		ps1  = con1.prepareStatement("insert into hb_user_location(id, user_id, latitude, longitude, city, country) values(?, ?, ?, ?, ?, ?)", generatedColumns);
		ps1.setInt(1, userLocation_p.getId());
		ps1.setString(2, userLocation_p.getUser_id());
		ps1.setString(3, userLocation_p.getLatitude());
		ps1.setString(4, userLocation_p.getLongitude());
		ps1.setString(5, userLocation_p.getCity());
		ps1.setString(6, userLocation_p.getCountry());
		
	    int result  = ps1.executeUpdate();
	    
	    rs1 = ps1.getGeneratedKeys();
	    	  
	    if(result > 0 )
	    {
		    while(rs1.next())
		    {
		    	userLocation_p.setLocation_id(rs1.getInt(1));
		    }
	    }	    
	    
	    rs1.close();
	    ps1.close();
		con1.close();
		
		
		return userLocation_p;
	}
	
}
