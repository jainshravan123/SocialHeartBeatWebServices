package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.RThreeCategorialItem;
import org.social.heartbeat.bean.RThreeCategory;
import org.social.heartbeat.bean.RThreeModule;
import org.social.heartbeat.bean.RThreeMostPopularCategory;
import org.social.heartbeat.bean.RThreeType;
import org.social.heartbeat.dao.RThreeDAO;

public class RThreeService {

	RThreeDAO rThreeDAO = new RThreeDAO();
	
	//Getting All Modules
	public ArrayList<RThreeModule> getAllModules() throws ClassNotFoundException, SQLException{
			return rThreeDAO.getAllModules();
	}
	
	//Getting Single Module based on id
    public RThreeModule getSingleModule(int id) throws ClassNotFoundException, SQLException{
    	return rThreeDAO.getSingleModule(id);
    }
	
    //Getting All Categories
  	public ArrayList<RThreeCategory> getAllCategories() throws ClassNotFoundException, SQLException{
  		return rThreeDAO.getAllCategories();
  	}
  	
    //Getting Single Category based on id
    public RThreeCategory getSingleCategory(int id) throws ClassNotFoundException, SQLException{
    	return rThreeDAO.getSingleCategory(id);
    }
    /*
    //Getting All Categorical Items
    public ArrayList<RThreeCategorialItem> getAllCategoricalItems() throws ClassNotFoundException, SQLException{
    	  return rThreeDAO.getAllCategoricalItems();
    }
    
    //Getting Single Categorical Item based on id
    public RThreeCategorialItem getSingleCategoricalItem(int id) throws ClassNotFoundException, SQLException{
    	return rThreeDAO.getSingleCategoricalItem(id);
    }
    
    //Getting Categorical Item based on categorical id
    public ArrayList<RThreeCategorialItem> getCategoricalItemdByCategory(int category_id) throws ClassNotFoundException, SQLException{
    	return rThreeDAO.getCategoricalItemdByCategory(category_id);
    }
    */
    
    //Getting All Most Popular Categories
    public ArrayList<RThreeMostPopularCategory> getMostPopularCategories()throws ClassNotFoundException, SQLException{
    	  return rThreeDAO.getMostPopularCategories();
    }
    
    
    //Get all RThree Types
    public ArrayList<RThreeType> getAllRThreeTypes() throws ClassNotFoundException, SQLException {
    		return rThreeDAO.getAllRThreeTypes();
    }
    
    //Get single rThreeType by rThreeType Id
    public RThreeType getSingleRThreeTypes(int id) throws ClassNotFoundException, SQLException {
    		return rThreeDAO.getSingleRThreeTypes(id);
    }
    
    //Get All Categorical Items
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsList() throws ClassNotFoundException, SQLException {
    		return rThreeDAO.getRThreeCategorialItemsList();
    }
    
    //Get Single Categorical Items
    public RThreeCategorialItem getSingleRThreeCategorialItemsList(int id) throws ClassNotFoundException, SQLException {
    		return rThreeDAO.getSingleRThreeCategorialItemsList(id);
    }
    
    //Get Categorical Item Based on category id
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsListByCategoryId(int cat_id) throws ClassNotFoundException, SQLException {
    	return rThreeDAO.getRThreeCategorialItemsListByCategoryId(cat_id);
    }
    
}
