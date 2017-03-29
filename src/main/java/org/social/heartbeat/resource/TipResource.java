package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.TipCategoryDataItem;
import org.social.heartbeat.service.TipService;

@Path("/tips")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipResource {

	TipService tipService = new TipService();
	
	@GET
	@Path("/{cat_id}")
	public ArrayList<TipCategoryDataItem> getTipsAccordingToCategory(@PathParam("cat_id") int cat_id) throws ClassNotFoundException, SQLException{
		return tipService.getTipsAccordingToCategory(cat_id);
	}
}
