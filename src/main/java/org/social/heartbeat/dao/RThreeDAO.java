package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.*;

import org.social.heartbeat.util.DBConnection;

public class RThreeDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Getting All Modules
	public ArrayList<RThreeModule> getAllModules() throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_r3_module");
		rs1  = ps1.executeQuery();
		
		ArrayList<RThreeModule> rThreeModules = new ArrayList<RThreeModule>();
		
		while(rs1.next())
		{
			RThreeModule rThreeModule = new RThreeModule();
			rThreeModule.setId(rs1.getInt(1));
			rThreeModule.setModule_name(rs1.getString(2));
			rThreeModule.setModule_icon(rs1.getString(3));
			rThreeModule.setStatus(rs1.getInt(4));
			
			rThreeModules.add(rThreeModule);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return rThreeModules;
	}
	
	//Getting Single Module based on id
    public RThreeModule getSingleModule(int id) throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_r3_module where id=?");
		ps1.setInt(1, id);
		rs1  = ps1.executeQuery();
		
		RThreeModule rThreeModule = new RThreeModule();
		
		while(rs1.next())
		{
			rThreeModule.setId(rs1.getInt(1));
			rThreeModule.setModule_name(rs1.getString(2));
			rThreeModule.setModule_icon(rs1.getString(3));
			rThreeModule.setStatus(rs1.getInt(4));
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return rThreeModule;
	 }
    
    //Getting All Categories
  	public ArrayList<RThreeCategory> getAllCategories() throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.category_name, t1.created_by, t2.firstname, t1.creation_date, t1.updation_date from hb_r3_category as t1, hb_user as t2 where t1.created_by = t2.id");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategory> rThreeCategories = new ArrayList<RThreeCategory>();
  		
  		while(rs1.next())
  		{
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(1));
  			rThreeCategory.setCategory_name(rs1.getString(2));
  			
  			User RThreeCategoryAuthor = new User();
  			RThreeCategoryAuthor.setId(rs1.getInt(3));
  			RThreeCategoryAuthor.setFirstname(rs1.getString(4));
  			
  			rThreeCategory.setAuthor(RThreeCategoryAuthor);
  			rThreeCategory.setCreation_date(rs1.getString(5));
  			rThreeCategory.setUpdation_date(rs1.getString(6));
  			
  			rThreeCategories.add(rThreeCategory);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategories;
  	}
  	
  	//Getting Single Category based on id
    public RThreeCategory getSingleCategory(int id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.category_name, t1.created_by, t2.firstname, t1.creation_date, t1.updation_date from hb_r3_category as t1, hb_user as t2 where t1.created_by = t2.id AND t1.id =?");
  		ps1.setInt(1, id);
  		rs1  = ps1.executeQuery();
  		
  		RThreeCategory rThreeCategory = new RThreeCategory();
  		
  		while(rs1.next())
  		{
  			rThreeCategory.setId(rs1.getInt(1));
  			rThreeCategory.setCategory_name(rs1.getString(2));
  			
  			User RThreeCategoryAuthor = new User();
  			RThreeCategoryAuthor.setId(rs1.getInt(3));
  			RThreeCategoryAuthor.setFirstname(rs1.getString(4));
  			
  			rThreeCategory.setAuthor(RThreeCategoryAuthor);
  			rThreeCategory.setCreation_date(rs1.getString(5));
  			rThreeCategory.setUpdation_date(rs1.getString(6));
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategory;
  	}

    /*
    //Getting All Categorical Items
    public ArrayList<RThreeCategorialItem> getAllCategoricalItems() throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.category_id, t1.heading, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.author, t2.firstname, t1.reference, t1.location, t3.city from hb_r3_categorical_item as t1, hb_user as t2, hb_r3_user_location as t3 where t1.author = t2.id AND t1.location = t3.id");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		
  		while(rs1.next())
  		{
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			
  			User rThreeCategoryItemAuthor = new User();
  			rThreeCategoryItemAuthor.setId(rs1.getInt(9));
  			rThreeCategoryItemAuthor.setFirstname(rs1.getString(10));
  			rThreeCategorialItem.setAuthor(rThreeCategoryItemAuthor);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(11));
  			
  			RThreeUserLocation rThreeUserLocation = new RThreeUserLocation();
  			rThreeUserLocation.setId(rs1.getInt(12));
  			rThreeUserLocation.setCity(rs1.getString(13));
  			rThreeCategorialItem.setRtThreeUserLocation(rThreeUserLocation);
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
  	}
    
    //Getting Single Categorical Item based on id
    public RThreeCategorialItem getSingleCategoricalItem(int id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.category_id, t1.heading, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.author, t2.firstname, t1.reference, t1.location, t3.city from hb_r3_categorical_item as t1, hb_user as t2, hb_r3_user_location as t3 where t1.author = t2.id AND t1.location = t3.id AND t1.id=?");
  		ps1.setInt(1, id);
  		rs1  = ps1.executeQuery();
  		
  		RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  		
  		while(rs1.next())
  		{
  			
            rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			
  			User rThreeCategoryItemAuthor = new User();
  			rThreeCategoryItemAuthor.setId(rs1.getInt(9));
  			rThreeCategoryItemAuthor.setFirstname(rs1.getString(10));
  			rThreeCategorialItem.setAuthor(rThreeCategoryItemAuthor);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(11));
  			
  			RThreeUserLocation rThreeUserLocation = new RThreeUserLocation();
  			rThreeUserLocation.setId(rs1.getInt(12));
  			rThreeUserLocation.setCity(rs1.getString(13));
  			rThreeCategorialItem.setRtThreeUserLocation(rThreeUserLocation);
  			
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItem;
  	}
    
    //Getting Categorical Item based on categorical id
    public ArrayList<RThreeCategorialItem> getCategoricalItemdByCategory(int category_id) throws ClassNotFoundException, SQLException{
  		
  		con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.category_id, t1.heading, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.author, t2.firstname, t1.reference, t1.location, t3.city from hb_r3_categorical_item as t1, hb_user as t2, hb_r3_user_location as t3 where t1.author = t2.id AND t1.location = t3.id AND t1.category_id=?");
  		ps1.setInt(1, category_id);
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		
  		
  		while(rs1.next())
  		{
  			
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			rThreeCategory.setId(rs1.getInt(2));
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(3));
  			rThreeCategorialItem.setDescription(rs1.getString(4));
  			rThreeCategorialItem.setLikes(rs1.getInt(5));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(6));
  			rThreeCategorialItem.setCreation_date(rs1.getString(7));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(8));
  			
  			User rThreeCategoryItemAuthor = new User();
  			rThreeCategoryItemAuthor.setId(rs1.getInt(9));
  			rThreeCategoryItemAuthor.setFirstname(rs1.getString(10));
  			rThreeCategorialItem.setAuthor(rThreeCategoryItemAuthor);
  			
  			rThreeCategorialItem.setHeading(rs1.getString(11));
  			
  			RThreeUserLocation rThreeUserLocation = new RThreeUserLocation();
  			rThreeUserLocation.setId(rs1.getInt(12));
  			rThreeUserLocation.setCity(rs1.getString(13));
  			rThreeCategorialItem.setRtThreeUserLocation(rThreeUserLocation);
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
  	}
  	
  	//Getting Most By users category items
    public ArrayList<RThreeCategorialItem> getMostByUsersCategoryItems() throws ClassNotFoundException, SQLException
    {
    	
    	con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_categorical_item where category_id=?");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
    }
    
    */
    //Getting All Most Popular Categories
    public ArrayList<RThreeMostPopularCategory> getMostPopularCategories()throws ClassNotFoundException, SQLException{
    	
    	con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select * from hb_r3_most_popular_categories");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeMostPopularCategory> rThreeMostPopularCategories = new ArrayList<RThreeMostPopularCategory>();
  		while(rs1.next()){
  			RThreeMostPopularCategory rThreeMostPopularCategory = new RThreeMostPopularCategory();
  			rThreeMostPopularCategory.setId(rs1.getInt(1));
  			rThreeMostPopularCategory.setName(rs1.getString(2));
  			rThreeMostPopularCategory.setIcon(rs1.getString(3));
  			rThreeMostPopularCategory.setStatus(rs1.getInt(4));
  			rThreeMostPopularCategories.add(rThreeMostPopularCategory);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		return rThreeMostPopularCategories;
    }
    
    
    //Get all R Three Types
    public ArrayList<RThreeType> getAllRThreeTypes() throws ClassNotFoundException, SQLException {
    	con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.name, t1.description, t2.id, t2.category_name, t2.created_by, t2.creation_date, t2.updation_date, t3.id, t3.username, t3.emp_id, t3.firstname, t3.lastname, t3.email, t3.status, t4.id, t4.status, t1.remarks from `hb_r3_type` as t1, `hb_r3_category` as t2, `hb_user` as t3, `hb_user_status` as t4 where t1.cat_id = t2.id AND  t1.author_id = t3.id AND t1.status_id = t4.id");
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeType> rThreeTypes = new ArrayList<RThreeType>();
  		while(rs1.next()) {
  			RThreeType rThreeType = new RThreeType();
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			User user = new User();
  			UserStatus userStatus = new UserStatus();
  		
  			rThreeType.setId(rs1.getInt(1));
  			rThreeType.setName(rs1.getString(2));
  			rThreeType.setDescription(rs1.getString(3));
  			
  			rThreeCategory.setId(rs1.getInt(4));
  			rThreeCategory.setCategory_name(rs1.getString(5));
  			User rThreeAuthor = new User();
  			rThreeAuthor.setId(rs1.getInt(6));
  			rThreeCategory.setAuthor(rThreeAuthor);
  			rThreeCategory.setCreation_date(rs1.getString(7));
  			rThreeCategory.setUpdation_date(rs1.getString(8));
  			
  			user.setId(rs1.getInt(9));
  			user.setUsername(rs1.getString(10));
  			user.setEmp_id(rs1.getString(11));
  			user.setFirstname(rs1.getString(12));
  			user.setLastname(rs1.getString(13));
  			user.setEmail(rs1.getString(14));
  			
  			UserStatus userStatus2 = new UserStatus();
  			userStatus2.setId(rs1.getInt(15));
  			user.setUserStatus(userStatus2);
  			
  			userStatus.setId(rs1.getInt(16));
  			userStatus.setStatus(rs1.getString(17));
  			
  			rThreeType.setRemarks(rs1.getString(18));
  			
  			rThreeType.setrThreeCategory(rThreeCategory);
  			rThreeType.setUserAuthor(user);
  			rThreeType.setUserStatus(userStatus);
  			
  			rThreeTypes.add(rThreeType);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeTypes;
    }
    
    //Get single rThreeType by rThreeType Id
    public RThreeType getSingleRThreeTypes(int id) throws ClassNotFoundException, SQLException {
    	con1 = db.getConnection();
  		ps1  = con1.prepareStatement("select t1.id, t1.name, t1.description, t2.id, t2.category_name, t2.created_by, t2.creation_date, t2.updation_date, t3.id, t3.username, t3.emp_id, t3.firstname, t3.lastname, t3.email, t3.status, t4.id, t4.status, t1.remarks from `hb_r3_type` as t1, `hb_r3_category` as t2, `hb_user` as t3, `hb_user_status` as t4 where t1.cat_id = t2.id AND  t1.author_id = t3.id AND t1.status_id = t4.id AND t1.id = ?");
  		ps1.setInt(1, id);
  		rs1  = ps1.executeQuery();
  		
  		RThreeType rThreeType = new RThreeType();
  		RThreeCategory rThreeCategory = new RThreeCategory();
		User user = new User();
		UserStatus userStatus = new UserStatus();
		
  		while(rs1.next()) {
  			
  			rThreeType.setId(rs1.getInt(1));
  			rThreeType.setName(rs1.getString(2));
  			rThreeType.setDescription(rs1.getString(3));
  			
  			rThreeCategory.setId(rs1.getInt(4));
  			rThreeCategory.setCategory_name(rs1.getString(5));
  			User rThreeAuthor = new User();
  			rThreeAuthor.setId(rs1.getInt(6));
  			rThreeCategory.setAuthor(rThreeAuthor);
  			rThreeCategory.setCreation_date(rs1.getString(7));
  			rThreeCategory.setUpdation_date(rs1.getString(8));
  			
  			user.setId(rs1.getInt(9));
  			user.setUsername(rs1.getString(10));
  			user.setEmp_id(rs1.getString(11));
  			user.setFirstname(rs1.getString(12));
  			user.setLastname(rs1.getString(13));
  			user.setEmail(rs1.getString(14));
  			
  			UserStatus userStatus2 = new UserStatus();
  			userStatus2.setId(rs1.getInt(15));
  			user.setUserStatus(userStatus2);
  			
  			userStatus.setId(rs1.getInt(16));
  			userStatus.setStatus(rs1.getString(17));
  			
  			rThreeType.setRemarks(rs1.getString(18));
  			
  			rThreeType.setrThreeCategory(rThreeCategory);
  			rThreeType.setUserAuthor(user);
  			rThreeType.setUserStatus(userStatus);
  			
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeType;
    }
    
    //Get All Categorical Items
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsList() throws ClassNotFoundException, SQLException {
    	
    	con1 = db.getConnection();
    	//String sql = "select t1.id, t1.title, t1.description, t1.title, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.resource, t2.id, t2.category_name, t3.id, t3.name, t3.description, t3.remarks, t4.id, t4.category_name, t4.created_by, t4.creation_date, t4.updation_date, t5.id, t5.username, t5.emp_id, t5.firstname, t5.lastname, t5.email, t5.status, t6.id, t6.status from `hb_r3_categorical_item` as t1, `hb_r3_category` as t2, `hb_r3_type` as t3,  `hb_r3_category` as t4,  `hb_user` as t5, `hb_user_status` as t6 where t1.category_id = t2.id AND t1.type_id = t3.id AND t3.cat_id = t4.id AND t3.author_id = t5.id AND t3.status_id = t6.id";
  		String sql = "select t1.id, t1.title, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.resource, t2.id, t2.category_name, t3.id, t3.name from `hb_r3_categorical_item` as t1, `hb_r3_category` as t2, `hb_r3_type` as t3 where t1.category_id = t2.id AND t1.type_id = t3.id order by t1.id";
    	ps1  = con1.prepareStatement(sql);
  		rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		while(rs1.next()) {
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			RThreeType rThreeType = new RThreeType();
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			rThreeCategorialItem.setHeading(rs1.getString(2));
  			rThreeCategorialItem.setDescription(rs1.getString(3));
  			rThreeCategorialItem.setLikes(rs1.getInt(4));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(5));
  			rThreeCategorialItem.setCreation_date(rs1.getString(6));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(7));
  			rThreeCategorialItem.setReference(rs1.getString(8));
  			
  			rThreeCategory.setId(rs1.getInt(9));
  			rThreeCategory.setCategory_name(rs1.getString(10));
  			
  			rThreeType.setId(rs1.getInt(11));
  			rThreeType.setName(rs1.getString(12));
  			
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			rThreeCategorialItem.setrThreeType(rThreeType);
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
    }
    
    //Get Single Categorical Items
    public RThreeCategorialItem getSingleRThreeCategorialItemsList(int id) throws ClassNotFoundException, SQLException {
    	
    	con1 = db.getConnection();
    	String sql = "select t1.id, t1.title, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.resource, t2.id, t2.category_name, t3.id, t3.name from `hb_r3_categorical_item` as t1, `hb_r3_category` as t2, `hb_r3_type` as t3 where t1.category_id = t2.id AND t1.type_id = t3.id AND t1.id = ?";
    	ps1  = con1.prepareStatement(sql);
  		ps1.setInt(1, id);
    	rs1  = ps1.executeQuery();
  		
    	RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
		RThreeType rThreeType = new RThreeType();
		RThreeCategory rThreeCategory = new RThreeCategory();
  		while(rs1.next()) {
  			
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			rThreeCategorialItem.setHeading(rs1.getString(2));
  			rThreeCategorialItem.setDescription(rs1.getString(3));
  			rThreeCategorialItem.setLikes(rs1.getInt(4));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(5));
  			rThreeCategorialItem.setCreation_date(rs1.getString(6));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(7));
  			rThreeCategorialItem.setReference(rs1.getString(8));
  			
  			rThreeCategory.setId(rs1.getInt(9));
  			rThreeCategory.setCategory_name(rs1.getString(10));
  			
  			rThreeType.setId(rs1.getInt(11));
  			rThreeType.setName(rs1.getString(12));
  			
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			rThreeCategorialItem.setrThreeType(rThreeType);
  			
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItem;
    }
    
    
    //Get Categorical Item Based on category id
    public ArrayList<RThreeCategorialItem> getRThreeCategorialItemsListByCategoryId(int cat_id) throws ClassNotFoundException, SQLException {
    	
    	con1 = db.getConnection();
    	String sql = "select t1.id, t1.title, t1.description, t1.likes, t1.most_useful, t1.creation_date, t1.updation_date, t1.resource, t2.id, t2.category_name, t3.id, t3.name from `hb_r3_categorical_item` as t1, `hb_r3_category` as t2, `hb_r3_type` as t3 where t1.category_id = t2.id AND t1.type_id = t3.id AND t1.category_id = ? order by t1.id";
    	ps1  = con1.prepareStatement(sql);
  		ps1.setInt(1, cat_id);
    	rs1  = ps1.executeQuery();
  		
  		ArrayList<RThreeCategorialItem> rThreeCategorialItems = new ArrayList<RThreeCategorialItem>();
  		while(rs1.next()) {
  			RThreeCategorialItem rThreeCategorialItem = new RThreeCategorialItem();
  			RThreeType rThreeType = new RThreeType();
  			RThreeCategory rThreeCategory = new RThreeCategory();
  			
  			rThreeCategorialItem.setId(rs1.getInt(1));
  			rThreeCategorialItem.setHeading(rs1.getString(2));
  			rThreeCategorialItem.setDescription(rs1.getString(3));
  			rThreeCategorialItem.setLikes(rs1.getInt(4));
  			rThreeCategorialItem.setMost_useful(rs1.getInt(5));
  			rThreeCategorialItem.setCreation_date(rs1.getString(6));
  			rThreeCategorialItem.setUpdation_date(rs1.getString(7));
  			rThreeCategorialItem.setReference(rs1.getString(8));
  			
  			rThreeCategory.setId(rs1.getInt(9));
  			rThreeCategory.setCategory_name(rs1.getString(10));
  			
  			rThreeType.setId(rs1.getInt(11));
  			rThreeType.setName(rs1.getString(12));
  			
  			rThreeCategorialItem.setrThreeCategory(rThreeCategory);
  			rThreeCategorialItem.setrThreeType(rThreeType);
  			
  			rThreeCategorialItems.add(rThreeCategorialItem);
  		}
  		
  		rs1.close();
  		ps1.close();
  		con1.close();
  		
  		return rThreeCategorialItems;
    }
}
