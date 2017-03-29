package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.social.heartbeat.bean.UtilityCategory;
import org.social.heartbeat.service.UtilityService;

@Path("/utility")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtilityResource 
{

	UtilityService utilityService = new UtilityService();
	
	@GET
	@Path("/enabledCategory")
	//Getting All Utility Enabled Categories
	public ArrayList<UtilityCategory> getEnabledUtilityCategories() throws ClassNotFoundException, SQLException
	{
			return utilityService.getEnabledUtilityCategories();
	}
}
