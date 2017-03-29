package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.Fact;
import org.social.heartbeat.service.FactService;

@Path("/facts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FactResource {

	FactService factService =  new FactService();
	
	@GET
	public ArrayList<Fact> getAllFacts() throws ClassNotFoundException, SQLException
	{
	   return factService.getAllFacts();
	}
	
	@GET
	@Path("/{fact_id}")
	public Fact getSingleFactById(@PathParam("fact_id") int fact_id)throws ClassNotFoundException, SQLException
	{
		return factService.getSingleFactById(fact_id);
	}
	
	@GET
	@Path("/city/{city_id}")
	public ArrayList<Fact> getFactsAccordingToCity(@PathParam("city_id") int city_id) throws ClassNotFoundException, SQLException
	{
		return factService.getFactsAccordingToCity(city_id);
	}
	
	@GET
	@Path("/{city_id}/{csr_init_id}")
	public ArrayList<Fact> getFactsAccordingToCityAndAOI(@PathParam("city_id") int city_id, @PathParam("csr_init_id") int csr_init_id) throws ClassNotFoundException, SQLException
	{
		return factService.getFactsAccordingToCityAndAOI(city_id, csr_init_id);
	}
}
