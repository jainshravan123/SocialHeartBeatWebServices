package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.RThreeCategorialItem;
import org.social.heartbeat.bean.RThreeCategory;
import org.social.heartbeat.bean.RThreeModule;
import org.social.heartbeat.bean.RThreeMostPopularCategory;
import org.social.heartbeat.bean.RThreeType;
import org.social.heartbeat.service.RThreeService;

@Path("/rThree")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RThreeResource {

	RThreeService rThreeService = new RThreeService();
	
	@GET
	@Path("/modules")
	public ArrayList<RThreeModule> getAllModules() throws ClassNotFoundException, SQLException{
		return rThreeService.getAllModules();
    }
	
	@GET
	@Path("/modules/{id}")
	public RThreeModule getSingleModule(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
    	return rThreeService.getSingleModule(id);
    }
	
	@GET
	@Path("/categories")
	public ArrayList<RThreeCategory> getAllCategories() throws ClassNotFoundException, SQLException{
  		return rThreeService.getAllCategories();
  	}
	
	@GET
	@Path("/categories/{id}")
	public RThreeCategory getSingleCategory(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
    	return rThreeService.getSingleCategory(id);
    }
	
	/*
	@GET
	@Path("/categoricalItems")
	public ArrayList<RThreeCategorialItem> getAllCategoricalItems() throws ClassNotFoundException, SQLException{
  	  return rThreeService.getAllCategoricalItems();
    }
	
	@GET
	@Path("/categoricalItems/{id}")
	public RThreeCategorialItem getSingleCategoricalItem(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
    	return rThreeService.getSingleCategoricalItem(id);
    }
	
	@GET
	@Path("/categoricalItems/category/{id}")
	public ArrayList<RThreeCategorialItem> getCategoricalItemdByCategory(@PathParam("id") int category_id) throws ClassNotFoundException, SQLException{
    	return rThreeService.getCategoricalItemdByCategory(category_id);
    }
	*/
	
	@GET
	@Path("/most_popular_categories")
    public ArrayList<RThreeMostPopularCategory> getMostPopularCategories()throws ClassNotFoundException, SQLException{
		return rThreeService.getMostPopularCategories();
	}
	
	
	@GET
	@Path("/types")
	public ArrayList<RThreeType> getAllRThreeTypes() throws ClassNotFoundException, SQLException {
		return rThreeService.getAllRThreeTypes();
	}
	
	@GET
	@Path("/types/{id}")
    public RThreeType getSingleRThreeTypes(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    		return rThreeService.getSingleRThreeTypes(id);
    }
	
	@GET
	@Path("/categorial_item_list")
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsList() throws ClassNotFoundException, SQLException {
    		return rThreeService.getRThreeCategorialItemsList();
    }
	
	@GET
	@Path("/categorial_item_list/{id}")
    public RThreeCategorialItem getSingleRThreeCategorialItemsList(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	return rThreeService.getSingleRThreeCategorialItemsList(id);
    }
	
	@GET
	@Path("/categorial_item_list/category/{id}")
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsListByCategoryId(@PathParam("id") int cat_id) throws ClassNotFoundException, SQLException {
    		return rThreeService.getRThreeCategorialItemsListByCategoryId(cat_id);
    }
}
