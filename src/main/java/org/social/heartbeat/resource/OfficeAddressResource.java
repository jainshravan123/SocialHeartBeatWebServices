package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.City;
import org.social.heartbeat.bean.OfficeAddress;
import org.social.heartbeat.service.OfficeAddressService;

@Path("/office_address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfficeAddressResource {

	OfficeAddressService officeAddressService = new OfficeAddressService();
	
	@GET
	public ArrayList<OfficeAddress> getOfficeAddress() throws SQLException, ClassNotFoundException{
	      return officeAddressService.getOfficeAddress();
	}
	
	@GET
	@Path("/cities")
	public ArrayList<City> getAllCities() throws SQLException, ClassNotFoundException
	{
		 return officeAddressService.getAllCities();
	}
	
	@GET
	@Path("/nearest/{latitude}/{longitude}/{country_short_name}")
	public OfficeAddress getNearestTCSOffice(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude, @PathParam("country_short_name") String country_short_name) throws SQLException, ClassNotFoundException
	{
		return officeAddressService.getNearestTCSOffice(latitude, longitude, country_short_name);
	}
	
	@GET
	@Path("/nearest/{latitude}/{longitude}")
	public OfficeAddress getNearestTCSOfficeComplete(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) throws SQLException, ClassNotFoundException
	{
		return officeAddressService.getNearestTCSOfficeComplete(latitude, longitude);
	}
}
