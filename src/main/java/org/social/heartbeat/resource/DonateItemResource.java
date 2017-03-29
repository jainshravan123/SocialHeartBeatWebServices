package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.DonateItem;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.service.DonateItemService;

@Path("/donate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DonateItemResource
{

	DonateItemService donateItemService = new DonateItemService();
	
	@POST
	@Path("/donateItem")
	public DonateItem createNewDonateItemRequest(DonateItem donateItem) throws SQLException, ClassNotFoundException
	{
		System.out.println("Inside Donate Item Resource");
		return donateItemService.createNewDonateItemRequest(donateItem);	
	}
	
	@GET
	@Path("/itemStatus/{user_id}")
	public ArrayList<DonateItem> getDonatedItemsStatus(@PathParam("user_id") int user_id) throws SQLException, ClassNotFoundException
	{
		return donateItemService.getDonatedItemsStatus(user_id);
	}
	
	@GET
	@Path("/itemStatus/{user_id}/{donated_item_id}")
	public DonateItem getSingleDonatedItemStatus(@PathParam("user_id") int user_id, @PathParam("donated_item_id") int donated_item_id) throws SQLException, ClassNotFoundException{
		return donateItemService.getSingleDonatedItemStatus(user_id, donated_item_id);
	}
	
}
