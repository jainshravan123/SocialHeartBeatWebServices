package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.City;
import org.social.heartbeat.bean.CsrInitCategory;
import org.social.heartbeat.bean.Fact;
import org.social.heartbeat.bean.HostedImage;
import org.social.heartbeat.bean.HostedUrl;
import org.social.heartbeat.util.DBConnection;

public class FactDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	    //Getting all of the Facts
		public ArrayList<Fact> getAllFacts() throws ClassNotFoundException, SQLException
		{
				
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("SELECT t1.id, t1.fact, t1.short_desc, t1.detailed_desc, t2.id, t2.url, t3.id, t3.url, t4.id, t4.name FROM `hb_fact` AS `t1`, `hb_purpose_for_life_url` AS `t2`, `hb_image` AS `t3`, `hb_city` AS `t4` WHERE t1.uri = t2.id AND t1.image_id = t3.id AND t1.city_id = t4.id;");
			rs1  = ps1.executeQuery();
			
			ArrayList<Fact> facts_List = new ArrayList<Fact>();
			
			while(rs1.next())
			{
				
				Fact fact = new Fact();
				fact.setId(rs1.getInt(1));
				fact.setFact(rs1.getString(2));
				fact.setShort_desc(rs1.getString(3));
				fact.setLong_desc(rs1.getString(4));
				
				HostedUrl hostedUrl = new HostedUrl();
				HostedImage hostedImage = new HostedImage();
				City city =  new City();
				
				hostedUrl.setId(rs1.getInt(5));
				hostedUrl.setUrl(rs1.getString(6));
				
				hostedImage.setId(rs1.getInt(7));
				hostedImage.setImage_url(rs1.getString(8));
				
				city.setId(rs1.getInt(9));
				city.setName(rs1.getString(10));
				
				fact.setHostedUrl(hostedUrl);
				fact.setHostedImage(hostedImage);
				fact.setCity(city);
				
				facts_List.add(fact);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return facts_List;
		}
		
		
		//Getting Single Fact 
		public Fact getSingleFactById(int id)throws ClassNotFoundException, SQLException
		{
				
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("SELECT t1.id, t1.fact, t1.short_desc, t1.detailed_desc, t2.id, t2.url, t3.id, t3.url, t4.id, t4.name FROM `hb_fact` AS `t1`, `hb_purpose_for_life_url` AS `t2`, `hb_image` AS `t3`, `hb_city` AS `t4` WHERE t1.uri = t2.id AND t1.image_id = t3.id AND t1.city_id = t4.id AND t1.id=?");
			ps1.setInt(1, id);
			rs1  = ps1.executeQuery();
			
			Fact fact = new Fact();
			
			while(rs1.next())
			{
				
				
				fact.setId(rs1.getInt(1));
				fact.setFact(rs1.getString(2));
				fact.setShort_desc(rs1.getString(3));
				fact.setLong_desc(rs1.getString(4));
				
				HostedUrl hostedUrl = new HostedUrl();
				HostedImage hostedImage = new HostedImage();
				City city =  new City();
				
				hostedUrl.setId(rs1.getInt(5));
				hostedUrl.setUrl(rs1.getString(6));
				
				hostedImage.setId(rs1.getInt(7));
				hostedImage.setImage_url(rs1.getString(8));
				
				city.setId(rs1.getInt(9));
				city.setName(rs1.getString(10));
				
				fact.setHostedUrl(hostedUrl);
				fact.setHostedImage(hostedImage);
				fact.setCity(city);
				
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return fact;
		}
		
		//Getting Facts According to City
		public ArrayList<Fact> getFactsAccordingToCity(int city_id) throws ClassNotFoundException, SQLException
		{
				
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("SELECT t1.id, t1.fact, t1.short_desc, t1.detailed_desc, t2.id, t2.url, t3.id, t3.url, t4.id, t4.name FROM `hb_fact` AS `t1`, `hb_purpose_for_life_url` AS `t2`, `hb_image` AS `t3`, `hb_city` AS `t4` WHERE t1.uri = t2.id AND t1.image_id = t3.id AND t1.city_id = t4.id AND t4.id=?;");
			ps1.setInt(1, city_id);
			rs1  = ps1.executeQuery();
			
			ArrayList<Fact> facts_List = new ArrayList<Fact>();
			
			while(rs1.next())
			{
				
				Fact fact = new Fact();
				fact.setId(rs1.getInt(1));
				fact.setFact(rs1.getString(2));
				fact.setShort_desc(rs1.getString(3));
				fact.setLong_desc(rs1.getString(4));
				
				HostedUrl hostedUrl = new HostedUrl();
				HostedImage hostedImage = new HostedImage();
				City city =  new City();
				
				hostedUrl.setId(rs1.getInt(5));
				hostedUrl.setUrl(rs1.getString(6));
				
				hostedImage.setId(rs1.getInt(7));
				hostedImage.setImage_url(rs1.getString(8));
				
				city.setId(rs1.getInt(9));
				city.setName(rs1.getString(10));
				
				fact.setHostedUrl(hostedUrl);
				fact.setHostedImage(hostedImage);
				fact.setCity(city);
				
				facts_List.add(fact);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return facts_List;
		}
		
		//Getting Facts according to city and aoi
		public ArrayList<Fact> getFactsAccordingToCityAndAOI(int city_id, int csr_init_id) throws ClassNotFoundException, SQLException
		{
				
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("SELECT t1.id, t1.fact, t1.short_desc, t1.detailed_desc, t2.id, t2.url, t3.id, t3.url, t4.id, t4.name, t1.csr_init_id FROM `hb_fact` AS `t1`, `hb_purpose_for_life_url` AS `t2`, `hb_image` AS `t3`, `hb_city` AS `t4` WHERE t1.uri = t2.id AND t1.image_id = t3.id AND t1.city_id = t4.id AND t4.id=? AND t1.csr_init_id=?;");
			ps1.setInt(1, city_id);
			ps1.setInt(2, csr_init_id);
			rs1  = ps1.executeQuery();
			
			ArrayList<Fact> facts_List = new ArrayList<Fact>();
			
			while(rs1.next())
			{
				
				Fact fact = new Fact();
				fact.setId(rs1.getInt(1));
				fact.setFact(rs1.getString(2));
				fact.setShort_desc(rs1.getString(3));
				fact.setLong_desc(rs1.getString(4));
				
				HostedUrl hostedUrl = new HostedUrl();
				HostedImage hostedImage = new HostedImage();
				City city =  new City();
				
				hostedUrl.setId(rs1.getInt(5));
				hostedUrl.setUrl(rs1.getString(6));
				
				hostedImage.setId(rs1.getInt(7));
				hostedImage.setImage_url(rs1.getString(8));
				
				city.setId(rs1.getInt(9));
				city.setName(rs1.getString(10));
				
				CsrInitCategory csrInitCategory = new CsrInitCategory();
				csrInitCategory.setId(rs1.getInt(11));
				
				fact.setHostedUrl(hostedUrl);
				fact.setHostedImage(hostedImage);
				fact.setCity(city);
				fact.setCsrInitCategory(csrInitCategory);
				
				facts_List.add(fact);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return facts_List;
		}
		
}
