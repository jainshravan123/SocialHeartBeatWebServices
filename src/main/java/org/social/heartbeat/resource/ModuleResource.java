package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.Module;
import org.social.heartbeat.service.ModuleService;

@Path("/modules")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModuleResource {

	ModuleService moduleService = new ModuleService();
	
	@GET
	public ArrayList<Module> getAllEnabledModules() throws ClassNotFoundException, SQLException{
		return moduleService.getAllEnabledModules();
	}
	
}
