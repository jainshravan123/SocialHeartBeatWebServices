package org.social.heartbeat.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.FirebaseNotificationResp;
import org.social.heartbeat.bean.UserFirebaseToken;
import org.social.heartbeat.service.UserFirebaseNotificationService;

@Path("/notify")
public class UserFIrebaseNotificationResource 
{

	UserFirebaseNotificationService userFirebaseNotificationService = new UserFirebaseNotificationService();
	
	@POST
	@Path("/regiterToken")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserFirebaseToken registerFirebaseToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		 return userFirebaseNotificationService.registerFirebaseToken(userFirebaseToken);
	} 
	
	
	@POST
	@Path("/regiterTokenBeforeSignIn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserFirebaseToken registerFirebaseTokenBeforeSignin(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
	   return userFirebaseNotificationService.registerFirebaseTokenBeforeSignin(userFirebaseToken);
	}
	
	
	@PUT
	@Path("/updateUserIDAfterSignIn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserFirebaseToken updateUserIdAccorToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		   return userFirebaseNotificationService.updateUserIdAccorToken(userFirebaseToken);
	}
	
	@POST
	@Path("/sendQuizCompleteNotification")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FirebaseNotificationResp sendQuizCompleteNotificationToSpecificUser(UserFirebaseToken userFirebaseToken) throws ClassNotFoundException, SQLException, JSONException
	{
		return userFirebaseNotificationService.sendQuizCompleteNotificationToSpecificUser(userFirebaseToken);
	}
		
}
