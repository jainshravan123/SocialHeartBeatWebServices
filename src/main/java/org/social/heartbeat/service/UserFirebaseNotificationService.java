package org.social.heartbeat.service;

import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.FirebaseNotificationResp;
import org.social.heartbeat.bean.UserFirebaseToken;
import org.social.heartbeat.dao.UserFirebaseNotificationDAO;

public class UserFirebaseNotificationService 
{

	UserFirebaseNotificationDAO userFirebaseNotificationDAO = new UserFirebaseNotificationDAO();
	
	//Registers Token of user
	public UserFirebaseToken registerFirebaseToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
	  return userFirebaseNotificationDAO.registerFirebaseToken(userFirebaseToken);
	}
	
	//Registers Token Before SignIn
	public UserFirebaseToken registerFirebaseTokenBeforeSignin(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
	   return userFirebaseNotificationDAO.registerFirebaseTokenBeforeSignin(userFirebaseToken);
	}
	
	//Update UserId After SignIn
	public UserFirebaseToken updateUserIdAccorToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		if(userFirebaseNotificationDAO.updateUserIdAccorToken(userFirebaseToken) == null){
			userFirebaseToken.setSuccess(0);
			return userFirebaseToken;
		}
	   return userFirebaseNotificationDAO.updateUserIdAccorToken(userFirebaseToken);
	}
	
	public FirebaseNotificationResp sendQuizCompleteNotificationToSpecificUser(UserFirebaseToken userFirebaseToken) throws ClassNotFoundException, SQLException, JSONException
	{
		return userFirebaseNotificationDAO.sendQuizCompleteNotificationToSpecificUser(userFirebaseToken);
	}
}
