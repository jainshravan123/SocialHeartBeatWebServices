package org.social.heartbeat.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.UserLocation;
import org.social.heartbeat.service.LocationService;

@Path("/location")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
	
	LocationService locationService = new LocationService();
	
	@POST
	@Path("/user_loc")
	public UserLocation storeUserIntialLocation(UserLocation userLocation) throws SQLException, ClassNotFoundException, JSONException
	{
			return locationService.storeUserIntialLocation(userLocation);
	}

}
