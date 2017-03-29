package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.social.heartbeat.bean.Fact;
import org.social.heartbeat.dao.FactDAO;

public class FactService {

	FactDAO factDAO = new FactDAO();
	
	//Getting all of the Facts
	public ArrayList<Fact> getAllFacts() throws ClassNotFoundException, SQLException
	{
	   return factDAO.getAllFacts();
	}
	
	//Getting Single Fact
	public Fact getSingleFactById(int id)throws ClassNotFoundException, SQLException
	{
		return factDAO.getSingleFactById(id);
	}
	
	//Getting Facts According to City
	public ArrayList<Fact> getFactsAccordingToCity(int city_id) throws ClassNotFoundException, SQLException
	{
		return factDAO.getFactsAccordingToCity(city_id);
	}
	
	//Getting Facts according to city and aoi
	public ArrayList<Fact> getFactsAccordingToCityAndAOI(int city_id, int csr_init_id) throws ClassNotFoundException, SQLException
	{
		return factDAO.getFactsAccordingToCityAndAOI(city_id, csr_init_id);
	}
}
