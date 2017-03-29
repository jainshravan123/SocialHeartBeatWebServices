package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.CsrInitCatData;
import org.social.heartbeat.bean.CsrInitCategory;
import org.social.heartbeat.service.CsrInitService;


@Path("/csrInitCategories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CsrInitResource 
{
	
	CsrInitService csrInitService = new CsrInitService();

    @GET
	public ArrayList<CsrInitCategory> getAllCsrInitCategories(@PathParam("userId") int userId) throws ClassNotFoundException, SQLException
	{
		return csrInitService.getAllCsrInitCategories();
	}
    
    @GET
    @Path("/{catId}")
    public CsrInitCategory getSingleCsrInitCategoryByCatId(@PathParam("catId") int catId) throws ClassNotFoundException, SQLException
    {
    	return csrInitService.getSingleCsrInitCategoryByCatId(catId);
    }
    
    @GET
    @Path("/cat_data/{catId}")
    public ArrayList<CsrInitCatData> getCsrInitCatData(@PathParam("catId") int catId) throws ClassNotFoundException, SQLException{
    	return csrInitService.getCsrInitCatData(catId);
    }
    
	
}
