package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.City;
import org.social.heartbeat.bean.Country;
import org.social.heartbeat.bean.Module;
import org.social.heartbeat.bean.OfficeAddress;
import org.social.heartbeat.bean.UserLocation;
import org.social.heartbeat.util.DBConnection;
import org.social.heartbeat.util.DistanceCalculator;

public class OfficeAddressDAO {

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Getting all office addresses
	public ArrayList<OfficeAddress> getOfficeAddress() throws SQLException, ClassNotFoundException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("SELECT t1.id, t1.address_name, t1.company_name, t1.building_name, t1.postal_code, t1.locality_name, t2.id AS `city_id`, t2.name AS `city`, t3.id AS `country_id`, t3.name AS `country` FROM `hb_office_address` AS `t1`, `hb_city` AS `t2`, `hb_country` AS `t3` WHERE t1.city_id = t2.id AND t2.country_id = t3.id   ");
		rs1  = ps1.executeQuery();
		
		ArrayList<OfficeAddress> office_address_list = new ArrayList<OfficeAddress>();
		
		while(rs1.next())
		{
			OfficeAddress officeAddress  = new OfficeAddress();
			officeAddress.setId(rs1.getInt(1));
			officeAddress.setAddress_name(rs1.getString(2));
			officeAddress.setComp_name(rs1.getString(3));
			officeAddress.setBuilding_name(rs1.getString(4));
			officeAddress.setPostal_code(rs1.getString(5));
			officeAddress.setLocality_name(rs1.getString(6));
			
			City city = new City();
			city.setId(rs1.getInt(7));
			city.setName(rs1.getString(8));
			
			
			Country country = new Country();
			country.setId(rs1.getInt(9));
			country.setName(rs1.getString(10));
			
			city.setCountry(country);
			
			officeAddress.setCity(city);
			
			
			//officeAddress.setCity(rs1.getInt(7));
			office_address_list.add(officeAddress);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return office_address_list;
		
	}
	
	//Getting all cities
	public ArrayList<City> getAllCities() throws SQLException, ClassNotFoundException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("SELECT * FROM hb_city");
		rs1  = ps1.executeQuery();
		
		ArrayList<City> cities_list = new ArrayList<City>();
		
		while(rs1.next())
		{
			
			City city = new City();
			city.setId(rs1.getInt(1));
			city.setName(rs1.getString(2));
			
			Country country = new Country();
			country.setId(rs1.getInt(3));
			
			city.setCountry(country);
			
			cities_list.add(city);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return cities_list;
		
	}
	
	
	//Getting nearest TCS Office to user's current location (Country Specific)
	public OfficeAddress getNearestTCSOffice(String latitude, String longitude, String country_short_name) throws SQLException, ClassNotFoundException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select t1.id as 'address_id', t1.address_name, t1.company_name, t1.building_name, t1.postal_code, t1.locality_name, t1.latitude, t1.longitude, t2.id, t2.name, t3.id, t3.name, t3.short_name from hb_office_address as t1, hb_city as t2, hb_country as t3 where t1.city_id=t2.id AND t2.country_id=t3.id AND t3.short_name=?");
		ps1.setString(1, country_short_name);
		rs1  = ps1.executeQuery();
	
		UserLocation userLocation = new UserLocation();
		
		userLocation.setLatitude(latitude);
		userLocation.setLongitude(longitude);
		
		ArrayList<OfficeAddress> country_office_address_list = new ArrayList<OfficeAddress>();
		
		while(rs1.next()){
			
			OfficeAddress officeAddress =new OfficeAddress();
			City city = new City();
			Country country = new Country();
			
			officeAddress.setId(rs1.getInt(1));
			officeAddress.setAddress_name(rs1.getString(2));
			officeAddress.setComp_name(rs1.getString(3));
			officeAddress.setBuilding_name(rs1.getString(4));
			officeAddress.setPostal_code(rs1.getString(5));
			officeAddress.setLocality_name(rs1.getString(6));
			officeAddress.setLatitude(rs1.getString(7));
			officeAddress.setLongitude(rs1.getString(8));
			
			city.setId(rs1.getInt(9));
			city.setName(rs1.getString(10));
			
			country.setId(rs1.getInt(11));
			country.setName(rs1.getString(12));
			country.setShort_name(rs1.getString(13));
			
			city.setCountry(country);
			officeAddress.setCity(city);
			
			country_office_address_list.add(officeAddress);
		}
		
		if(country_office_address_list.size()==1){
			return country_office_address_list.get(0);
		}else{
			return nearestTcsOffice(country_office_address_list, userLocation);
		}
	}
	
	//Getting nearest TCS Office to user's current location
	public OfficeAddress getNearestTCSOfficeComplete(String latitude, String longitude) throws SQLException, ClassNotFoundException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select t1.id as 'address_id', t1.address_name, t1.company_name, t1.building_name, t1.postal_code, t1.locality_name, t1.latitude, t1.longitude, t2.id, t2.name, t3.id, t3.name, t3.short_name from hb_office_address as t1, hb_city as t2, hb_country as t3 where t1.city_id=t2.id AND t2.country_id=t3.id");
		rs1  = ps1.executeQuery();
	
		UserLocation userLocation = new UserLocation();
		
		userLocation.setLatitude(latitude);
		userLocation.setLongitude(longitude);
		
		ArrayList<OfficeAddress> country_office_address_list = new ArrayList<OfficeAddress>();
		
		while(rs1.next()){
			
			OfficeAddress officeAddress =new OfficeAddress();
			City city = new City();
			Country country = new Country();
			
			officeAddress.setId(rs1.getInt(1));
			officeAddress.setAddress_name(rs1.getString(2));
			officeAddress.setComp_name(rs1.getString(3));
			officeAddress.setBuilding_name(rs1.getString(4));
			officeAddress.setPostal_code(rs1.getString(5));
			officeAddress.setLocality_name(rs1.getString(6));
			officeAddress.setLatitude(rs1.getString(7));
			officeAddress.setLongitude(rs1.getString(8));
			
			city.setId(rs1.getInt(9));
			city.setName(rs1.getString(10));
			
			country.setId(rs1.getInt(11));
			country.setName(rs1.getString(12));
			country.setShort_name(rs1.getString(13));
			
			city.setCountry(country);
			officeAddress.setCity(city);
			
			country_office_address_list.add(officeAddress);
		}
		
		if(country_office_address_list.size()==1){
			return country_office_address_list.get(0);
		}else{
			return nearestTcsOffice(country_office_address_list, userLocation);
		}
	}
	
	
	public OfficeAddress nearestTcsOffice(ArrayList<OfficeAddress> country_office_address_list, UserLocation userLocation){
		
		
		ArrayList<Double> distance_list = new ArrayList<Double>();
		
		for(OfficeAddress officeAddress : country_office_address_list){
			
			DistanceCalculator distanceCalculator = new DistanceCalculator();
			
			double dis = distanceCalculator.distance(Double.parseDouble(userLocation.getLatitude()),
										Double.parseDouble(userLocation.getLongitude()),
										Double.parseDouble(officeAddress.getLatitude()),
										Double.parseDouble(officeAddress.getLongitude()));
			
			distance_list.add(dis);
			
			System.out.println("Distance : "+dis);
			
		
		}
		
		double smallest = distance_list.get(0);
		int i = 0, pos=0;
		
		for(Double d1 : distance_list){
			if(d1<smallest){
				smallest = d1;
				pos=i;
			}
			i++;
		}
		
		return country_office_address_list.get(pos);
	}
	
}
