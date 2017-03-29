package org.social.heartbeat.service;

import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.UserLocation;
import org.social.heartbeat.dao.LocationDAO;

public class LocationService {

	LocationDAO locationDAO = new LocationDAO();
	
	//Adding initial location of user
	public UserLocation storeUserIntialLocation(UserLocation userLocation) throws SQLException, ClassNotFoundException, JSONException{
		return locationDAO.storeUserIntialLocation(userLocation);
	}
}
